/**
 * 
 */
package com.nebulent.vectura.data.model.mongodb;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nebulent.vectura.persistence.mongodb.CoreRepository;

/**
 * @author mfedorov
 *
 */
@Document(collection=CoreRepository.COLLECTION_LOCATIONS)
@CompoundIndexes({
    @CompoundIndex(name = "searchByZip_idx", def = "{'accountUuid': 1, 'name': 1, 'address.zipCode': 1}"),
    @CompoundIndex(name = "searchByHash_idx", def = "{'name': 1, 'address.hash': 1}")
})
public class Location extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4952308266074076699L;
	
	@Indexed
	private String accountUuid;
	private String patientUuid;
	private String name;
	private AddressInfo address;
	
	/**
	 * @return the accountUuid
	 */
	public String getAccountUuid() {
		return accountUuid;
	}
	/**
	 * @param accountUuid the accountUuid to set
	 */
	public void setAccountUuid(String customerUuid) {
		this.accountUuid = customerUuid;
	}
	/**
	 * @return the patientUuid
	 */
	public String getPatientUuid() {
		return patientUuid;
	}
	/**
	 * @param patientUuid the patientUuid to set
	 */
	public void setPatientUuid(String patientUuid) {
		this.patientUuid = patientUuid;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the address
	 */
	public AddressInfo getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(AddressInfo address) {
		this.address = address;
	}
}
