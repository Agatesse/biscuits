package com.agathevaisse.biscuits.authentication.model;

public enum RoleName {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private String roleNameString;

    RoleName(String roleNameString) {
        this.roleNameString = roleNameString;
    }

    public String getRoleNameString() {
        return roleNameString;
    }
}



