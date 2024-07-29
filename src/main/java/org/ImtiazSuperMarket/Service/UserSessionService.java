package org.ImtiazSuperMarket.Service;

import lombok.Getter;
import lombok.Setter;

public class UserSessionService {
    private static UserSessionService instance;
    @Getter
    @Setter
    private String username;
    private boolean isAuthenticated;

    private UserSessionService() {
        // private constructor to prevent instantiation
    }

    public static UserSessionService getInstance() {
        if (instance == null) {
            instance = new UserSessionService();
        }
        return instance;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public void clearSession() {
        this.username = null;
        this.isAuthenticated = false;
    }
}

