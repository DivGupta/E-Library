package com.fdm.users;

import java.util.ArrayList;
import java.util.List;

import com.fdm.library.Book;

public class ShoppingCart {
	List<Book> cart;
	User user;

	public ShoppingCart(User user) {
		this.cart = new ArrayList<Book>();
		this.user = user;
	}

	public boolean add(Book book) {
		if (!user.isOwned(book)) {
			cart.add(book);
			return true;
		}
		return false;
	}

	public void remove(Book book) {
		this.cart.remove(book);
	}

	public void purge() {
		this.cart.clear();
	}

	public List<Book> getCart() {
		return cart;
	}

	public void setCart(List<Book> cart) {
		this.cart = cart;
	}

	public User getUser() {
		return user;
	}

}
