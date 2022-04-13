package ueba;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;



import java.sql.*;
public class Dbconnection implements Factoryinterface{
    Factory factory=new Factory();
    public  Connection connection() throws ClassNotFoundException, SQLException {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/responsedb","root","root");
           return con;
    }
    public  Connection postgresconnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:33337/analytics?&user=postgres&password=Stonebraker");;
        return con;
    }
    public ResultSet getresult(String query) throws SQLException, IOException, ClassNotFoundException {
        Factoryinterface dbcon= (Factoryinterface) factory.getanyconnection("dbconnection");
        Connection conn= (Connection) dbcon.connection();
        Statement stmt;
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }
    public void updatetable(String query) throws SQLException, IOException, ClassNotFoundException {
        Factoryinterface dbcon= (Factoryinterface) factory.getanyconnection("dbconnection");
        Connection conn= (Connection) dbcon.connection();
        PreparedStatement stmtt = conn.prepareStatement(query);
        stmtt.executeUpdate();
    }



}

