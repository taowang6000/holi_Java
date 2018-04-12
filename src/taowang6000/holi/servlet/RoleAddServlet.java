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
import taowang6000.holi.utils.DBUtils;

/**
 * Servlet implementation class RoleAddServlet
 */
@WebServlet("/roleAdd")
public class RoleAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RoleAddServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/role/roleAddView.jsp");

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = (String) request.getParameter("name");

		String errorString = null;

		List<Role> list = null;
		try {
			list = DBUtils.queryRoleAll(request);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		/*----------------determine the roleId of Role ----------------------------*/

		Integer roleIdInt = 0;
		Role last_role = null;

		if (list == null) {
			roleIdInt = 1;
		} else if (list.size() == 1) {
			// Get the only element of the list
			Iterator<Role> iter = list.iterator();

			last_role = iter.next();
			String str_temp = last_role.getRoleId();
			if (str_temp != null) {
				roleIdInt = Integer.valueOf(str_temp) + 1;
			} else {
				errorString = "Null Role ID.";
			}
		} else {
			// Get the last element of the list
			last_role = list.get(list.size() - 1);
			String str_temp1 = last_role.getRoleId();
			if (str_temp1 != null) {
				roleIdInt = Integer.valueOf(str_temp1) + 1;
			} else {
				errorString = "Null Role ID.";
			}
		}

		/* ---------------create the role to be added ---------------------- */
		Role new_role = new Role();
		new_role.setRoleId(String.valueOf(roleIdInt));
		new_role.setName(name);
	
		/*
		 * ----------- add the role, and forward to corresponding page ----------------
		 */

		try {
			DBUtils.addRole(request, new_role);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		if (errorString != null) {
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/role/roleAddView.jsp");
			dispatcher.forward(request, response);
		} else {
			if (request.getParameter("_save") != null) {
				response.sendRedirect(request.getContextPath() + "/role");
			} else if (request.getParameter("_addanother") != null) {
				response.sendRedirect(request.getContextPath() + "/roleAdd");
			} else if (request.getParameter("_continue") != null) {
				request.setAttribute("accountList", new_role.getUserAccounts());
				request.setAttribute("role", new_role);
				request.setAttribute("errorString", errorString);
				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/WEB-INF/views/role/roleEditView.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
