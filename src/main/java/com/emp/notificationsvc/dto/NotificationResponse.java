package com.emp.notificationsvc.dto;

import java.time.LocalDateTime;

public record NotificationResponse(
        Long id,
        String eventType,
        Long employeeId,
        String employeeName,
        String employeeEmail,
        String message,
        LocalDateTime createdAt
) {}
