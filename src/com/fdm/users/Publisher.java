package com.fdm.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fdm.library.Book;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "userId")
public class Publisher extends Agent {

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinTable(name = "Author_Books", joinColumns = @JoinColumn(name = "USER_ID") , inverseJoinColumns = @JoinColumn(name = "bookId") )
	private List<Book> wrote;

	public Publisher(String name, String email) {
		super(name, email);
	}

	public Publisher() {
	}

	@Override
	public String toString() {
		return "Publisher [wrote=" + wrote + ", userId=" + userId + ", name=" + name + ", email=" + email + "]";
	}

	public List<Book> bibliography() {
		return wrote;
	}

	public void modBib(Book book, boolean add) {
		if (add) {
			if (wrote == null)
				wrote = new ArrayList<Book>();
			wrote.add(book);
		} else if (wrote != null)
			wrote.remove(book);
	}

	public boolean iWrote(Book book) {
		if (wrote.contains(book))
			return true;
		else
			return false;
	}

}
