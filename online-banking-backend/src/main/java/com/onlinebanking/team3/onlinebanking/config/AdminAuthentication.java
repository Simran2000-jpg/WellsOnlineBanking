package com.onlinebanking.team3.onlinebanking.config;

import com.onlinebanking.team3.onlinebanking.exception.UnauthorizedAccessException;
import org.apache.tomcat.util.codec.binary.Base64;

public class AdminAuthentication {
	public static void authenticateAdminCredentials(String credentials) throws UnauthorizedAccessException {

			String pair = new String(Base64.decodeBase64(credentials.substring(6)));
			if(pair.split(":").length!=2){
				throw new UnauthorizedAccessException("User Not Authorized for this route");
			}
			String userName = pair.split(":")[0];
			String password = pair.split(":")[1];


			if (!userName.equals("admin") || !password.equals("f3rokq034!@#$qu2")) {
				throw new UnauthorizedAccessException("User Not Authorized for this route");
			} else {
				System.out.println(userName + ", " + password);
			}

	}
}
