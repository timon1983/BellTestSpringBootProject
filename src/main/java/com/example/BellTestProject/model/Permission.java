package com.example.BellTestProject.model;

public enum Permission {

    ORGANIZATIONS_READ("organizations:read"),
    ORGANIZATIONS_WRITE("organizations:write"),
    OFFICES_READ("offices:read"),
    OFFICES_WRITE("offices:write"),
    USERS_READ("users:read"),
    USERS_WRITE("users:write"),
    DOCUMENTS_READ("documents:read"),
    COUNTRIES_READ("countries:read");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
