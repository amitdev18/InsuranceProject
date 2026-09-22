package protecons.insurance.dto.auth;

import protecons.insurance.constant.Roles;

public class LoginResponse {

    private String token;

    private String tokenType;

    private long expiresIn;

    private String userId;

    private String role;

    public LoginResponse() {
    }

    public LoginResponse(
            String token,
            String tokenType,
            long expiresIn,
            String userId,
            String role) {

        this.token = token;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.userId = userId;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

}
