package com.example.BellTestProject.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {

    USER(Set.of(Permission.ORGANIZATIONS_READ , Permission.OFFICES_READ , Permission.USERS_READ , Permission.DOCUMENTS_READ , Permission.COUNTRIES_READ)),
    MODERATOR(Set.of(Permission.ORGANIZATIONS_WRITE , Permission.OFFICES_WRITE , Permission.USERS_WRITE)),
    ADMIN(Set.of(Permission.ORGANIZATIONS_READ , Permission.OFFICES_READ , Permission.USERS_READ ,
            Permission.ORGANIZATIONS_WRITE , Permission.OFFICES_WRITE , Permission.USERS_WRITE , Permission.DOCUMENTS_READ , Permission.COUNTRIES_READ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
