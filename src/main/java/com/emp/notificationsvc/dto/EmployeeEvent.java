package com.emp.notificationsvc.dto;

public class EmployeeEvent {

    private String eventType;
    private Long employeeId;
    private String employeeName;
    private String employeeEmail;
    private String department;

    public EmployeeEvent() {
    }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getEmployeeEmail() { return employeeEmail; }
    public void setEmployeeEmail(String employeeEmail) { this.employeeEmail = employeeEmail; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}
