package taowang6000.holi.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import taowang6000.holi.utils.DBUtils;

@WebServlet("/adminUserDelete")
public class AdminUserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminUserDeleteServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = (String) request.getParameter("username");

		request.setAttribute("name", name);

		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/auth/adminUserDeleteView.jsp");

		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String) request.getParameter("name_todelete");
		
		String errorString = null;

		try {
			DBUtils.deleteAdminUser(request, username);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// Store infomation to request attribute, before forward to views.
		request.setAttribute("errorString", errorString);

		// If error, forward to Edit page.
		if (errorString != null) {		
			request.setAttribute("name", username);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/auth/adminUserView.jsp");
			dispatcher.forward(request, response);
		}
		// If everything nice.
		// Redirect to the admin-user listing page.
		else {
			response.sendRedirect(request.getContextPath() + "/adminUser");
		}
	}

}
