package sample.db;

import sample.common.Result;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class DBConnection {

    public enum Type {REQ, INC}

    private final String JDBC_URL = "jdbc:sqlserver://Provider=SQLNCLI11;Server=REMEDY-SQL;Database=ARSystem;";
    private String updateQueryString = "update ARSystem.dbo.HPD_Help_Desk_Classic set status =4 where ";


    public Result executeQuery(Type type, String num, String user, String password) {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connObj = DriverManager.getConnection(JDBC_URL, user, password);

            if (connObj != null) {
                if (type.equals(Type.REQ)) {
                    updateQueryString += "SRID = ?";
                } else {
                    updateQueryString += "Incident_Number = ?";
                }

                PreparedStatement pstmt = connObj.prepareStatement(updateQueryString);
                pstmt.setString(1, num);
                int modifiedRows = pstmt.executeUpdate();
                return new Result(String.format(Result.OK_MSG, modifiedRows), modifiedRows);
            } else
                return new Result(Result.CONN_ERR, Result.ERR);

        } catch (Exception sqlException) {
            sqlException.printStackTrace();
            return new Result(sqlException.toString(), Result.ERR);
        }
    }
}
