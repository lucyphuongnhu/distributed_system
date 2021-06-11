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
            System.out.println(studentList);
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
    
        try {
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

            /** ERROR
             * COMPARE 2 databases
            System.out.println("--- Compare 2 databases ---");            
            for (int i = 0; i < studentDB1List.size(); i++){
                for (int j = 0; j < studentDB2List.size(); j++){
                    if (!studentDB1List.get(i).equals(studentDB2List.get(j))){
                        query = "INSERT INTO studentDB2 VALUES (?,?,?)";
                        addToStudentList(conn, query, studentDB1List.get(j));
                    }
                    else if (!studentDB2List.get(i).equals(studentDB1List.get(j))){
                        query = "INSERT INTO studentDB1 VALUES (?,?,?)";
                        addToStudentList(conn, query, studentDB2List.get(j));
                    }
                    else{
                        System.out.println("It is already up to date");
                    }
                }
            } 
            
            //SELECT data from studentDB1
            System.out.println("--- studentDB1 after synchronization ---");
            query = "SELECT * FROM studentDB1";
            getStudentList(conn, query, studentDB1List);

            //SELECT data from studentDB2
            System.out.println("--- studentDB2 after synchronization ---");
            query = "SELECT * FROM studentDB2";
            getStudentList(conn, query, studentDB2List);*/
        }
        catch (SQLException | ClassNotFoundException e) {
            throw new Error("Problem", e);
        } 
        

    }
}