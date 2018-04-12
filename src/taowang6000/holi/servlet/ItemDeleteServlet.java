package taowang6000.holi.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import taowang6000.holi.domain.Item;
import taowang6000.holi.utils.DBUtils;

@WebServlet("/itemDelete")
public class ItemDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ItemDeleteServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String itemId = (String) request.getParameter("id");
		Item item = null;
		try {
			item = DBUtils.findItem(request, itemId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("item", item);

		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/item/itemDeleteView.jsp");

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String itemId = (String) request.getParameter("itemid_todelete");
		String errorString = null;

		try {
			DBUtils.deleteItem(request, itemId);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// Store information to request attribute, before forward to views.
		request.setAttribute("errorString", errorString);

		// Redirect to the item listing page.
		response.sendRedirect(request.getContextPath() + "/item");
	}

}
