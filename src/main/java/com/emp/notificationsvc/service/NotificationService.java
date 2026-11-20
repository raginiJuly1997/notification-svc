package com.emp.notificationsvc.service;

import com.emp.notificationsvc.dto.EmployeeEvent;
import com.emp.notificationsvc.dto.NotificationResponse;
import com.emp.notificationsvc.entity.Notification;
import com.emp.notificationsvc.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "employee-events", groupId = "notification-group")
    public void handleEmployeeEvent(EmployeeEvent event) {
        log.info("Received employee event: {} for employee id={}", event.getEventType(), event.getEmployeeId());

        String message = buildMessage(event);

        Notification notification = new Notification(
                event.getEventType(),
                event.getEmployeeId(),
                event.getEmployeeName(),
                event.getEmployeeEmail(),
                message
        );

        repository.save(notification);
        log.info("Notification saved: {}", message);
    }

    public List<NotificationResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(n -> new NotificationResponse(
                        n.getId(),
                        n.getEventType(),
                        n.getEmployeeId(),
                        n.getEmployeeName(),
                        n.getEmployeeEmail(),
                        n.getMessage(),
                        n.getCreatedAt()
                ))
                .toList();
    }

    private String buildMessage(EmployeeEvent event) {
        return switch (event.getEventType()) {
            case "EMPLOYEE_CREATED" -> String.format(
                    "Employee %s (%s) was created.", event.getEmployeeName(), event.getEmployeeEmail());
            case "EMPLOYEE_DELETED" -> String.format(
                    "Employee %s (%s) was deleted.", event.getEmployeeName(), event.getEmployeeEmail());
            default -> String.format(
                    "Employee event %s received for %s.", event.getEventType(), event.getEmployeeName());
        };
    }
}
