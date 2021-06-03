package com.risefalcon.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 任务分发
 */
@RequestMapping("/task")
public interface TaskController {
    @GetMapping("/")
    String getTask();

    @PostMapping("/")
    String postTask();
}
