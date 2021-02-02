package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

@Component
public class CartDaoSqlImpl implements CartDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(CartDaoSqlImpl.class);
	private double totalPrice;

	/**
	 * adds menu items to the cart
	 * 
	 * @param userId
	 * @param menuItemId
	 */
	@Override
	public boolean addCartItem(long userId, long menuItemId) {
		LOGGER.info("Start");
		MenuItemDao menuItemDao = new MenuItemDaoSqlImpl();
		MenuItem menuItem = menuItemDao.getMenuItem(menuItemId);
		String sql = "insert into cart(name,price,free_delivery,menu_item_id,user_id)Values(?,?,?,?,?)";
		try(Connection cn = ConnectionHandler.getConnection();) {
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, menuItem.getName());
			ps.setFloat(2, Float.parseFloat(menuItem.getPrice()));
			ps.setBoolean(3, menuItem.isFreeDelivery());
			ps.setInt(4,(int)menuItemId );
			ps.setInt(5,(int)userId );
			int rs=ps.executeUpdate();

			LOGGER.info("End");
			if (rs > 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			LOGGER.info("End");
			return false;
		}

	}

	/**
	 * returns list of menu items in cart for a user
	 * 
	 * @param userId
	 * @return menuItemListCart
	 * @throws CartEmptyException
	 */
	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {

		LOGGER.info("Start");
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		List<MenuItem> menuItemListCart=new ArrayList<MenuItem>();
		Cart cart=new Cart(menuItemListCart, 0);
		String sql="select * from cart,menu_item where menu_item.menu_item_id=cart.menu_item_id and user_id="+userId;
		String sqlPrice="select sum(cart.price) as total from cart where user_id="+userId;
		try (Connection cn=ConnectionHandler.getConnection();){
			PreparedStatement ps=cn.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				menuItemListCart.add(new MenuItem(Long.parseLong(rs.getString("menu_item_id")), rs.getString("name"), rs.getString("price"), rs.getBoolean("active"), DateUtil.convertToDate(sdf.format(rs.getDate("date_of_launch"))), rs.getString("category"), rs.getBoolean("free_delivery")));
			}
			PreparedStatement sum=cn.prepareStatement(sqlPrice);
			ResultSet rsset=sum.executeQuery();
			rsset.next();
			double total=rsset.getFloat("total");
			if(menuItemListCart.isEmpty()) {
				throw new CartEmptyException("\nNo items in cart to display");
			}else {
			cart.setMenuItemList(menuItemListCart);
			cart.setTotal(total);
			totalPrice=total;
			}
		LOGGER.info("End");
		
	}catch (SQLException e) {
		// TODO: handle exception
	}
		return menuItemListCart;
	}
		

	/**
	 * deletes menu items from cart
	 * 
	 * @param userId
	 * @param menuItemId
	 */
	@Override
	public boolean removeCartItem(long userId, long menuItemId) {

		LOGGER.info("Start");
		
		String sql = "delete from cart where user_id = ? and menu_item_id = ? ";
		try(Connection cn = ConnectionHandler.getConnection();) {
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setLong(1, userId);
			ps.setLong(2, menuItemId);
			int rs = ps.executeUpdate();

			LOGGER.info("End");
			if (rs > 0)
				return true;
			else
				return false;
		} catch (SQLException e) {

			LOGGER.info("End");
			return false;
		}

	}

	/**
	 * returns total price of all the menu items in the cart
	 * 
	 * @return totalPrice
	 */
	
	public double getTotalCartAmount() {

		LOGGER.info("Start");
		LOGGER.info("End");

		return totalPrice;
	}

}