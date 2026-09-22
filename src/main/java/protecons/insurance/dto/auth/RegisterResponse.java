package protecons.insurance.dto.auth;

import protecons.insurance.constant.Roles;

public class RegisterResponse {
    private String userId;

    private String message;

    private String email;

    private String role;

    public RegisterResponse() {
    }

    public RegisterResponse(
            String userId,
            String message,
            String email,
            String role) {

        this.userId = userId;
        this.message = message;
        this.email = email;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
