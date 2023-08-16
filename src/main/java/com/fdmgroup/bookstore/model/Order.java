package com.fdmgroup.bookstore.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order {

	private int orderId;
	private Book bookOrdered;	
    private int userId;
    private LocalDateTime orderDateTime;
    
	public Order (Book bookOrdered, int userId, LocalDateTime orderDateTime) {
		super();
		this.bookOrdered = bookOrdered;
		this.userId = userId;
		this.orderDateTime = orderDateTime;
	}


	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Book getBookOrdered() {
		return bookOrdered;
	}

	public void setBookOrdered(Book bookOrdered) {
		this.bookOrdered = bookOrdered;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDateTime getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(LocalDateTime orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookOrdered, orderDateTime, orderId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(bookOrdered, other.bookOrdered) && Objects.equals(orderDateTime, other.orderDateTime)
				&& orderId == other.orderId && userId == other.userId;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", bookOrdered=" + bookOrdered + ", userId=" + userId + ", orderDateTime="
				+ orderDateTime + "]";
	}
    

}
