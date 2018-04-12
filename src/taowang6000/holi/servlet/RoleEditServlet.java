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
 * Servlet implementation class RoleEditServlet
 */
@WebServlet("/roleEdit")
public class RoleEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RoleEditServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roleId = (String) request.getParameter("id");
		String errorString = null;
		Role role = null;
		
		try {
			role = DBUtils.findRole(request, roleId);
		} catch (Exception e) {
			errorString = e.getMessage();
			e.printStackTrace();
		}
		

		// Same entrance parameter name as in RoleAddServlet.doPost()
		request.setAttribute("accountList", role.getUserAccounts());
		request.setAttribute("role", role);
		request.setAttribute("errorString", errorString);

		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/role/roleEditView.jsp");

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roleId = (String) request.getParameter("role_id");
		String name = (String) request.getParameter("name");
	
		String errorString = null;
		Role this_role = null;
		try {
			this_role = DBUtils.findRole(request, roleId);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		
		/*
		 * ----------- update the Role, and forward to corresponding page
		 * not touching the account list belonging the role
		 */

		if (errorString == null) {
			try {
				DBUtils.updateRole(request, roleId, name);
			} catch (Exception e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
		}

		if (errorString != null) {
			
			request.setAttribute("role", this_role);
			request.setAttribute("orderList", this_role.getUserAccounts());
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/role/roleEditView.jsp");
			dispatcher.forward(request, response);
		} else {
			if (request.getParameter("_save") != null) {
				response.sendRedirect(request.getContextPath() + "/role");
			} else if (request.getParameter("_addanother") != null) {
				response.sendRedirect(request.getContextPath() + "/roleAdd");
			} else if (request.getParameter("_continue") != null) {
				request.setAttribute("role", this_role);
				request.setAttribute("accountList", this_role.getUserAccounts());
				request.setAttribute("errorString", errorString);
				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/WEB-INF/views/role/roleEditView.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
