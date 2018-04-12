package taowang6000.holi.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import taowang6000.holi.domain.AdminUser;
import taowang6000.holi.utils.DBUtils;

/**
 * Servlet implementation class AdminUserAddServlet
 */
@WebServlet("/adminUserAdd")
public class AdminUserAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUserAddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/auth/adminUserAddView.jsp");

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String) request.getParameter("username");
		String email = (String) request.getParameter("email");
		String password1 = (String) request.getParameter("password1");
		String password2 = (String) request.getParameter("password2");

		String errorString = null;

		if (!(password1.equals(password2))) {
			errorString = "Passwords mismatch!";
		} else {
			// validate the username
			List<AdminUser> list = null;
			try {
				list = DBUtils.queryAdminAll(request);
			} catch (Exception e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
			Iterator<AdminUser> iter = list.iterator();
			while (iter.hasNext()) {
				AdminUser user = iter.next();
				if (user.getUserName().equals(username)) {
					errorString = "User name already existed!";
					break;
				}
			}
		}

		if (errorString == null) {
			AdminUser newuser = new AdminUser(username, email, password1);
			
			try {
				DBUtils.addAdminUser(request, newuser);
			} catch (Exception e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
			request.setAttribute("newuser", newuser);
		}

		// Store infomation to request attribute, before forward to views.
		request.setAttribute("errorString", errorString);

		// If error, forward to Edit page.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/auth/adminUserAddView.jsp");
			dispatcher.forward(request, response);
		}
		// If everything nice.
		// Redirect to the product listing page.
		else {
			response.sendRedirect(request.getContextPath() + "/adminUser");
		}
	}

}
