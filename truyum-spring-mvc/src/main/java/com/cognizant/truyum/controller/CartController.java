package com.cognizant.truyum.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.cognizant.truyum.dao.CartEmptyException;
import com.cognizant.truyum.service.CartService;
import com.cognizant.truyum.service.MenuItemService;

@Controller
public class CartController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemController.class);
	/**
	 * CartController is the controller class for various Get and Post mappings 
	 * from menu-item-list for customers 
	 * cartService is the instance of CartService
	 * cartService is autowired and it contains all dao methods 
	 * menuItemService is autowired and it contains all the menu item dao implementations
	 */
	@Autowired
	CartService cartService;

	@Autowired
	MenuItemService menuItemService;

	/**
	 * to add the menu item to the cart
	 * 
	 * @param menuItemId
	 * @param map
	 * @return "menu-item-list-customer" jsp reference
	 * @throws CartEmptyException
	 */
	@GetMapping("/add-to-cart")
	public String addToCart(@RequestParam("menuItemId") long menuItemId, ModelMap map) throws CartEmptyException {
		LOGGER.info("Start");
		long userId = 1L;
		map.addAttribute("addCartStatus", cartService.addCartItem(userId, menuItemId));
		map.addAttribute("menuItemListCustomer", menuItemService.getMenuItemListCustomer());
		LOGGER.info("End");
		return "menu-item-list-customer";
	}

	/**
	 * displays menu items in the cart for a user
	 * 
	 * @param userId
	 * @param modelMap
	 * @return "cart" jsp reference if cart has items 
	 * else return "cart-empty" jsp reference
	 */
	@GetMapping("/show-cart")
	public String showCart(@RequestParam("userId") long userId, ModelMap modelMap) {
		LOGGER.info("Start");

		try {
			modelMap.addAttribute("cart", cartService.getAllCartItems(userId));
			modelMap.addAttribute("total", cartService.getTotalCartAmount());
			LOGGER.info("End");
			return "cart";
		} catch (CartEmptyException e) {
			LOGGER.info("End");
			return "cart-empty";
		}
	}

	/**
	 * deletes a menu item from the cart
	 * 
	 * @param menuItemId
	 * @param userId
	 * @param map
	 * @return "cart" jsp reference if cart has items 
	 * else return "cart-empty" jsp reference
	 */

	@GetMapping("/remove-cart")
	public String removeCart(@RequestParam("menuItemId") long menuItemId, @RequestParam("userId") long userId,
			ModelMap map) {

		LOGGER.info("Start");
		try {
			map.addAttribute("removeCartItemStatus", cartService.removeCartItem(userId, menuItemId));
			map.addAttribute("cart", cartService.getAllCartItems(userId));
			map.addAttribute("total", cartService.getTotalCartAmount());
			LOGGER.info("End");
			return "cart";

		} catch (CartEmptyException e) {
			LOGGER.info("End");
			return "cart-empty";
		}

	}

}