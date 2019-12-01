package cookies;

import cryptography.Ciphering;
import jdbc.DbConnection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCookies {

    public static int getIdfromDb(String login, String pass) {
        Connection conn = DbConnection.getConnection();
        final String SQLO = "SELECT * FROM users";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(SQLO);
            ResultSet rset = ps.executeQuery();
            String name;
            int pswd;
            int id;
            while (rset.next()) {
                id = rset.getInt("id");
                name = rset.getString("user_name");
                pswd = rset.getInt("password");
                if (login.equalsIgnoreCase(name) && Ciphering.passwordCrypt(pass) == pswd) {
                    return id;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int getIdFromCookies(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        int id = -1;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("%Cookies%")) {
                    id = Integer.parseInt(c.getValue());
                }
            }
        }
        return id;
    }
}
