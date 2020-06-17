import java.sql.*;
        
public class DBConnection {
    private static Connection connection;
    private static final String URL = "jdbc:derby://localhost:1527/Library";
    private static final String username = "library";
    private static final String password = "password";
    
    public static Connection getConnection(){
        if (connection == null){
            try{
                connection = DriverManager.getConnection(URL,username,password);
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return connection;
    }
}
