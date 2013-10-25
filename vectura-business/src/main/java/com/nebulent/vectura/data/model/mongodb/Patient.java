/**
 * 
 */
package com.nebulent.vectura.data.model.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nebulent.vectura.persistence.mongodb.CoreRepository;


/**
 * @author mfedorov
 *
 */
@Document(collection=CoreRepository.COLLECTION_PATIENTS)
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
