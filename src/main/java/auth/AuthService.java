package auth;

import cryptography.Ciphering;
import jdbc.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService implements Auth {

    public boolean check(String login, String pass) {
        Connection conn = DbConnection.getConnection();
        final String SQLO = "SELECT * FROM users";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(SQLO);
            ResultSet rset = ps.executeQuery();
            while (rset.next()) {
                String name = rset.getString("user_name");
                int pswd = rset.getInt("password");
                if (login.equals(name) && Ciphering.passwordCrypt(pass) == pswd) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
