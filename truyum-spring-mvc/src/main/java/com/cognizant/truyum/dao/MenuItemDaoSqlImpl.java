package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

@Component("menuItemDao")
public class MenuItemDaoSqlImpl implements MenuItemDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemDaoSqlImpl.class);
	SimpleDateFormat sf=new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * To get the menu items list for Admin
	 * 
	 * @return menuItemListAdmin
	 */
	public List<MenuItem> getMenuItemListAdmin(){
		LOGGER.info("Start");
		List<MenuItem> menuItemListAdmin=new ArrayList<MenuItem>();
		try (Connection cn=ConnectionHandler.getConnection();){
			String sql="select * from menu_item m";
			 PreparedStatement ps=cn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				long id=rs.getLong("menu_item_id");
				String name = rs.getString("name");
				String price = String.valueOf(rs.getFloat("price"));
				String date = sf.format(rs.getDate("date_of_launch"));
				boolean active = rs.getBoolean("active");
				String category = rs.getString("category");
				boolean freeDelivery = rs.getBoolean("free_delivery");
				MenuItem menuItem = new MenuItem(id, name, price, active, DateUtil.convertToDate(date), category,
						freeDelivery);
				menuItemListAdmin.add(menuItem);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info("End");
		return menuItemListAdmin;
	}
	
	/**
	 * To get the menu items list for Customer
	 * 
	 * @return menuItemListCustomer
	 */
	public List<MenuItem> getMenuItemListCustomer(){
		LOGGER.info("Start");
		List<MenuItem> menuItemListCustomer=new ArrayList<MenuItem>();
		try(Connection cn=ConnectionHandler.getConnection();){
			String sql="select menu_item_id,name,price,active,date_of_launch,category,free_delivery from menu_item where date_of_launch<=CURDATE() and active='1'";
			PreparedStatement ps=cn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			ps.clearParameters();
			while(rs.next()) {
				long id=rs.getLong("menu_item_id");
				String name = rs.getString("name");
				String price = String.valueOf(rs.getFloat("price"));
				String date = sf.format(rs.getDate("date_of_launch"));
				boolean active = rs.getBoolean("active");
				String category = rs.getString("category");
				boolean freeDelivery = rs.getBoolean("free_delivery");
				MenuItem menuItem=new MenuItem(id, name, price, active, DateUtil.convertToDate(date), category,
						freeDelivery);
				menuItemListCustomer.add(menuItem);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info("End");
		return menuItemListCustomer;
	}
	
	/**
	 * To get the menu item from menu_item table based on id
	 * 
	 * @param menuItemId
	 * @return menuItem
	 */
	public MenuItem getMenuItem(long menuItemId) {
		LOGGER.info("Start");
		MenuItem menuItem=null;
		try(Connection cn=ConnectionHandler.getConnection();) {
			String sql="SELECT * FROM menu_item where menu_item_id="+menuItemId;
			PreparedStatement ps=cn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			ps.clearParameters();
			while(rs.next()) {
				long id=rs.getLong("menu_item_id");
				String name = rs.getString("name");
				String price = String.valueOf(rs.getFloat("price"));
				String date = sf.format(rs.getDate("date_of_launch"));
				boolean active = rs.getBoolean("active");
				String category = rs.getString("category");
				boolean freeDelivery = rs.getBoolean("free_delivery");
				menuItem=new MenuItem(id, name, price, active, DateUtil.convertToDate(date), category,
						freeDelivery);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info("End");
		return menuItem;
	}
	
	/**
	 * To edit or update the details of menu item
	 * 
	 * @param menuItem
	 */
	public void modifyMenuItem(MenuItem menuItem) {
		LOGGER.info("Start");
		long menuItemId=menuItem.getId();
		try(Connection cn=ConnectionHandler.getConnection();) {
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			String d=sf.format(menuItem.getDateOfLaunch());
			Date date=Date.valueOf(d);
			String sql="update menu_item set name=?,price=?,active=?,date_of_launch=?,category=?,free_delivery=? where menu_item_id="
					+menuItemId;
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1, menuItem.getName());
			ps.setFloat(2, Float.parseFloat(menuItem.getPrice()));
			ps.setBoolean(3, menuItem.isActive());
			ps.setDate(4, date);
			ps.setString(5, menuItem.getCategory());
			ps.setBoolean(6, menuItem.isFreeDelivery());
			int rs=ps.executeUpdate();
			ps.clearParameters();
			if(rs>0) {
				System.out.println("Item Modified SUCCESSFULLY!!!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info("End");
}
	
	
	public void editMenuItem(MenuItem menuItem) {}
}
