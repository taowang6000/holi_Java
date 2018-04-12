package taowang6000.holi.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import taowang6000.holi.domain.Role;
import taowang6000.holi.utils.DBUtils;

/**
 * Servlet implementation class RoleDeleteServlet
 */
@WebServlet("/roleDelete")
public class RoleDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public RoleDeleteServlet() {
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

		request.setAttribute("account", role);
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/role/roleDeleteView.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roleId = (String) request.getParameter("roleId_todelete");
		String errorString = null;

		try {
			DBUtils.deleteRole(request, roleId);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// Store information to request attribute, before forward to views.
		request.setAttribute("errorString", errorString);

		// Redirect to the item listing page.
		response.sendRedirect(request.getContextPath() + "/role");
	}
	

}
