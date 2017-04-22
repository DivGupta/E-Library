package com.fdm.users;

import java.io.IOException;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.StoredProcedureParameter;

import com.fdm.JPA.isStorable;
import com.fdm.controlers.ControlerFactory;

@Entity
@NamedStoredProcedureQuery(name = "NEW_PWD", procedureName = "NEW_PWD", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "user"),
		@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "pwd"),
		@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "newpwd") })

public class Agent implements validate, isStorable {

	@Id
	@GeneratedValue(generator = "UserSequence")
	@SequenceGenerator(name = "UserSequence", initialValue = 1, allocationSize = 1, sequenceName = "seq_users_id")
	@Column(name = "user_id")
	protected int userId;

	public int getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	@Column(name = "User_Name")
	protected String name;
	@Column(name = "email")
	protected String email;

	public Agent(String name, String email) {
		this.email = email;
		this.name = name;
	}

	public Agent() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public <Q extends Agent> Q register(String name, String pwd, String email) throws SQLException, IOException {
		if (email == null || pwd == null || name == null)
			throw new IOException("Enter All Parameters");
		this.email = email;
		this.name = name;
		String salt = encrypt.salt();
		String hash = encrypt.hash(pwd, salt);
		ControlerFactory.getAgentControler().register(hash, salt, this);
		return (Q) this;
	}

	public static boolean checkExists(String name) {
		return ControlerFactory.getAgentControler().checkExists(name);
	}

	@Override
	public <Q extends Agent> Q login(String name, String pwd) throws SQLException {
		return ControlerFactory.getAgentControler().login(name, pwd);
	}

	@Override
	public boolean logout() {
		return false;
	}

	public void setNewPwd(String pwd, String oldpwd) {
		ControlerFactory.getAgentControler().newPWD((oldpwd), (pwd), this);
	}

	public void setNewEmail(String email) {
		this.email = email;
		update();
	}

	public void update() {
		ControlerFactory.getAgentControler().update(this);
	}

}
