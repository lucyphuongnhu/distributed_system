import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GradeManagement {
	// save a student to student table
	public static void saveStudent() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/distributedsystem",
                    "newuser", "password");

			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO student VALUES (?,?,?)");
			pstmt.setInt(1, 123);
			pstmt.setString(2, "Nhu");
			pstmt.setDouble(3, 2.0);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	// get all the students from the student table
	// connection pool
	public static List<Student> getStudents() {
		List<Student> students = new ArrayList<Student>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/distributedsystem",
                    "newuser", "password");

			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM student");

			while (rs.next()) {
				students.add(new Student(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return students;
	}

	// get a student by id
	//  make sure student(id) ... exist in this table
	public static Student getStudentById(int id) {
		Student result = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/distributedsystem",
                    "newuser", "password");

			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM student WHERE id=" + id);
			while (rs.next()) {
				result = new Student(rs.getInt(1), rs.getString(2), rs.getDouble(3));
				System.out.println(result);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static void main(String[] args) {
		saveStudent();
		getStudentById(123);

	}
}

