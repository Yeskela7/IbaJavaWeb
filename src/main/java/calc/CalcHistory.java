package calc;

import jdbc.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CalcHistory {

    public static void save(String num1, String num2, String op, String res, String userId) {
        Connection conn = DbConnection.getConnection();

        String SQLI = "INSERT INTO users (userid, num_one, num_two, " +
                "String operator, result) VALUES (?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(SQLI);
            ps.setInt(1, Integer.parseInt(userId));
            ps.setInt(2, Integer.parseInt(num1));
            ps.setInt(3, Integer.parseInt(num2));
            ps.setString(4, op);
            ps.setInt(5, Integer.parseInt(res));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
