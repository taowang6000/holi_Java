package taowang6000.holi.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import taowang6000.holi.domain.AdminUser;
import taowang6000.holi.utils.MyUtils;
import taowang6000.holi.domain.*;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomeServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Forward to /WEB-INF/views/homeView.jsp
		// (Users can not access directly into JSP pages placed in WEB-INF)
		HttpSession session = request.getSession();

		// Check User has logged on
		AdminUser loginedUser = MyUtils.getLoginedUser(session);

		// Not logged in
		if (loginedUser == null) {
			// Redirect to login page.
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		// Store info to the request attribute before forwarding.
		request.setAttribute("user", loginedUser);
		
		/*
		 * EntityManagerFactory emf =
		 * Persistence.createEntityManagerFactory("holi_manager");
		 * 
		 * EntityManager em = emf.createEntityManager(); EntityTransaction tx =
		 * em.getTransaction();
		 * 
		 * tx.begin();
		 */
		/* ------------------------- use function "persist"---------------------- */
		/*
		 * Date date = new Date(); Timestamp ts = new Timestamp(date.getTime());
		 * 
		 * AdminUser admin1 = new AdminUser("admin", "tw15k@my.fsu.edu", "admin", ts);
		 * 
		 * em.persist(admin1);
		 */

		/* ------------------------use function "find" all related data------------- */
		/*
		 * CheckoutOrder checkoutOrder = em.find(CheckoutOrder.class,
		 * Integer.toString(1)); System.out.println(checkoutOrder.getDateTime());
		 * System.out.println(checkoutOrder.getUserAccount().getUserName()); List<Item>
		 * items = checkoutOrder.getItems(); for (Item i:items) {
		 * System.out.println(i.getCountry()); }
		 */

		/* ------------------------ use basic JPQL ----------------------------- */
		/*
		 * Query query = em.createQuery("select i from Item i"); List<Item> items =
		 * query.getResultList();
		 * 
		 * for (Item i:items) { System.out.println(i.getCountry()); }
		 */

		/*------------------------ use basic JPQL Typed Query ------------------*/
		/* -------------- use it whenever possible ----------------------------- */
		/*
		 * TypedQuery<Item> query = em.createQuery("select i from Item i", Item.class);
		 * List<Item> items = query.getResultList();
		 * 
		 * for (Item i:items) { System.out.println(i.getCountry()); }
		 */

		/*----------------------use JPQL Join Query_1 ---------------------*/
		/*
		 * List<Object[]> join_test =
		 * em.createQuery("select c.orderId, c.dateTime, u.userName" +
		 * " from CheckoutOrder c join c.userAccount u").getResultList();
		 * 
		 * for (Object[] j:join_test) { System.out.println(j[0]);
		 * System.out.println(j[1]); System.out.println(j[2]); }
		 */

		/* ----------------------- JPQL Named Query ---------------------------- */
		/* Will only need to be constructed once when the application starts */
		/*
		 * List<CheckoutOrder> orders = em.createNamedQuery("CheckoutOrder.findAll",
		 * CheckoutOrder.class).getResultList(); for (CheckoutOrder c:orders) {
		 * System.out.println(c.getDateTime()); }
		 */

		/*
		 * 
		 * 
		 * tx.commit(); em.close();
		 */
		// System.out.println("Just a test");

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
