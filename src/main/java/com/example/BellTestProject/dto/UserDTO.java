package com.example.BellTestProject.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Схема пользователя")
public class UserDTO {

    @Schema(description = "идентификатор пользователя", example = "1")
    private int id;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String firstName;
    private String position;
    private int phone;
    private boolean isIdentified;
    private int officeId;

    public UserDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public boolean isIdentified() {
        return isIdentified;
    }

    public void setIdentified(boolean identified) {
        isIdentified = identified;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }
}
