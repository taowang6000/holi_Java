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
 * Servlet implementation class UserAccountAddServlet
 */
@WebServlet("/userAccountAdd")
public class UserAccountAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserAccountAddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String errorString = null;
		List<Role> list = null;
		try {
			list = DBUtils.queryRoleAll(request);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// Store info in request attribute, before forward to views
		request.setAttribute("errorString", errorString);
		request.setAttribute("roleList", list);

		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/useraccount/userAccountAddView.jsp");

		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String contactNo = (String) request.getParameter("contact_no");
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		String userName = (String) request.getParameter("user_name");
		//String roleId = (String) request.getParameter("id_role");

		String errorString = null;

		List<UserAccount> list = null;
		try {
			list = DBUtils.queryAccountAll(request);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		/*----------------determine the userId of Account ----------------------------*/

		Integer accountIdInt = 0;
		UserAccount last_account = null;

		if (list == null) {
			accountIdInt = 1;
		} else if (list.size() == 1) {
			// Get the only element of the list
			Iterator<UserAccount> iter = list.iterator();

			last_account = iter.next();
			String str_temp = last_account.getUserId();
			if (str_temp != null) {
				accountIdInt = Integer.valueOf(str_temp) + 1;
			} else {
				errorString = "Null Account ID.";
			}
		} else {
			// Get the last element of the list
			last_account = list.get(list.size() - 1);
			String str_temp1 = last_account.getUserId();
			if (str_temp1 != null) {
				accountIdInt = Integer.valueOf(str_temp1) + 1;
			} else {
				errorString = "Null Account ID.";
			}
		}

		/* -------- determine the User Role --------------------------- 
		Role theRole = null;
		try {
			theRole = DBUtils.findRole(request, roleId);
			if (theRole == null) {
				if (roleId == null) {
					errorString = "Role ID null";
				} else {
					errorString = "Role not found.";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		*/

		/* ---------------create the account to be added ---------------------- */
		UserAccount new_account = new UserAccount();
		new_account.setUserId(String.valueOf(accountIdInt));
		new_account.setContactNo(contactNo);
		new_account.setEmail(email);
		new_account.setPassword(password);
		new_account.setUserName(userName);
		
		/* ---------------update from role (from owner side)-----------------------*/
		

		/*
		 * ----------- add the account, and forward to corresponding page ----------------
		 */

		try {
			DBUtils.addAccount(request, new_account);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		if (errorString != null) {
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/useraccount/userAccountAddView.jsp");
			dispatcher.forward(request, response);
		} else {
			if (request.getParameter("_save") != null) {
				response.sendRedirect(request.getContextPath() + "/userAccount");
			} else if (request.getParameter("_addanother") != null) {
				response.sendRedirect(request.getContextPath() + "/userAccountAdd");
			} else if (request.getParameter("_continue") != null) {
				request.setAttribute("orderList", new_account.getCheckoutOrders());
				request.setAttribute("account", new_account);
				request.setAttribute("errorString", errorString);
				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/WEB-INF/views/useraccount/userAccountEditView.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
