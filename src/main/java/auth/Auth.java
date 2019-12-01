package auth;

public interface Auth {
    boolean check(String login, String pass);
}
