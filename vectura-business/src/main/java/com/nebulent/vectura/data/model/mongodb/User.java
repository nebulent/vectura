/**
 * 
 */
package com.nebulent.vectura.data.model.mongodb;

import java.util.Date;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nebulent.vectura.persistence.mongodb.CoreRepository;

/**
 * @author mfedorov
 *
 */
@Document(collection=CoreRepository.COLLECTION_USERS)
public class User extends Contact{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5848544211951791694L;

	@Indexed
	private String username;
	@Indexed
	private String accountUuid;
	
	private String pwdHash;
	private Date lastLogin;
	private int loginAttempts;
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the pwdHash
	 */
	public String getPwdHash() {
		return pwdHash;
	}
	/**
	 * @param pwdHash the pwdHash to set
	 */
	public void setPwdHash(String pwdHash) {
		this.pwdHash = pwdHash;
	}
	/**
	 * @return the lastLogin
	 */
	public Date getLastLogin() {
		return lastLogin;
	}
	/**
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	/**
	 * @return the loginAttempts
	 */
	public int getLoginAttempts() {
		return loginAttempts;
	}
	/**
	 * @param loginAttempts the loginAttempts to set
	 */
	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}
}
