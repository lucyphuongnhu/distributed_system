import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class Server{
    //Select all students from the database and add it to the student list
    public static void getStudentList(Connection conn, String query, List<Student> studentList){
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                studentList.add(new Student(rs.getInt(1), rs.getString(2), rs.getFloat(3)));
            }
            System.out.println(studentList + "\n");
        } 
        catch (SQLException e ) {
            throw new Error("Problem", e);
        } 
    }

    //Add students to synchronize to database
    public static void addToStudentList(Connection conn, String query, Student student){
        try{
            PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, student.getId());
			pstmt.setString(2, student.getName());
			pstmt.setFloat(3, student.getGrade());
			pstmt.executeUpdate();
        }
        catch (SQLException e ){
            throw new Error("Problem", e);
        }
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        List<Student> studentDB1List = new ArrayList<Student>();
        List<Student> studentDB2List = new ArrayList<Student>();
    
        try{
            //CONNECT to Database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/distributed_system", "newuser", "password");

            //SELECT data from studentDB1
            System.out.println("--- studentDB1 ---");
            String query = "SELECT * FROM studentDB1";
            getStudentList(conn, query, studentDB1List);

            //SELECT data from studentDB2
            System.out.println("--- studentDB2 ---");
            query = "SELECT * FROM studentDB2";
            getStudentList(conn, query, studentDB2List);

            //COMPARE 2 databases
            System.out.println("Compare studentDB1 to studentDB2");
            for (int i = 0; i < studentDB1List.size(); i++){
                if (studentDB2List.get(i).getId() == studentDB1List.get(i).getId()){
                    System.out.println("Database is up to date");
                }
                else{
                    query = "INSERT INTO studentDB2 VALUES(?,?,?)";
                    addToStudentList(conn, query, studentDB1List.get(i));
                    System.out.println(studentDB1List.get(i));
                }
            }

            System.out.println("Compare studentDB2 to studentDB1");
            for (int i = 0; i < studentDB2List.size(); i++){
                if (studentDB2List.get(i).getId() == studentDB1List.get(i).getId()){
                    System.out.println("Database is up to date");
                }
                else{
                    query = "INSERT INTO studentDB1 VALUES(?,?,?)";
                    addToStudentList(conn, query, studentDB2List.get(i));
                    System.out.println(studentDB2List.get(i));
                }
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            throw new Error("Problem", e);
        } 
    }
}