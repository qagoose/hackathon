package com.qagoose.hackathon.traditional.builders;

import java.util.ArrayList;

public class LogInAction {
    private String username;
    private String password;
    private Boolean assertSuccess;
    private ArrayList<String> errorMessages;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getAssertSuccess() {
        return assertSuccess;
    }

    public ArrayList<String> getErrorMessages() {
        return errorMessages;
    }

    private LogInAction(LogInActionBuilder builder) {
        username = builder.username;
        password = builder.password;
        assertSuccess = builder.assertSuccess;
        errorMessages = builder.errorMessages;
    }

    public static Step builder() {
        return new LogInActionBuilder();
    }

    private static class LogInActionBuilder implements Step {
        private String username;
        private String password;
        private Boolean assertSuccess;
        private ArrayList<String> errorMessages = new ArrayList<>();

        @Override
        public Step withUsername(String username) {
            this.username = username;
            return this;
        }

        @Override
        public Step withPassword(String password) {
            this.password = password;
            return this;
        }

        @Override
        public Step assertDashboardLoads() {
            this.assertSuccess = true;
            return this;
        }

        @Override
        public Step assertErrorPresent(String errorMessage) {
            errorMessages.add(errorMessage);
            return this;
        }

        @Override
        public LogInAction build() {
            return new LogInAction(this);
        }
    }

    public interface Step {
        Step withUsername(String username);
        Step withPassword(String password);
        Step assertDashboardLoads();
        Step assertErrorPresent(String errorMessage);
        LogInAction build();
    }

}
