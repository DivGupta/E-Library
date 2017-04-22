package com.fdm.frontEndFuntionality;

import java.util.ArrayList;
import java.util.List;

import com.fdm.library.Book;
import com.fdm.library.Comment;
import com.fdm.library.Library;
import com.fdm.users.User;

public class UserFunctionality implements AgentFunctionality {

	public boolean comment(Book book, User user, String... args) {
		Comment comment = new Comment(args[0], Double.parseDouble(args[1]), user, book);
		if (Library.newComment(comment, user, book)) {
			comment.writeComment();
			return true;
		}
		return false;
	}

	public void removeComment(Comment comment, User user) {
		comment.getBook().removeComment(comment);
		comment.delete();
		user.removeComment(comment);
	}

	public void buyBook(Book book, User user) {
		Library.newBook(book, user);
		user.buyBook(book);
	}

	public List<String> checkOut(User user) {
		List<String> paths = new ArrayList<String>();
		for (Book book : user.getSc(user).getCart())
			paths.add(book.getPath());

		return paths;
	}

	public void checkOutCommit(User user) {
		for (Book book : user.getSc(user).getCart())
			buyBook(book, user);

		emptyCart(user);
	}

	public void emptyCart(User user) {
		user.getSc(user).purge();
	}

	public void removeBook(User user, Book book) {
		user.getSc(user).remove(book);
	}

	public void addBook(User user, Book book) {
		user.getSc(user).add(book);
	}

	public List<Book> viewCart(User user) {
		return user.getSc(user).getCart();
	}

	public List<Book> viewOwned(User user) {
		return user.personalLibrary();
	}

	public void modify(User user, String... args) {
		user.setNewEmail(args[0]);
		user.setNewPwd(args[1], args[2]);
	}

}
