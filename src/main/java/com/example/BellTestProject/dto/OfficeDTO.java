package com.example.BellTestProject.dto;

public class OfficeDTO {

    private int id;
    private String name;
    private String address;
    private int phone;
    private boolean isActive;
    private int orgId;

    public OfficeDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Model{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", isActive=" + isActive +
                ", orgId=" + orgId +
                '}';
    }
}
