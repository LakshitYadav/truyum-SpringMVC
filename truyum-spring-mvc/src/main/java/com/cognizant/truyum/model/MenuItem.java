package com.cognizant.truyum.model;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class MenuItem {
	
	//instance variables declaration
	private long id;
	
	@NotBlank(message = "Title is required")
	@Size(min = 2, max = 65, message = "Title should have 2 to 65 characters")
	private String name;
	
	@NotBlank(message = "Price is required")
	@Digits(integer = 4,fraction = 2,message = "Price has to be a number")
	private String price;
	
	@NotNull(message= "Status required")
	private boolean active;
	
	@NotNull(message = "Launch Date required")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateOfLaunch;
	
	@NotBlank(message = "Category required")
	private String category;
	
	@NotNull(message= "Delivery status required")
	private boolean freeDelivery;
	
	//parameterized constructor to set all instance variables
		public MenuItem(long id,  String name,  String price, boolean active, Date date, String category, boolean freeDelivery) {
			super();
			this.id = id;
			this.category = category;
			this.name = name;
			this.price = price;
			this.active = active;
			this.freeDelivery = freeDelivery;
			this.dateOfLaunch = date;
		}
		
		//Getters and Setters
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		public boolean isActive() {
			return active;
		}
		public void setActive(boolean active) {
			this.active = active;
		}
		public boolean isFreeDelivery() {
			return freeDelivery;
		}
		public void setFreeDelivery(boolean freeDelivery) {
			this.freeDelivery = freeDelivery;
		}
		public Date getDateOfLaunch() {
			return dateOfLaunch;
		}
		public void setDateOfLaunch(Date dateOfLaunch) {
			this.dateOfLaunch = dateOfLaunch;
		}
	
	@Override	//hashCode() method
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	
	@Override	//equals() method which checks for equality based on the �id� attribute
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuItem other = (MenuItem) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override	//toString() method
	public String toString() {
		return "MenuItem [id=" + id + ", category=" + category + ", name=" + name + ", price=" + price + ", active="
				+ active + ", freeDelivery=" + freeDelivery + ", dateOfLaunch=" + dateOfLaunch + "]";
	}
}
