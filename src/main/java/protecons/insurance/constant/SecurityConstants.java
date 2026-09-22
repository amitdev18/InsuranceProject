package protecons.insurance.constant;


public final class SecurityConstants {

    private SecurityConstants() {
    }

    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String BEARER_PREFIX = "Bearer ";

    public static final long JWT_EXPIRATION_MS = 3600000L;

    public static final String LOGIN_ENDPOINT = "/api/v1/auth/login";

    public static final String REGISTER_ENDPOINT = "/api/v1/auth/register";
}