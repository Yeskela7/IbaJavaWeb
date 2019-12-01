package servlets;

import calc.Calc;
import calc.CalcHistory;
import cookies.UserCookies;

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

public class CalcServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path path = Paths.get("./content/calc.html");
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
        String num1 = req.getParameter("num_one");
        String num2 = req.getParameter("num_two");
        String op = req.getParameter("operator");

        String res = Calc.calc(num1, num2, op);
        String userId = String.valueOf(UserCookies.getIdFromCookies(req));
        try (PrintWriter printWriter = resp.getWriter()) {
            printWriter.printf("%s %s %s = %s\n", num1, op, num2, res);
            CalcHistory.save(userId, num1, num2, op, res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
