package com.laptrinhjavaweb.dto.response;

public class StaffResponse {
    private String fullName;
    private Long id;
    private String checked;

    public StaffResponse() {
    }

    public StaffResponse(String fullName, Long id, String checked) {
        this.fullName = fullName;
        this.id = id;
        this.checked = checked;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}
