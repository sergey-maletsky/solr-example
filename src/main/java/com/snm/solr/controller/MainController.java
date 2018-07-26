package com.snm.solr.controller;

import com.snm.solr.model.User;
import com.snm.solr.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.data.solr.core.query.Query.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    private UserService userService;

    @ApiOperation("Show the main page")
    @GetMapping
    public ModelAndView index(
            @PageableDefault(page = 0, size = DEFAULT_PAGE_SIZE, sort = "age", direction = Sort.Direction.ASC) Pageable pageable,
            ModelAndView modelAndView) {

        Page<User> users;
        try {
            users = userService.list(pageable);
        } catch (DataAccessResourceFailureException ex) {
            userService.createTestUsers();
            users = userService.list(pageable);
        }

        modelAndView.addObject("users", users.getContent());
        modelAndView.addObject("pagesTotal", users.getTotalPages());
        modelAndView.addObject("recordsTotal", users.getTotalElements());
        modelAndView.addObject("currentPage", pageable.getPageNumber());
        modelAndView.addObject("currentPageSize", pageable.getPageSize());
        modelAndView.setViewName("index");

        return modelAndView;
    }
}
