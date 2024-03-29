package taowang6000.holi.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import taowang6000.holi.domain.UserAccount;
import taowang6000.holi.utils.DBUtils;

/**
 * Servlet implementation class UserAccountDeleteServlet
 */
@WebServlet("/userAccountDelete")
public class UserAccountDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UserAccountDeleteServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountId = (String) request.getParameter("id");
		UserAccount account = null;
		try {
			account = DBUtils.findAccount(request, accountId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("account", account);
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/useraccount/userAccountDeleteView.jsp");
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountId = (String) request.getParameter("accountId_todelete");
		String errorString = null;

		try {
			DBUtils.deleteAccount(request, accountId);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// Store information to request attribute, before forward to views.
		request.setAttribute("errorString", errorString);

		// Redirect to the item listing page.
		response.sendRedirect(request.getContextPath() + "/userAccount");
	}

}
