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
import taowang6000.holi.domain.Item;
import taowang6000.holi.utils.DBUtils;

@WebServlet("/itemEdit")
public class ItemEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ItemEditServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String itemId = (String) request.getParameter("id");
		String errorString = null;
		Item item = null;

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

		try {
			item = DBUtils.findItem(request, itemId);
		} catch (Exception e) {
			errorString = e.getMessage();
			e.printStackTrace();
		}

		// Same entrance parameter name as in ItemAddServlet.doPost()
		request.setAttribute("orderList", list);
		request.setAttribute("item", item);
		request.setAttribute("theOrder", item.getCheckoutOrder());
		request.setAttribute("errorString", errorString);

		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/item/itemEditView.jsp");

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String itemId = (String) request.getParameter("item_id");
		String city = (String) request.getParameter("city");
		String country = (String) request.getParameter("country");
		String optionValueStr = (String) request.getParameter("option_value");
		String priceStr = request.getParameter("price");
		String state = request.getParameter("state");
		String year = request.getParameter("year");
		String orderId = request.getParameter("id_order");

		String errorString = null;

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

		/*
		 * ---------------create an new item with designated parameters
		 * ------------------
		 */
		Item new_item = new Item();
		new_item.setItemid(itemId);
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
		 * ----------- update the item, and forward to corresponding page
		 * ----------------
		 */

		if (errorString == null) {
			try {
				DBUtils.updateItem(request, new_item);
			} catch (Exception e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
		}

		if (errorString != null) {
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
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/item/itemEditView.jsp");
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
