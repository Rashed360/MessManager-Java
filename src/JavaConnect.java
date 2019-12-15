import java.sql.*;
import javax.swing.JOptionPane;

public class JavaConnect {
    Connection con=null;
    
    public static Connection ConnectDb(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
                Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/messmanager", "root", "123456");
                return conn;               
        }
        catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
        }
        return null;
    }
    
}
