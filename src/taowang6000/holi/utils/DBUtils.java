package taowang6000.holi.utils;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

import taowang6000.holi.domain.*;

public class DBUtils {
	/* find a designated admin user with a username and a password */

	public static AdminUser findAdmin(HttpServletRequest request, String userName, //
			String password) throws Exception {

		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<AdminUser> query = em.createQuery("select a FROM AdminUser a WHERE " + "a.userName = '"
					+ userName + "' AND a.password = '" + password + "'", AdminUser.class);
			List<AdminUser> users = query.getResultList();
			Iterator<AdminUser> iter = users.iterator();
			if (iter.hasNext()) {
				AdminUser user = iter.next();
				return user;
			}
			return null;
		} finally {
			em.close();
		}
	}

	/* List all the admin_users from database */
	public static List<AdminUser> queryAdminAll(HttpServletRequest request) throws Exception {
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		try {
			List<AdminUser> users = em.createNamedQuery("AdminUser.findAll", AdminUser.class).getResultList();
			return users;
		} finally {
			em.close();
		}
	}

	/* Add a new admin_user to database */
	public static void addAdminUser(HttpServletRequest request, AdminUser newuser) throws Exception {
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(newuser);
		tx.commit();
		em.close();
	}

	/* Update an existing user in database */
	public static void updateAdminUser(HttpServletRequest request, String adminName, String email,
			String password) throws Exception {
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		AdminUser existingUser = em.find(AdminUser.class, adminName);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		existingUser.setEmail(email);
		existingUser.setPassword(password);
		tx.commit();
		em.close();
	}

	public static void deleteAdminUser(HttpServletRequest request, String username) throws Exception {
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		AdminUser existingUser = em.find(AdminUser.class, username);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.remove(existingUser);
		tx.commit();
		em.close();
	}

	public static Item findItem(HttpServletRequest request, String itemId) throws Exception {

		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Item> query = em.createQuery("select i FROM Item i WHERE " + "i.itemid = '" + itemId + "'",
					Item.class);
			List<Item> items = query.getResultList();
			Iterator<Item> iter = items.iterator();
			if (iter.hasNext()) {
				Item item = iter.next();
				return item;
			}
			return null;
		} finally {
			em.close();
		}
	}

	/* List all the items from database */
	public static List<Item> queryItemAll(HttpServletRequest request) throws Exception {
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		try {
			List<Item> items = em.createNamedQuery("Item.findAll", Item.class).getResultList();
			return items;
		} finally {
			em.close();
		}
	}

	public static void addItem(HttpServletRequest request, Item new_item) throws Exception {
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();

		CheckoutOrder theOrder = new_item.getCheckoutOrder();
		Double total = theOrder.getTotal() + new_item.getPrice();
		CheckoutOrder existingOrder = em.find(CheckoutOrder.class, theOrder.getOrderId());

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(new_item);
		existingOrder.setTotal(total);
		tx.commit();
		em.close();
	}

	public static void updateItem(HttpServletRequest request, Item new_item) throws Exception {
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();

		CheckoutOrder theOrder = new_item.getCheckoutOrder();
		CheckoutOrder existingOrder = em.find(CheckoutOrder.class, theOrder.getOrderId());
		Item existingItem = em.find(Item.class, new_item.getItemid());
		Double total = theOrder.getTotal() + new_item.getPrice() - existingItem.getPrice();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		existingItem.setCity(new_item.getCity());
		existingItem.setCountry(new_item.getCountry());
		existingItem.setOptionValue(new_item.getOptionValue());
		existingItem.setPrice(new_item.getPrice());
		existingItem.setState(new_item.getState());
		existingItem.setYear(new_item.getYear());
		existingItem.setCheckoutOrder(new_item.getCheckoutOrder());

		existingOrder.setTotal(total);

		tx.commit();
		em.close();
	}

	public static void deleteItem(HttpServletRequest request, String itemId) throws Exception {

		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		Item existingItem = em.find(Item.class, itemId);
		CheckoutOrder theOrder = existingItem.getCheckoutOrder();
		CheckoutOrder existingOrder = em.find(CheckoutOrder.class, theOrder.getOrderId());
		Double total = theOrder.getTotal() - existingItem.getPrice();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.remove(existingItem);
		existingOrder.setTotal(total);
		tx.commit();
		em.close();
	}

	public static List<Item> getItemsFromOrder(HttpServletRequest request, String orderId) throws Exception {
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Item> query = em.createQuery(
					"select i FROM Item i WHERE " + "i.checkoutOrder.orderId = '" + orderId + "'", Item.class);
			List<Item> items = query.getResultList();
			return items;
		} finally {
			em.close();
		}
	}

	public static CheckoutOrder findOrder(HttpServletRequest request, String orderId) throws Exception {

		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<CheckoutOrder> query = em.createQuery(
					"select o FROM CheckoutOrder o WHERE " + "o.orderId = '" + orderId + "'", CheckoutOrder.class);
			List<CheckoutOrder> orders = query.getResultList();
			Iterator<CheckoutOrder> iter = orders.iterator();
			if (iter.hasNext()) {
				CheckoutOrder order = iter.next();
				return order;
			}
			return null;
		} finally {
			em.close();
		}
	}

	/* List all the checkout orders from database */
	public static List<CheckoutOrder> queryOrderAll(HttpServletRequest request) throws Exception {
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		try {
			List<CheckoutOrder> orders = em.createNamedQuery("CheckoutOrder.findAll", CheckoutOrder.class)
					.getResultList();
			return orders;
		} finally {
			em.close();
		}
	}

	public static void addOrder(HttpServletRequest request, CheckoutOrder newOrder) throws Exception {
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(newOrder);
		tx.commit();
		em.close();
	}

	public static void updateOrder(HttpServletRequest request, String orderId, String dateTime, UserAccount theAccount)
			throws Exception {

		Double total = 0.0;
		List<Item> items = getItemsFromOrder(request, orderId);
		for (Item i : items) {
			total += i.getPrice();
		}

		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		CheckoutOrder existingOrder = em.find(CheckoutOrder.class, orderId);
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		existingOrder.setDateTime(dateTime);
		existingOrder.setUserAccount(theAccount);
		existingOrder.setTotal(total);

		tx.commit();
		em.close();
	}

	public static void deleteOrder(HttpServletRequest request, String orderId) throws Exception {

		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		CheckoutOrder existingOrder = em.find(CheckoutOrder.class, orderId);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.remove(existingOrder);
		tx.commit();
		em.close();
	}

	public static UserAccount findAccount(HttpServletRequest request, String accountId) throws Exception {

		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<UserAccount> query = em.createQuery(
					"select u FROM UserAccount u WHERE " + "u.userId = '" + accountId + "'", UserAccount.class);
			List<UserAccount> accounts = query.getResultList();
			Iterator<UserAccount> iter = accounts.iterator();
			if (iter.hasNext()) {
				UserAccount account = iter.next();
				return account;
			}
			return null;
		} finally {
			em.close();
		}
	}

	public static List<UserAccount> queryAccountAll(HttpServletRequest request) throws Exception {
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		try {
			List<UserAccount> accounts = em.createNamedQuery("UserAccount.findAll", UserAccount.class).getResultList();
			return accounts;
		} finally {
			em.close();
		}
	}

	public static void addAccount(HttpServletRequest request, UserAccount newAccount) throws Exception {
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(newAccount);
		tx.commit();
		em.close();
	}

	public static void updateAccount(HttpServletRequest request, String userId, String contactNo, String email,
			String password, String userName) throws Exception {

		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		UserAccount existingAccount = em.find(UserAccount.class, userId);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		existingAccount.setContactNo(contactNo);
		existingAccount.setEmail(email);
		existingAccount.setPassword(password);
		existingAccount.setUserName(userName);
		tx.commit();
		em.close();
	}

	public static void deleteAccount(HttpServletRequest request, String userId) throws Exception {

		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		UserAccount existingAccount = em.find(UserAccount.class, userId);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.remove(existingAccount);
		tx.commit();
		em.close();
	}

	public static List<Role> queryRoleAll(HttpServletRequest request) throws Exception {
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		try {
			List<Role> roles = em.createNamedQuery("Role.findAll", Role.class).getResultList();
			return roles;
		} finally {
			em.close();
		}
	}

	public static Role findRole(HttpServletRequest request, String roleId) throws Exception {

		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Role> query = em.createQuery("select r FROM Role r WHERE " + "r.roleId = '" + roleId + "'",
					Role.class);
			List<Role> roles = query.getResultList();
			Iterator<Role> iter = roles.iterator();
			if (iter.hasNext()) {
				Role role = iter.next();
				return role;
			}
			return null;
		} finally {
			em.close();
		}
	}
	
	public static void addRole(HttpServletRequest request, Role newRole) throws Exception {
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(newRole);
		tx.commit();
		em.close();
	}
	
	public static void updateRole(HttpServletRequest request, String roleId, String name)
			throws Exception {

		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		Role existingRole = em.find(Role.class, roleId);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		existingRole.setName(name);
		tx.commit();
		em.close();
	}
	
	public static void deleteRole(HttpServletRequest request, String roleId) throws Exception {

		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		Role existingRole = em.find(Role.class, roleId);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.remove(existingRole);
		tx.commit();
		em.close();
	}

	public static void updateUserRole(HttpServletRequest request, String roleId, List<UserAccount> list) throws Exception {
		
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();
		Role existingRole = em.find(Role.class, roleId);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		existingRole.setUserAccounts(list);
		tx.commit();
		em.close();
	}

	
}
