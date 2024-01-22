package servlet_exercises;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculatorServlet
 */
@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		int x = Integer.parseInt(request.getParameter("x"));
		int y = Integer.parseInt(request.getParameter("y"));
		int z = 0;
		String output = "";

		String operation = request.getParameter("operation");

		switch (operation) {

		case "add":
			operation = "+";
			z = x + y;
			output = Integer.toString(z);
			break;

		case "multiply":
			operation = "*";
			z = x * y;
			output = Integer.toString(z);
			break;

		case "substract":
			operation = "-";
			z = x - y;
			output = Integer.toString(z);
			break;

		case "divide":
			if (y != 0) {
				operation = "/";
				z = x / y;
				output = Integer.toString(z);
			} else {
				operation = "/";
				output = "Error!";
			}

		}

		out.println(x + " " + operation + " " + y + " = " + output);
	}

}
