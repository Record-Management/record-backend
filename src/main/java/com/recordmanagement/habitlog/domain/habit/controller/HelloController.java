package com.recordmanagement.habitlog.domain.habit.controller;

import com.recordmanagement.habitlog.common.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public ApiResponse<String> hello() {
        return ApiResponse.success("Hello, HabitLog!");
    }
}
