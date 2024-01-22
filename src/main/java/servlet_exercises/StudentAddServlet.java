package servlet_exercises;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import student_data_access.StudentDAO;
import student_model.Student;
import student_model.Status;
/**
 * Servlet implementation class StudentAddServlet
 */
@WebServlet("/addStudent")
public class StudentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		Student student = new Student(Integer.parseInt(request.getParameter("id")),request.getParameter("firstname"),request.getParameter("lastname"), 
				request.getParameter("streetaddress"), request.getParameter("postcode"), request.getParameter("postoffice"));
		
		StudentDAO newStudent = new StudentDAO();
		int errorCode = newStudent.insertStudent(student);
		
		Status errorStatus = new Status(errorCode);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(errorStatus);
		
		out.println(jsonString);
		
	}

}
