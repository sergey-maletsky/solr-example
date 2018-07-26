package com.snm.solr.controller;

import com.snm.solr.dto.JsonResult;
import com.snm.solr.dto.UserDto;
import com.snm.solr.model.User;
import com.snm.solr.service.AmountService;
import com.snm.solr.service.UserService;
import com.snm.solr.utils.UserGenerator;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

import static com.snm.solr.dto.JsonResult.ErrorCode.*;
import static org.springframework.data.solr.core.query.Query.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AmountService amountService;

    @ApiOperation("generate users")
    @PostMapping("/generate")
    public ResponseEntity<JsonResult> generateUsers(
            @RequestParam(value = "amount", required = false, defaultValue = "1") Integer usersAmount) {

        usersAmount = amountService.processUsersAmount(usersAmount);
        List<User> users = UserGenerator.generateUsers(usersAmount);
        userService.saveAll(users);

        JsonResult jsonResult = getJsonResult(usersAmount);
        return createResponseEntity(jsonResult);
    }

    @ApiOperation("Find users in the page")
    @GetMapping("/search")
    public ResponseEntity<JsonResult> findInPage(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @PageableDefault(page = 0, size = DEFAULT_PAGE_SIZE, sort = "age", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<User> users = userService.findAllByName(name, pageable);
        JsonResult result = new JsonResult(NO_ERROR);
        result.setResult(users.getContent());
        return createResponseEntity(result);
    }

    @ApiOperation("find all users by name")
    @GetMapping
    public ResponseEntity<JsonResult> list(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                           @RequestParam(value = "age", required = false) Integer age) {

        JsonResult result = new JsonResult(NO_ERROR);
        if (!StringUtils.isEmpty(name) && Objects.nonNull(age)) {
            result.setMessage("search");
            result.setResult(userService.findAllByNameOrAge(name, age));
        } else if (Objects.isNull(age)) {
            result.setMessage("search");
            result.setResult(userService.findAllByName(name));
        } else if (StringUtils.isEmpty(name)) {
            result.setMessage("search");
            result.setResult(userService.findAllByAge(age));
        } else {
            result.setErrorCode(ENTITY_NOT_FOUND.getCode());
            result.setMessage("Users not found");
        }
        return createResponseEntity(result);
    }

    @ApiOperation("Create a new user")
    @PostMapping
    public ResponseEntity<JsonResult> create(@Valid UserDto userDto, BindingResult bindingResult) {

        JsonResult result;
        ResponseEntity<JsonResult> responseEntity;
        if (bindingResult.hasErrors()) {
            result = setValidationErrors(bindingResult);
            responseEntity = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            UserDto newUserDto = userService.save(userDto);
            result = new JsonResult<>(NO_ERROR);
            result.setMessage("create");
            result.setResult(newUserDto);
            responseEntity = new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return responseEntity;
    }

    private <T> JsonResult<T> setValidationErrors(BindingResult binding) {

        JsonResult<T> result = new JsonResult<>(VALIDATION_ERROR);
        for (FieldError fieldError : binding.getFieldErrors()) {
            result.getValidatorErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return result;
    }

    private JsonResult getJsonResult(int usersAmount) {

        JsonResult result = new JsonResult(NO_ERROR);
        String responseMassage = usersAmount > 0 ? usersAmount + " пользователей успешно создано"
                : "Количество пользователей в системе превысило предел. Невозможно создать новых пользователей";
        result.setMessage(responseMassage);

        return result;
    }

    @ApiOperation("Get total amount of users")
    @GetMapping("/total_amount")
    public ResponseEntity<JsonResult> getUsersTotalAmount() {

        JsonResult jsonResult = new JsonResult(NO_ERROR);
        Integer usersTotalAmount = amountService.findOne().getUsersTotalAmount();
        jsonResult.setResult(usersTotalAmount);
        jsonResult.setMessage("Количество пользователей в системе");

        return createResponseEntity(jsonResult);
    }

    private ResponseEntity createResponseEntity(JsonResult result) {
        if (!result.getErrorCode().equals(NO_ERROR.getCode())) {
            return ResponseEntity.badRequest().body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }
}
