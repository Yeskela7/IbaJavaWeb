package servlets;

import auth.Auth;
import cookies.UserCookies;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoginServlet extends HttpServlet {
    private final Auth auth;

    public LoginServlet(Auth auth) {
        this.auth = auth;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Path path = Paths.get("./content/login.html");
        ServletOutputStream os;
        try {
            os = resp.getOutputStream();
            Files.copy(path, os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("user_name");
        String password = req.getParameter("user_pass");

        boolean checked = auth.check(login, password);

        try (PrintWriter printWriter = resp.getWriter()) {
            printWriter.printf("user: %s %s\n", login,
                    checked ? "logged in successfully" : "login failed");
            int uid = UserCookies.getIdfromDb(login, password);
            Cookie c = new Cookie("%Cookies%", String.valueOf(uid));
            c.setPath("/");
            c.setMaxAge(-1);
            resp.addCookie(c);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
