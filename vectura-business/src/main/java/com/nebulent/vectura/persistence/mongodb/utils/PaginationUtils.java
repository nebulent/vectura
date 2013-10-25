/**
 * 
 */
package com.nebulent.vectura.persistence.mongodb.utils;

import org.springframework.util.Assert;

/**
 * @author Max Fedorov
 *
 */
public class PaginationUtils {

	/**
	 * @param count
	 * @param pageSize
	 * @return
	 */
	public static int getTotalNumberOfPages(int count, int pageSize) {
		Assert.isTrue(count >= 0 && pageSize > 0);
		if(count == 0) {
			return 0;
		}
		int totalNumOfPages = count / pageSize;
		if((count % pageSize) > 0) {
			totalNumOfPages++;
		}
		return totalNumOfPages;
	}
	
	/**
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	public static int getFirstResult(int pageNumber, int pageSize) {
		Assert.isTrue(pageNumber > 0 && pageSize > 0);
		return pageNumber == 1 ? 0 : (pageNumber * pageSize - pageSize);
	}
}
