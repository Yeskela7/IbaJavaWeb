package checking;

import jdbc.DbConnection;
import register.Register;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Checking {

    public static boolean checkLogin(ServletRequest req) {
        String login = req.getParameter("user_name");
        String password = req.getParameter("user_pass");

        Connection conn = DbConnection.getConnection();
        String SQLO = "SELECT * FROM users";
        if (login != null && password != null) {
            try {
                PreparedStatement ps = conn.prepareStatement(SQLO);
                ResultSet rset = ps.executeQuery();
                while (rset.next()) {
                    String nick = rset.getString("user_name");
                    if (nick.equals(login)) return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}
