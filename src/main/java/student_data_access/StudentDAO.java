package student_data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import student_model.Student;

import java.util.ArrayList;

import com.google.gson.Gson;

public class StudentDAO {

	public StudentDAO() {

		try {
			Class.forName(MyConnectionParameters.jdbcDriver);

		} catch (ClassNotFoundException cnfe) {
			cnfe.getMessage();
		}

	}

	private Connection openConnection() throws SQLException {

		return DriverManager.getConnection(MyConnectionParameters.connectionString, MyConnectionParameters.username,
				MyConnectionParameters.password);

	}

	public List<Student> getAllStudents() {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultset = null;
		List<Student> students = null;

		try {

			connection = openConnection();
			String sqlText = "SELECT id, firstname, lastname, streetaddress, postcode, postoffice FROM Student";
			ps = connection.prepareStatement(sqlText);
			resultset = ps.executeQuery();

			students = new ArrayList<>();
			while (resultset.next()) {

				int id = resultset.getInt("id");
				String firstName = resultset.getString("firstname");
				String lastName = resultset.getString("lastname");
				String streetAddress = resultset.getString("streetaddress");
				String postCode = resultset.getString("postcode");
				String postOffice = resultset.getString("postoffice");

				students.add(new Student(id, firstName, lastName, streetAddress, postCode, postOffice));

			}

		} catch (SQLException sqle) {
			System.out.println(sqle.getErrorCode() + ", " + sqle.getMessage());
		} finally {
			myDbUtils.closeQuietly(resultset, ps, connection);
		}

		return students;
	}

	public Student getStudentByID(int studentId) {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultset = null;

		Student student = null;

		try {
			connection = openConnection();
			String sqlText = "SELECT id, firstname, lastname, streetaddress, postcode, postoffice FROM Student WHERE id = ?";

			ps = connection.prepareStatement(sqlText);

			ps.setInt(1, studentId);

			resultset = ps.executeQuery();

			while (resultset.next()) {

				int id = resultset.getInt("id");
				String firstName = resultset.getString("firstname");
				String lastName = resultset.getString("lastname");
				String streetAddress = resultset.getString("streetaddress");
				String postCode = resultset.getString("postcode");
				String postOffice = resultset.getString("postoffice");

				student = new Student(id, firstName, lastName, streetAddress, postCode, postOffice);

			}

		} catch (SQLException sqle) {

			System.out.println("[ERROR] StudentDAO: getStudentByID() failed. " + sqle.getMessage() + "\n");

		} finally {

			myDbUtils.closeQuietly(resultset, ps, connection);
		}

		return student;
	}

	public int insertStudent(Student student) {

		int returnCode = -1;

		Connection co = null;
		PreparedStatement ps = null;

		try {

			co = openConnection();
			String sqlText = "INSERT INTO Student(id, firstname, lastname, streetaddress, postcode, postoffice) VALUES (?, ?, ?, ?, ?, ?)";

			ps = co.prepareStatement(sqlText);
			ps.setInt(1, student.getId());
			ps.setString(2, student.getFirstname());
			ps.setString(3, student.getLastname());
			ps.setString(4, student.getStreetaddress());
			ps.setString(5, student.getPostcode());
			ps.setString(6, student.getPostoffice());

			System.out.println(student);

			ps.executeUpdate();
			returnCode = 0;

		} catch (SQLException sqle) {
			if (sqle.getErrorCode() == MyConnectionParameters.PK_VIOLATION_ERROR) {
				returnCode = 1;
			} else {
				System.out.println("\n[ERROR] StudentDAO: insertStudent() failed. " + sqle.getMessage() + "\n");
				returnCode = -1;
			}

		} finally {

			myDbUtils.closeQuietly(ps, co);
		}

		return returnCode;

	}

	public int updateStudent(Student student) {

		Connection co = null;
		PreparedStatement ps = null;
		int updateRow = -1;
		int returnCode = -1;

		try {
			co = openConnection();
			String sqlText = "UPDATE Student SET firstname =?, lastname= ?, streetaddress = ?, postcode = ?, postoffice = ? WHERE id = ?";

			ps = co.prepareStatement(sqlText);

			ps.setString(1, student.getFirstname());
			ps.setString(2, student.getLastname());
			ps.setString(3, student.getStreetaddress());
			ps.setString(4, student.getPostcode());
			ps.setString(5, student.getPostoffice());
			ps.setInt(6, student.getId());

			updateRow = ps.executeUpdate();

			if (updateRow == 1) {
				returnCode = 0;
			} else if (updateRow == 0) {
				returnCode = 1;
			} else {
				returnCode = -1;
			}

		} catch (SQLException e) {

			System.out.println("\n[ERROR] StudentDAO: deleteStudent() failed. " + e.getSQLState() + e.getErrorCode()
					+ e.getMessage());

		} finally {
			myDbUtils.closeQuietly(ps, co);
		}

		return returnCode;
	}

	public int deleteStudent(int studentId) {
		Connection co = null;
		PreparedStatement ps = null;
		int updateRow = -1;
		int returnCode = -1;

		try {
			co = openConnection();
			String deleteSQLText = "DELETE FROM Student WHERE id = ?";

			ps = co.prepareStatement(deleteSQLText);

			ps.setInt(1, studentId);

			updateRow = ps.executeUpdate();

			if (updateRow == 1) {
				returnCode = 0;
			} else if (updateRow == 0) {
				returnCode = 1;
			} else {
				returnCode = -1;
			}

		} catch (SQLException e) {

			System.out.println("\n[ERROR] StudentDAO: deleteStudent() failed. " + e.getSQLState() + e.getErrorCode()
					+ e.getMessage());

		} finally {
			myDbUtils.closeQuietly(ps, co);
		}

		return returnCode;
	}

	public String getAllStudentsJSON() {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultset = null;

		List<Student> studentList = null;
		String studentJson = "";

		try {
			connection = openConnection();
			String sqlText = "SELECT id, firstname, lastname, streetaddress, postcode, postoffice FROM Student ORDER BY id";

			ps = connection.prepareStatement(sqlText);
			resultset = ps.executeQuery();

			studentList = new ArrayList<>();

			while (resultset.next()) {

				int id = resultset.getInt("id");
				String firstName = resultset.getString("firstname");
				String lastName = resultset.getString("lastname");
				String streetAddress = resultset.getString("streetaddress");
				String postCode = resultset.getString("postcode");
				String postOffice = resultset.getString("postoffice");

				studentList.add(new Student(id, firstName, lastName, streetAddress, postCode, postOffice));

			}
			Gson gson = new Gson();
			studentJson = gson.toJson(studentList);

		} catch (SQLException sqle) {
			System.out.println(sqle.getErrorCode() + ", " + sqle.getMessage());
		} finally {
			myDbUtils.closeQuietly(resultset, ps, connection);
		}

		return studentJson;
	}

}
