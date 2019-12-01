package register;

import cryptography.Ciphering;
import jdbc.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register {

    public static void register(String login, String password){
        Connection conn = DbConnection.getConnection();
        final String SQLI = "INSERT INTO users (user_name, password) VALUE (?,?)";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(SQLI);
            ps.setString(1, login);
            ps.setInt(2, Ciphering.passwordCrypt(password));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
