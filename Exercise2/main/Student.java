package main;

import java.io.Serializable;

public final class Student implements Serializable {
	private int studentID;
	private String studentName;
	private String studentYear;

	Student(int studentID, String studentName, String studentYear) {
		this.studentID = studentID;
		this.studentName = studentName;
		this.studentYear = studentYear;
	}

	public int getStudentID() {
		return studentID;
	}

	public String getStudentName() {
		return studentName;
	}

	public String getStudentYear() {
		return studentYear;
	}
}
