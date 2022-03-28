package pl.dev.httyd.httydplugins.database;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private final Dotenv dotenv = Dotenv.load();
    private final String DBURL = dotenv.get("DBURL");
    private final String DBUSER = dotenv.get("DBUSER");
    private final String DBPASS = dotenv.get("DBPASS");
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
