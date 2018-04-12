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

import taowang6000.holi.domain.CheckoutOrder;
import taowang6000.holi.domain.UserAccount;
import taowang6000.holi.utils.DBUtils;

@WebServlet("/checkoutOrderAdd")
public class CheckoutOrderAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckoutOrderAddServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String errorString = null;
		List<UserAccount> list = null;
		try {
			list = DBUtils.queryAccountAll(request);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		if (list == null) {
			errorString = "To add orders, there should be at least one user account. Please add a user account first";
		}
		// Store info in request attribute, before forward to views
		request.setAttribute("errorString", errorString);
		request.setAttribute("accountList", list);

		RequestDispatcher dispatcher //
				= this.getServletContext()
						.getRequestDispatcher("/WEB-INF/views/checkoutorder/checkoutOrderAddView.jsp");

		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dateTime = (String) request.getParameter("date_time");
		String accountId = (String) request.getParameter("id_account");

		String errorString = null;

		List<CheckoutOrder> list = null;
		try {
			list = DBUtils.queryOrderAll(request);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		/*----------------determine the orderId ----------------------------*/

		Integer orderIdInt = 0;
		CheckoutOrder last_order = null;

		if (list == null) {
			orderIdInt = 1;
		} else if (list.size() == 1) {
			// Get the only element of the list
			Iterator<CheckoutOrder> iter = list.iterator();

			last_order = iter.next();
			String str_temp = last_order.getOrderId();
			if (str_temp != null) {
				orderIdInt = Integer.valueOf(str_temp) + 1;
			} else {
				errorString = "Null Order ID.";
			}
		} else {
			// Get the last element of the list
			last_order = list.get(list.size() - 1);
			String str_temp1 = last_order.getOrderId();
			if (str_temp1 != null) {
				orderIdInt = Integer.valueOf(str_temp1) + 1;
			} else {
				errorString = "Null order ID.";
			}
		}

		/* -------- determine the User Account --------------------------- */
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

		/* ---------------create the order to be added ---------------------- */
		CheckoutOrder new_order = new CheckoutOrder();
		new_order.setOrderId(String.valueOf(orderIdInt));
		new_order.setDateTime(dateTime);
		new_order.setTotal(0);
		new_order.setUserAccount(theAccount);
		

		/*
		 * ----------- add the order, and forward to corresponding page ----------------
		 */

		try {
			DBUtils.addOrder(request, new_order);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		if (errorString != null) {
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/checkoutorder/checkoutOrderAddView.jsp");
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
					errorString = "To add items, there should be at least one checkout order. Please add a checkout order first";
				}
				request.setAttribute("itemList", new_order.getItems());
				request.setAttribute("accountList", account_list);
				request.setAttribute("order", new_order);
				request.setAttribute("theAccount", new_order.getUserAccount());
				request.setAttribute("errorString", errorString);
				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/WEB-INF/views/checkoutorder/checkoutOrderEditView.jsp");
				dispatcher.forward(request, response);
			}
		}

	}

}
