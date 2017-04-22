package com.fdm.users;

import org.mindrot.jbcrypt.BCrypt;

public class encrypt {

	public static String salt() {
		return BCrypt.gensalt();
	}

	public static String hash(String pwd, String salt) {
		return BCrypt.hashpw(pwd, salt);
	}

	// @Deprecated
	public static boolean check(String pwd, String hash) {
		if (BCrypt.checkpw(pwd, hash))
			return true;
		else
			return false;
	}
}
