package com.cognizant.truyum.dao;

import java.util.List;
import com.cognizant.truyum.model.MenuItem;

public interface CartDao {

	//abstract methods
	public boolean addCartItem(long userId,long  menuItemId);
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException;
	public boolean removeCartItem(long userId,long  menuItemId);
	public double getTotalCartAmount();

}
