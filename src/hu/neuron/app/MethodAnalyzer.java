package hu.neuron.app;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MethodAnalyzer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(MethodAnalyzer.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MethodAnalyzer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String param = request.getParameter("dataSize");
		// String classNames[] = request.getParameterValues("action[]");
		
		String clazz1 = request.getParameter("className0");
		String clazz2 = request.getParameter("className1");
		String clazz3 = request.getParameter("className2");

		StringWriter stringWriter = new StringWriter();
		BufferedWriter bufferedWriter = new BufferedWriter(stringWriter);

		MethodRuntimeAnalyzer methodRuntimeAnalyzer = new MethodRuntimeAnalyzer(bufferedWriter);
		methodRuntimeAnalyzer.openTableStatistic();

		try {
			methodRuntimeAnalyzer.analize(Class.forName(clazz1), Integer.parseInt(param));
			methodRuntimeAnalyzer.analize(Class.forName(clazz2), Integer.parseInt(param));
			methodRuntimeAnalyzer.analize(Class.forName(clazz3), Integer.parseInt(param));
		} catch (ClassNotFoundException e) {
			logger.log(Level.SEVERE, "Class not Found");
			logger.log(Level.SEVERE, e.getMessage());
		}
		methodRuntimeAnalyzer.closeTableStatistic();

		request.setAttribute("result", stringWriter.toString());
		request.getRequestDispatcher("/result.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
