package servlet_exercises;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student_data_access.StudentDAO;
import student_model.Status;

import com.google.gson.*;

/**
 * Servlet implementation class StudentDeleteServlet
 */
@WebServlet("/deleteStudent")
public class StudentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		StudentDAO studentDAO = new StudentDAO();
		int delStudentId = Integer.parseInt(request.getParameter("id"));
		int returnCode = studentDAO.deleteStudent(delStudentId);
		
		Status status = new Status(returnCode);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(status);
		
		out.println(jsonString);
	}

}
