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
 * Servlet implementation class UserAccountRoleAddServlet, add a role to a existing UserAccount
 */
@WebServlet("/userAccountRoleAdd")
public class UserAccountRoleAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserAccountRoleAddServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roleId = (String) request.getParameter("id");
		Role role = null;
		try {
			role = DBUtils.findRole(request, roleId);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		List<UserAccount> list = null;
		try {
			list = DBUtils.queryAccountAll(request);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("role", role);
		request.setAttribute("accountList", list);
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/userrole/userAccountRoleAddView.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roleId = (String) request.getParameter("id_role");
		String accountId = (String) request.getParameter("id_account");

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
				errorString = "User account already have this role!";
				break;
			}
		}
		
		if (errorString != null) {
			List<UserAccount> list = null;
			try {
				list = DBUtils.queryAccountAll(request);
			} catch (Exception e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}	
			request.setAttribute("role", role);
			request.setAttribute("accountList", list);
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/userrole/userAccountRoleAddView.jsp");
			dispatcher.forward(request, response);
		} else {
			
			UserAccount theAccount = null;
			try {
				theAccount = DBUtils.findAccount(request, accountId);
			} catch (Exception e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}

			theList.add(theAccount);	
			
			try {
				DBUtils.updateUserRole(request, roleId, theList);
			} catch (Exception e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
			if (request.getParameter("_save") != null) {
				response.sendRedirect(request.getContextPath() + "/role");
			} else if (request.getParameter("_addanother") != null) {
				List<UserAccount> list = null;
				try {
					list = DBUtils.queryAccountAll(request);
				} catch (Exception e) {
					e.printStackTrace();
					errorString = e.getMessage();
				}	
				request.setAttribute("role", role);
				request.setAttribute("accountList", list);
				RequestDispatcher dispatcher //
						= this.getServletContext().getRequestDispatcher("/WEB-INF/views/userrole/userAccountRoleAddView.jsp");
				dispatcher.forward(request, response);
			} else if (request.getParameter("_continue") != null) {
				request.setAttribute("accountList", role.getUserAccounts());
				request.setAttribute("role", role);
				request.setAttribute("errorString", errorString);
				RequestDispatcher dispatcher //
						= this.getServletContext().getRequestDispatcher("/WEB-INF/views/role/roleEditView.jsp");
				dispatcher.forward(request, response);
			}
		}

	}

}
