package org.merariway.saleswarehouse.security;

public enum RolesEnum {
    ROLE_ADMIN(2),
    ROLE_CUSTOMER(1);

    private final int id;

    RolesEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
