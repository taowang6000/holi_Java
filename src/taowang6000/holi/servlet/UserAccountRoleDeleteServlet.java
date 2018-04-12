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

import taowang6000.holi.domain.Role;
import taowang6000.holi.domain.UserAccount;
import taowang6000.holi.utils.DBUtils;

/**
 * Servlet implementation class UserAccountRoleDeleteServlet, delete a role from an existing user account
 */
@WebServlet("/userAccountRoleDelete")
public class UserAccountRoleDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UserAccountRoleDeleteServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roleId = (String) request.getParameter("id_role");
		String accountId = (String) request.getParameter("id_account");
		
		Role role = null;
		try {
			role = DBUtils.findRole(request, roleId);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		UserAccount account = null;
		
		try {
			account = DBUtils.findAccount(request, accountId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("role", role);
		request.setAttribute("account", account);
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/userrole/userAccountRoleDeleteView.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountId = (String) request.getParameter("accountId_todelete");
		String roleId = (String) request.getParameter("roleId_todelete");
		String errorString = null;

		Role role = null;
		try {
			role = DBUtils.findRole(request, roleId);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		
		List<UserAccount> theList = role.getUserAccounts();
		Iterator<UserAccount> iter = theList.iterator();
		while (iter.hasNext()) {
			UserAccount userAccount = iter.next();
			if (userAccount.getUserId().equals(accountId)) {
				iter.remove();
				break;
			}
		}
		
		try {
			DBUtils.updateUserRole(request, roleId, theList);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// Store information to request attribute, before forward to views.
		request.setAttribute("errorString", errorString);

		// Redirect to the role listing page.
		response.sendRedirect(request.getContextPath() + "/role");
	}

}
