package com.fdm.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

import com.fdm.library.Book;
import com.fdm.library.Comment;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "userId")
// @Inheritance(strategy = InheritanceType.JOINED)
public class User extends Agent {

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + "]";
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "User_Books", joinColumns = @JoinColumn(name = "USER_ID") , inverseJoinColumns = @JoinColumn(name = "bookId") )
	protected List<Book> books;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, mappedBy = "username", targetEntity = Comment.class)
	protected List<Comment> comments;

	@Transient
	ShoppingCart sc;

	public User(String name, String email) {
		super(name, email);
		iniColl();
	}

	public User(Agent agent) {
		super(agent.name, agent.email);
		iniColl();
	}

	public User() {
		super();
		iniColl();
	}

	public void iniColl() {
		if (books == null)
			books = new ArrayList<Book>();
		if (comments == null)
			comments = new ArrayList<Comment>();
	}

	public void newCart() {
		this.sc = new ShoppingCart(this);
	}

	public void addToCart(Book book, User user) {
		if (sc == null)
			sc = new ShoppingCart(user);
		sc.add(book);
	}

	public ShoppingCart getSc(User user) {
		if (this.sc == null)
			sc = new ShoppingCart(user);
		return sc;
	}

	public void setSc(ShoppingCart sc) {
		this.sc = sc;
	}

	public void buyBook(Book book) {
		if (books == null)
			books = new ArrayList<Book>();
		books.add(book);
	}

	public List<Book> personalLibrary() {
		return books;
	}

	public boolean isOwned(Book book) {
		if (books == null) {
			books = new ArrayList<Book>();
			return false;
		}

		return true;
	}

	public boolean addComment(Book book, Comment comment) {
		if (isOwned(book)) {
			if (comments == null)
				comments = new ArrayList<Comment>();
			comments.add(comment);
			return true;
		} else
			return false;
	}

	public void removeComment(Comment comment) {
		comments.remove(comment);
	}

	public List<Comment> myComments() {
		return comments;
	}

	public void update_profile(String email) {
		this.email = email;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getEmail() {
		return email;
	}

}
