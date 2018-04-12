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
 * Servlet implementation class UserAccountEditServlet
 */
@WebServlet("/userAccountEdit")
public class UserAccountEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UserAccountEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountId = (String) request.getParameter("id");
		String errorString = null;
		UserAccount account = null;
		
		try {
			account = DBUtils.findAccount(request, accountId);
		} catch (Exception e) {
			errorString = e.getMessage();
			e.printStackTrace();
		}
		

		// Same entrance parameter name as in CheckoutOrderAddServlet.doPost()
		request.setAttribute("roleList", account.getRoles());
		request.setAttribute("orderList", account.getCheckoutOrders());
		request.setAttribute("account", account);
		request.setAttribute("errorString", errorString);

		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/useraccount/userAccountEditView.jsp");

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountId = (String) request.getParameter("account_id");
		String contactNo = (String) request.getParameter("contact_no");
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		String userName = (String) request.getParameter("user_name");
	
		String errorString = null;
		UserAccount this_account = null;
		try {
			this_account = DBUtils.findAccount(request, accountId);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		
		/*
		 * ----------- update the User Account, and forward to corresponding page
		 * not touching the order list belonging the account
		 */

		if (errorString == null) {
			try {
				DBUtils.updateAccount(request, accountId, contactNo, email, password, userName);
			} catch (Exception e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
		}

		if (errorString != null) {
			
			request.setAttribute("roleList", this_account.getRoles());
			request.setAttribute("account", this_account);
			request.setAttribute("orderList", this_account.getCheckoutOrders());
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/useraccount/userAccountEditView.jsp");
			dispatcher.forward(request, response);
		} else {
			if (request.getParameter("_save") != null) {
				response.sendRedirect(request.getContextPath() + "/userAccount");
			} else if (request.getParameter("_addanother") != null) {
				response.sendRedirect(request.getContextPath() + "/userAccountAdd");
			} else if (request.getParameter("_continue") != null) {
				
				request.setAttribute("roleList", this_account.getRoles());
				request.setAttribute("account", this_account);
				request.setAttribute("orderList", this_account.getCheckoutOrders());
				request.setAttribute("errorString", errorString);
				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/WEB-INF/views/checkoutOrder/checkoutOrderEditView.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
