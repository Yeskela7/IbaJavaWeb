package calc;

import jdbc.DbConnection;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CalcHistory {

    public static void save(String userId, String num1, String num2, String op, String res) {
        Connection conn = DbConnection.getConnection();
        String SQLI = "INSERT INTO history (userid, num_one, num_two, " +
                "operator, result) VALUES (?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(SQLI);
            ps.setInt(1, Integer.parseInt(userId));
            ps.setInt(2, Integer.parseInt(num1));
            ps.setInt(3, Integer.parseInt(num2));
            ps.setString(4, String.valueOf(op));
            ps.setInt(5, Integer.parseInt(res));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void loadById(PrintWriter writer, int uid) {
        Connection conn = DbConnection.getConnection();
        String s = Integer.toString(uid);
        String SQLO = "SELECT * FROM history WHERE userid = " + s;
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(SQLO);
            ResultSet rset = ps.executeQuery();
            while (rset.next()) {
                String line = String.format("%d %s %d = %d",
                        rset.getInt("num_one"),
                        rset.getString("operator"),
                        rset.getInt("num_two"),
                        rset.getInt("result"));
                writer.println(line);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
