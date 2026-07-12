package com.shopkart.data.secret;

import com.shopkart.config.AppConfig;

public final class Secrets {

    private Secrets() {
    }

    public static String email(String user) {
        return AppConfig.get(user + ".email");
    }

    public static String password(String user) {

        // 1. Prefer environment variable
        String envPassword = System.getenv(user.toUpperCase() + "_PASSWORD");

        if (envPassword != null && !envPassword.isBlank()) {
            return envPassword;
        }

        // 2. Fall back to local properties
        return AppConfig.get(user + ".password");
    }
}