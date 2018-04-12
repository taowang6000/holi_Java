package taowang6000.holi.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import taowang6000.holi.domain.CheckoutOrder;
import taowang6000.holi.domain.UserAccount;
import taowang6000.holi.utils.DBUtils;

/**
 * Servlet implementation class CheckoutOrderEditServlet
 */
@WebServlet("/checkoutOrderEdit")
public class CheckoutOrderEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckoutOrderEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderId = (String) request.getParameter("id");
		String errorString = null;
		CheckoutOrder order = null;

		List<UserAccount> list = null;
		try {
			list = DBUtils.queryAccountAll(request);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		if (list == null) {
			errorString = "To add orders, there should be at least one User Account. Please add an account first";
		}

		try {
			order = DBUtils.findOrder(request, orderId);
		} catch (Exception e) {
			errorString = e.getMessage();
			e.printStackTrace();
		}

		// Same entrance parameter name as in CheckoutOrderAddServlet.doPost()
		request.setAttribute("itemList", order.getItems());
		request.setAttribute("accountList", list);
		request.setAttribute("order", order);
		request.setAttribute("theAccount", order.getUserAccount());
		request.setAttribute("errorString", errorString);

		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/checkoutorder/checkoutOrderEditView.jsp");

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderId = (String) request.getParameter("order_id");
		String dateTime = (String) request.getParameter("date_time");
		String accountId = request.getParameter("id_account");
	

		String errorString = null;
		CheckoutOrder this_order = null;
		try {
			this_order = DBUtils.findOrder(request, orderId);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		/* -------- determine the user account --------------------------- */
		UserAccount theAccount = null;
		try {
			theAccount = DBUtils.findAccount(request, accountId);

			if (theAccount == null) {
				if (accountId == null) {
					errorString = "Account ID null";
				} else {
					errorString = "Account not found.";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		/*
		 * ----------- update the order, and forward to corresponding page
		 * not touching the item list belonging the order
		 */

		if (errorString == null) {
			try {
				DBUtils.updateOrder(request, orderId, dateTime, theAccount);
			} catch (Exception e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
		}

		if (errorString != null) {
			List<UserAccount> account_list = null;
			try {
				account_list = DBUtils.queryAccountAll(request);
			} catch (Exception e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}

			if (account_list == null) {
				errorString = "To add orders, there should be at least one User Account. Please add a account first";
			}
			request.setAttribute("accountList", account_list);
			request.setAttribute("order", this_order);
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/checkoutorder/checkoutOrderEditView.jsp");
			dispatcher.forward(request, response);
		} else {
			if (request.getParameter("_save") != null) {
				response.sendRedirect(request.getContextPath() + "/checkoutOrder");
			} else if (request.getParameter("_addanother") != null) {
				response.sendRedirect(request.getContextPath() + "/checkoutOrderAdd");
			} else if (request.getParameter("_continue") != null) {
				List<UserAccount> account_list = null;
				try {
					account_list = DBUtils.queryAccountAll(request);
				} catch (Exception e) {
					e.printStackTrace();
					errorString = e.getMessage();
				}

				if (account_list == null) {
					errorString = "To add orders, there should be at least one User Account. Please add a account first";
				}
				request.setAttribute("accountList", account_list);
				request.setAttribute("order", this_order);
				request.setAttribute("theAccount", this_order.getUserAccount());
				request.setAttribute("errorString", errorString);
				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/WEB-INF/views/checkoutOrder/checkoutOrderEditView.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
