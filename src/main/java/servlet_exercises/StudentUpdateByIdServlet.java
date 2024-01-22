package servlet_exercises;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import com.google.gson.Gson;

import student_data_access.StudentDAO;
import student_model.Student;
/**
 * Servlet implementation class StudentUpdateByIdServlet
 */
@WebServlet("/StudentUpdateByIdServlet")
public class StudentUpdateByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		 response.setContentType("application/json"); 
		 response.setCharacterEncoding("UTF-8");
		 
		 int StudentId = Integer.parseInt(request.getParameter("studentId")); 
		 
		 StudentDAO studentDao = new StudentDAO();
		 Student student = studentDao.getStudentByID(StudentId);
		 
		 Gson gson = new Gson();
		 String json = gson.toJson(student);
		 out.print(json);
		 
		
		
	}

}
