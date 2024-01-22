package servlet_exercises;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import student_model.Student;

/**
 * Servlet implementation class JsonServlet
 */
@WebServlet("/jsontest")
public class JsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");

		List<Student> students = new ArrayList<>();

		students.add(new Student(19, "James", "Bond", "Merinnekatu 99", "00245", "Helsinki"));
		students.add(new Student(23, "Vejo", "Herlinen", "Aleksenkatu 2", "21540", "Tampere"));

		Gson gson = new Gson();
		String json = gson.toJson(students);

		out.println(json);

	}

}
