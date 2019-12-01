package servlets;

import register.Register;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path path = Paths.get("./content/register.html");
        ServletOutputStream os = resp.getOutputStream();
        Files.copy(path, os);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("user_name");
        String password = req.getParameter("user_pass");

        try (PrintWriter printWriter = resp.getWriter()) {
            Register.register(login, password);
            printWriter.printf("Welcome : %s \n", login);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
