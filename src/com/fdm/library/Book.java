package com.fdm.library;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import com.fdm.JPA.isStorable;
import com.fdm.controlers.ControlerFactory;

@Entity
@NamedStoredProcedureQuery(name = "SET_PWD", procedureName = "SET_PWD", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "user"),
		@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "setP"),
		@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "sa") })
@Table(name = "Books")
public class Book implements isStorable {

	@Id
	// @ManyToMany(mappedBy = "User_Books")
	@GeneratedValue(generator = "BookSequence")
	@SequenceGenerator(name = "BookSequence", initialValue = 1, allocationSize = 1, sequenceName = "seq_books_id")
	@Column(name = "book_id")
	private int bookId;

	@Column(name = "author")
	private String author;
	@Column(name = "length")
	private int length;
	@Column(name = "title")
	private String title;
	@Column(name = "genre")
	private String genre;
	@Column(name = "price")
	private long price;
	@Column(name = "description")
	private String desc;
	@Column(name = "path")
	private String path;
	@Column(name = "rating")
	private double rating;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, mappedBy = "book", targetEntity = Comment.class)
	private List<Comment> bookComments;

	public Book() {
		super();
		iniColl();
	}

	public Book(String author, int length, String title, String genre, String desc, String path) {
		super();
		this.author = author;
		this.length = length;
		this.title = title;
		this.genre = genre;
		this.desc = desc;
		this.path = path;
		iniColl();
	}

	public Book(String author, int length, String title, String genre, String desc, String path, long price) {
		super();
		this.author = author;
		this.length = length;
		this.title = title;
		this.genre = genre;
		this.price = price;
		this.desc = desc;
		this.path = path;
		iniColl();
	}

	public void iniColl() {
		if (bookComments == null)
			this.bookComments = new ArrayList<Comment>();
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", author=" + author + ", length=" + length + ", title=" + title + ", genre="
				+ genre + ", price=" + price + ", rating=" + rating + ", description=" + "temp" + "]";
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getId() {
		return bookId;
	}

	public void setId(int bookId) {
		this.bookId = bookId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDesc() {
		return desc;
	}

	public String getPath() {
		return path;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public List<Comment> getBookComments() {
		return bookComments;
	}

	public void setBookComments(List<Comment> bookComments) {
		double sum = 0;
		int iter = 0;
		for (Comment c : bookComments) {
			sum = sum + c.getRating();
			iter++;
		}
		this.rating = Math.round(sum / iter) * 1.00;
		ControlerFactory.getBookControler().update(this);
	}

	public void addComment(Comment comment) {
		iniColl();
		this.bookComments.add(comment);
		setBookComments(this.bookComments);
	}

	public void removeComment(Comment comment) {
		iniColl();
		this.bookComments.remove(comment);
		setBookComments(this.bookComments);
	}

}
