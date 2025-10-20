package org.merariway.saleswarehouse.security;

public class EndPointSecurity {

    public static final String[] OFFLINE_ACCESS = {
            "/auth/register-email",
            "/test/generate/**"
    };

    public static final String[] ADMIN_ACCESS = {
            "/auth/adm/register-admin"
    };
}
