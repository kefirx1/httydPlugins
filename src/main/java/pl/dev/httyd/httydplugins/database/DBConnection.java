package pl.dev.httyd.httydplugins.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private final static String DBURL = "jdbc:mysql://mysql.craftserve.pl:3306/csrv_976656";
    private final static String DBUSER = "csrv_976656";
    private final static String DBPASS = "ae2fdb74b8bc34f30697";


    public Statement getStatementDB(){
        Statement statement;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            return  connection.createStatement();
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return statement = null;
    }


}