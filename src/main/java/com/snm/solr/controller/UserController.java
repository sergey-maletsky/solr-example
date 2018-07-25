package com.snm.solr.controller;

import com.snm.solr.dto.JsonResult;
import com.snm.solr.model.Amount;
import com.snm.solr.model.User;
import com.snm.solr.service.AmountService;
import com.snm.solr.service.UserService;
import com.snm.solr.utils.AppConstants;
import com.snm.solr.utils.UserGenerator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static com.snm.solr.dto.JsonResult.ErrorCode.NO_ERROR;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AmountService amountService;

    @ApiOperation("generate users")
    @GetMapping(name = "/gen")
    public ResponseEntity<JsonResult> generateUsers(@RequestParam(value = "users_amount", required = false, defaultValue = "1") Integer usersAmount) {

        usersAmount = processUsersAmount(usersAmount);
        Set<User> users = UserGenerator.generateUsers(usersAmount);
        userService.saveAll(users);

        return createResponseEntity(getJsonResult(usersAmount));
    }

    private Integer processUsersAmount(Integer usersAmount) {

        Integer processedUsersAmount = 1;
        if (usersAmount > 0) {
            processedUsersAmount = usersAmount;
            Amount amount = amountService.findOne();
            Integer usersTotalAmount = amount.getUsersTotalAmount();
            Integer totalAmount = usersTotalAmount + usersAmount;
            if (totalAmount > AppConstants.MAX_USERS_AMOUNT) {
                processedUsersAmount = AppConstants.MAX_USERS_AMOUNT - usersTotalAmount;
            }

            amountService.update(processedUsersAmount + usersTotalAmount);
        }

        return processedUsersAmount;
    }

    private JsonResult getJsonResult(int usersAmount) {

        JsonResult result = new JsonResult(NO_ERROR);
        String responseMassage = usersAmount > 0 ? usersAmount + " пользователей успешно создано"
                : "Количество пользователей в системе превысило предел. Невозможно создать новых пользователей";
        result.setMessage(responseMassage);

        return result;
    }

    @ApiOperation("Get total amount of users")
    @GetMapping
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
