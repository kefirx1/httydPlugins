package pl.dev.httyd.httydplugins.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private final static String DBURL = System.getenv("DBURL");
    private final static String DBUSER = System.getenv("DBUSER");
    private final static String DBPASS = System.getenv("DBPASS");
    Connection connection;
    Statement statement;

    public Statement getStatementDB(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            return  connection.createStatement();
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return statement = null;
    }


}
