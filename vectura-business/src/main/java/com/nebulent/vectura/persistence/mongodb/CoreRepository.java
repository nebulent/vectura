/**
 * 
 */
package com.nebulent.vectura.persistence.mongodb;

import java.io.Serializable;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.geo.GeoResults;
import org.springframework.data.mongodb.core.query.Field;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.DBObject;
import com.nebulent.vectura.data.model.mongodb.Location;
import com.nebulent.vectura.persistence.repositories.mongodb.AccountRepository;
import com.nebulent.vectura.persistence.repositories.mongodb.LocationRepository;
import com.nebulent.vectura.persistence.repositories.mongodb.RideRepository;
import com.nebulent.vectura.persistence.repositories.mongodb.RunRepository;

/**
 * @author Max Fedorov
 *
 */
public interface CoreRepository {

	
	public static final String COLLECTION_ACCOUNTS = "accounts";
	public static final String COLLECTION_VEHICLES = "vehicles";
	public static final String COLLECTION_USERS = "users";
	public static final String COLLECTION_LOCATIONS = "locations";
	public static final String COLLECTION_PATIENTS = "patients";
	public static final String COLLECTION_RIDES = "rides";
	public static final String COLLECTION_RUNS = "runs";
	
	/**
	 * @return
	 */
	public AccountRepository getAccountRepository();
	
	/**
	 * @return
	 */
	public LocationRepository getLocationRepository();
	
	/**
	 * @return
	 */
	public RideRepository getRideRepository();
	
	/**
	 * @return
	 */
	public RunRepository getRunRepository();
	
	/**
	 * @param accountUuid
	 * @param position
	 * @return
	 */
	public GeoResults<Location> getLocationsByDistance(String accountUuid, double[] position);

	/**
	 * @author Max Fedorov
	 *
	 */
	public static class FindAndModifyRequest implements Serializable {
		
		/**/
		private static final long serialVersionUID = 1L;
		
		private Query query; 
		private Field field; 
		private Sort sort;
		private Update update;
		private boolean remove; 
		private boolean returnNew; 
		private boolean upsert;
		
		/**
		 * 
		 */
		public FindAndModifyRequest() {
		}

		/**
		 * @return
		 */
		public DBObject getQueryObject() {
			return query != null ? query.getQueryObject() : null;
		}
		
		/**
		 * @return
		 */
		public DBObject getFieldsObject() {
			return field != null ? field.getFieldsObject() : null;
		}
		
		/**
		 * @return
		 */
		public DBObject getSortObject() {
			return sort != null ? (new Query().with(sort).getSortObject()) : null;
		}
		
		/**
		 * @return
		 */
		public DBObject getUpdateObject() {
			return update != null ? update.getUpdateObject() : null;
		}
		
		/**
		 * @return the query
		 */
		public Query getQuery() {
			return query;
		}

		/**
		 * @param query the query to set
		 */
		public void setQuery(Query query) {
			this.query = query;
		}

		/**
		 * @return the sort
		 */
		public Sort getSort() {
			return sort;
		}

		/**
		 * @param sort the sort to set
		 */
		public void setSort(Sort sort) {
			this.sort = sort;
		}

		/**
		 * @return the update
		 */
		public Update getUpdate() {
			return update;
		}

		/**
		 * @param update the update to set
		 */
		public void setUpdate(Update update) {
			this.update = update;
		}

		/**
		 * @return the remove
		 */
		public boolean isRemove() {
			return remove;
		}

		/**
		 * @param remove the remove to set
		 */
		public void setRemove(boolean remove) {
			this.remove = remove;
		}

		/**
		 * @return the returnNew
		 */
		public boolean isReturnNew() {
			return returnNew;
		}

		/**
		 * @param returnNew the returnNew to set
		 */
		public void setReturnNew(boolean returnNew) {
			this.returnNew = returnNew;
		}

		/**
		 * @return the upsert
		 */
		public boolean isUpsert() {
			return upsert;
		}

		/**
		 * @param upsert the upsert to set
		 */
		public void setUpsert(boolean upsert) {
			this.upsert = upsert;
		}

		/**
		 * @return the field
		 */
		public Field getField() {
			return field;
		}

		/**
		 * @param field the field to set
		 */
		public void setField(Field field) {
			this.field = field;
		}
	}	
}
