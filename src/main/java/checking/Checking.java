package checking;

import jdbc.DbConnection;

import javax.servlet.ServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Checking {

    public static boolean checkRegister(ServletRequest req) {
        String login = req.getParameter("user_name");
        String password = req.getParameter("user_pass");

        Connection conn = DbConnection.getConnection();
        String SQLO = "SELECT user_name FROM users";
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
            return false;
        }
        return true;
    }

}
