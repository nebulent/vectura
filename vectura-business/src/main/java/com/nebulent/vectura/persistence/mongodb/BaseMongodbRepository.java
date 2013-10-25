/**
 * 
 */
package com.nebulent.vectura.persistence.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.QueryMapper;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.nebulent.vectura.persistence.mongodb.CoreRepository.FindAndModifyRequest;
import com.nebulent.vectura.persistence.mongodb.utils.PaginationUtils;

/**
 * @author Max Fedorov
 *
 */
public abstract class BaseMongodbRepository {
	
	protected static final String ID = "_id";
	
	@Autowired
	protected MongoTemplate mongoTemplate;
	protected QueryMapper queryMapper;
	private DB db;
	private String username;
	private String password;
	
	/**
	 * 
	 */
	protected void init() {
		db = mongoTemplate.getDb();
		queryMapper = new QueryMapper(mongoTemplate.getConverter());
	}

	/**
	 * @param collectionName
	 * @return
	 */
	protected DBCollection getDbCollection(String collectionName) {
		return db.getCollection(collectionName);
	}
	
	/**
	 * @param ids
	 * @return
	 */
	protected Query getByIdsQuery(List<String> ids){
		Criteria criteria = Criteria.where(ID).in(ids);
		return Query.query(criteria);
	}
	
	/**
	 * @param ids
	 * @param clazz
	 * @return
	 */
	protected <T> List<T> getByIds(List<String> ids, Class<T> clazz){
		return mongoTemplate.find(getByIdsQuery(ids), clazz);
	}
	
	/**
	 * * @param <T> * @param request * @param converter * @param collectionName
	 * * @return
	 */
	protected <T> T findAndModify(Class<T> clazz, FindAndModifyRequest request, String collectionName) {
		DBObject updateObj = request.getUpdateObject();
		for (String key : updateObj.keySet()) {
			updateObj.put(
					key,
					mongoTemplate.getConverter().convertToMongoType(
							updateObj.get(key)));
		}
		DBObject queryObj = (request.getQuery() == null) ? new BasicDBObject()
				: queryMapper.getMappedObject(request.getQueryObject(),
						mongoTemplate.getConverter().getMappingContext()
								.getPersistentEntity(clazz));
		DBObject dbObject = getDbCollection(collectionName).findAndModify(
				/* request.getQueryObject() */queryObj,
				request.getFieldsObject(), request.getSortObject(),
				request.isRemove(), /* request.getUpdateObject() */updateObj,
				request.isReturnNew(), request.isUpsert());
		if (dbObject == null) {
			return null;
		}
		return mongoTemplate.getConverter().read(clazz, dbObject);
	}

	/**
	 * @param id
	 * @return
	 */
	protected DBObject getByIdQuery(Object id) {
		return new BasicDBObject(ID, id);
	}
	
	/**
	 * @param prefix
	 * @return
	 */
	protected String getFullIdField(String prefix) {
		return prefix + "." + ID;
	}
	
    /**
     * @param query
     * @param pageRequest
     */
	protected void adjustQueryForPagination(Query query, org.springframework.data.domain.PageRequest pageRequest) {
        if (pageRequest == null) {
            return;
        }
        int firstResult = PaginationUtils.getFirstResult(pageRequest.getPageNumber(), pageRequest.getPageSize());
        if (firstResult > 0) {
            query.skip(firstResult - 1);
        }
        if(pageRequest.getSort() != null){
        	query.with(pageRequest.getSort());
        }
        query.limit(pageRequest.getPageSize());
    }
    
    /**
     * @param query
     * @param limit
     */
	protected void adjustQueryForLimit(Query query, Integer limit) {
        if (limit == null || limit.intValue() <= 0) {
            return;
        }
        query.limit(limit.intValue());
    }
	
	/**
	 * @param query
	 * @return
	 */
	protected <T> DBObject toDBObjectQuery(Class<T> clazz, Query query) {
		return query == null ? new BasicDBObject() : queryMapper.getMappedObject(query.getQueryObject(), mongoTemplate.getConverter().getMappingContext().getPersistentEntity(clazz));
	}
	
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the db
	 */
	public DB getDb() {
		return db;
	}

	/**
	 * @param mongoTemplate the mongoTemplate to set
	 */
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
}