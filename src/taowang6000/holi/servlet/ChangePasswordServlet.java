package taowang6000.holi.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import taowang6000.holi.domain.AdminUser;
import taowang6000.holi.utils.DBUtils;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangePasswordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/auth/adminUserChangePasswordView.jsp");

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adminName = (String) request.getParameter("name");
		String oldPassword = (String) request.getParameter("password");
		String password1 = (String) request.getParameter("password1");
		String password2 = (String) request.getParameter("password2");
		
		String errorString = null;
		String email = null;

		if (!(password1.equals(password2))) {
			errorString = "Passwords mismatch!";
		} else {
			// validate the username + old password
			AdminUser adminUser = null;
			try {
				adminUser = DBUtils.findAdmin(request, adminName, oldPassword);
			} catch (Exception e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
			email = adminUser.getEmail();
		}

		if (errorString == null) {		
			try {
				DBUtils.updateAdminUser(request, adminName, email, password1);
			} catch (Exception e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
		}

		// Store infomation to request attribute, before forward to views.
		request.setAttribute("errorString", errorString);

		// If error, forward to Edit page.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/auth/adminUserChangePassowrdView.jsp");
			dispatcher.forward(request, response);
		}
		// If everything nice.
		// Redirect to the admin-user listing page.
		else {
			response.sendRedirect(request.getContextPath() + "/home");
		}
	}

}
