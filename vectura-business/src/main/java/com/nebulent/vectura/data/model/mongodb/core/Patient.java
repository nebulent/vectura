/**
 * 
 */
package com.nebulent.vectura.data.model.mongodb.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;


/**
 * @author mfedorov
 *
 */
public class Patient extends Contact {

	/**
	 * 
	 */
	private static final long serialVersionUID = 859867101419996730L;
	
	@Indexed
	private String ssn;
	private List<String> locUuids = new ArrayList<String>();
	
	/**
	 * @return the ssn
	 */
	public String getSsn() {
		return ssn;
	}
	/**
	 * @param ssn the ssn to set
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	/**
	 * @return the locUuids
	 */
	public List<String> getLocUuids() {
		return locUuids;
	}
}
