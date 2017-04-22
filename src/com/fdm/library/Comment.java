package com.fdm.library;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import com.fdm.JPA.isStorable;
import com.fdm.controlers.ControlerFactory;
import com.fdm.users.User;

@Entity
@NamedStoredProcedureQuery(name = "BOOK_USER", procedureName = "BOOK_USER", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "uid"),
		@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "bid"),
		@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "type") })

@Table(name = "comments")
public class Comment implements isStorable {

	@Id
	@Column(name = "comment_id")
	@GeneratedValue(generator = "CommentSequence")
	@SequenceGenerator(name = "CommentSequence", initialValue = 1, allocationSize = 1, sequenceName = "seq_comment_id")
	private int id;

	@Column(name = "text")
	private String text;
	@Column(name = "rating")
	private double rating;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(referencedColumnName = "USER_ID ", name = "USER_ID")
	private User username;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "book_id", referencedColumnName = "book_id")
	private Book book;

	public Comment() {
		super();
	}

	public Comment(String text, double rating, User user, Book book) {
		super();
		this.text = text;
		this.rating = rating;
		this.username = user;
		this.book = book;
	}

	public Comment(String text, double rating, Book book, User user) {
		super();
		this.text = text;
		this.rating = rating;
		this.username = user;
		this.book = book;
	}

	public void writeComment() {
		ControlerFactory.getCommentControler().write(this);
	}

	@Override
	public String toString() {
		return "Comment [text=" + text + ", rating=" + rating + ", username=" + username + ", book=" + book.getTitle()
				+ "]";
	}

	public void delete() {
		ControlerFactory.getCommentControler().delete(this);
	}

	public User getUsername() {
		return username;
	}

	public void setUsername(User username) {
		this.username = username;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
