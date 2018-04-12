package taowang6000.holi.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import taowang6000.holi.domain.CheckoutOrder;
import taowang6000.holi.utils.DBUtils;

/**
 * Servlet implementation class CheckoutOrderDeleteServlet
 */
@WebServlet("/checkoutOrderDelete")
public class CheckoutOrderDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CheckoutOrderDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderId = (String) request.getParameter("id");
		CheckoutOrder order = null;
		try {
			order = DBUtils.findOrder(request, orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("order", order);
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/checkoutorder/checkoutOrderDeleteView.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderId = (String) request.getParameter("orderId_todelete");
		String errorString = null;

		try {
			DBUtils.deleteOrder(request, orderId);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// Store information to request attribute, before forward to views.
		request.setAttribute("errorString", errorString);

		// Redirect to the item listing page.
		response.sendRedirect(request.getContextPath() + "/checkoutOrder");
	}

}
