package servlets;

import calc.CalcHistory;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        Cookie[] cookies = req.getCookies();
        int id = -1;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("%Cookies%")) {
                    id = Integer.parseInt(c.getValue());
                }
            }
        }

        CalcHistory.loadById(writer, id);
        writer.close();
    }

}
