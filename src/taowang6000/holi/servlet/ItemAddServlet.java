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

import taowang6000.holi.domain.Item;
import taowang6000.holi.domain.CheckoutOrder;
import taowang6000.holi.utils.DBUtils;

@WebServlet("/itemAdd")
public class ItemAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemAddServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* From checkoutOrderEditView, there is a entrance parameter: order id */
		String orderId = (String) request.getParameter("id");
		String errorString = null;
		// String entrance = "itemAdd";
		List<CheckoutOrder> list = null;
		try {
			list = DBUtils.queryOrderAll(request);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		if (list == null) {
			errorString = "To add items, there should be at least one checkout order. Please add a checkout order first";
		}
		// Store info in request attribute, before forward to views
		request.setAttribute("errorString", errorString);
		request.setAttribute("orderList", list);
		request.setAttribute("selected_orderId", orderId);

		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/item/itemAddView.jsp");

		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String city = (String) request.getParameter("city");
		String country = (String) request.getParameter("country");
		String optionValueStr = (String) request.getParameter("option_value");
		String priceStr = request.getParameter("price");
		String state = request.getParameter("state");
		String year = request.getParameter("year");
		String orderId = request.getParameter("id_order");

		String errorString = null;

		List<Item> list = null;
		try {
			list = DBUtils.queryItemAll(request);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		/*----------------determine the itemid ----------------------------*/

		Integer itemIdInt = 0;
		Item last_item = null;

		if (list == null) {
			itemIdInt = 1;
		} else if (list.size() == 1) {
			// Get the only element of the list
			Iterator<Item> iter = list.iterator();

			last_item = iter.next();
			String str_temp = last_item.getItemid();
			if (str_temp != null) {
				itemIdInt = Integer.valueOf(last_item.getItemid()) + 1;
			} else {
				errorString = "Null item ID.";
			}
		} else {
			// Get the last element of the list
			last_item = list.get(list.size() - 1);
			String str_temp1 = last_item.getItemid();
			if (str_temp1 != null) {
				itemIdInt = Integer.valueOf(last_item.getItemid()) + 1;
			} else {
				errorString = "Null item ID.";
			}
		}

		/* -------- determine the checkout order --------------------------- */
		CheckoutOrder theOrder = null;
		try {
			theOrder = DBUtils.findOrder(request, orderId);

			if (theOrder == null) {
				if (orderId == null) {
					errorString = "Order ID null";
				} else {
					errorString = "Order not found.";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		/* ---------------create the item to be added ---------------------- */
		Item new_item = new Item();
		new_item.setItemid(String.valueOf(itemIdInt));
		new_item.setCity(city);
		new_item.setCountry(country);
		if (optionValueStr != null) {
			new_item.setOptionValue(Integer.valueOf(optionValueStr));
		}
		if (priceStr != null) {
			new_item.setPrice(Double.valueOf(priceStr));
		}
		new_item.setState(state);
		;
		new_item.setYear(year);
		new_item.setCheckoutOrder(theOrder);

		/*
		 * ----------- add the item, and forward to corresponding page ----------------
		 */

		try {
			DBUtils.addItem(request, new_item);
			// DBUtils.reCalculateOrderPrice(request, orderId);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		if (errorString != null) {
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/item/itemAddView.jsp");
			dispatcher.forward(request, response);
		} else {
			if (request.getParameter("_save") != null) {
				response.sendRedirect(request.getContextPath() + "/item");
			} else if (request.getParameter("_addanother") != null) {
				response.sendRedirect(request.getContextPath() + "/itemAdd");
			} else if (request.getParameter("_continue") != null) {
				List<CheckoutOrder> order_list = null;
				try {
					order_list = DBUtils.queryOrderAll(request);
				} catch (Exception e) {
					e.printStackTrace();
					errorString = e.getMessage();
				}

				if (order_list == null) {
					errorString = "To add items, there should be at least one checkout order. Please add a checkout order first";
				}
				request.setAttribute("orderList", order_list);
				request.setAttribute("item", new_item);
				request.setAttribute("theOrder", new_item.getCheckoutOrder());
				request.setAttribute("errorString", errorString);
				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/WEB-INF/views/item/itemEditView.jsp");
				dispatcher.forward(request, response);
			}
		}

	}

}
