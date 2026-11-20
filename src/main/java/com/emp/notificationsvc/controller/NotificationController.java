package com.emp.notificationsvc.controller;

import com.emp.notificationsvc.dto.NotificationResponse;
import com.emp.notificationsvc.service.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @GetMapping
    public List<NotificationResponse> getAll() {
        return service.findAll();
    }
}
