package com.example.demo.controller;

import com.example.demo.dto.UserCriteriaDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api("User")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation("Добавить товар")
    @PostMapping("/create")
    public ResponseEntity createUser(@ApiParam("User")
                                     @RequestHeader("x-Source") String xSource,
                                     @RequestBody User user) {
        userService.create(user, xSource);
        return ResponseEntity.ok().build();

    }

    @ApiOperation("Получить по id")
    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @ApiOperation("Search")
    @PostMapping("/search")
    public ResponseEntity search(@RequestBody UserCriteriaDto dto) {
        return ResponseEntity.ok(userService.search(dto));
    }
}
