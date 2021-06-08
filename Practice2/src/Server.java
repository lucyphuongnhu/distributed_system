import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class Server{
    public static void main(String[] args) {
        Connection conn = null;
        //List<Student> students = new ArrayList<Student>();
    
        try {
            //Connect to Database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/distributed_system", "newuser", "password");

            //SELECT data from studentDB1
            System.out.println("--- studentDB1 ---");
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM studentDB1");
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    float grade = rs.getFloat(3);
                    System.out.println("ID: " + id);
                    System.out.println("Name: " + name);
                    System.out.println("Grade: " + grade);
                }
            } 
            catch (SQLException e ) {
                throw new Error("Problem", e);
            } 

            //SELECT data from studentDB2
            System.out.println("--- studentDB2 ---");
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM studentDB2");
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    float grade = rs.getFloat(3);
                    System.out.println("ID: " + id);
                    System.out.println("Name: " + name);
                    System.out.println("Grade: " + grade);
                }
            } 
            catch (SQLException e ) {
                throw new Error("Problem", e);
            } 

        }
        catch (SQLException | ClassNotFoundException e) {
            throw new Error("Problem", e);
        } 
    }
}