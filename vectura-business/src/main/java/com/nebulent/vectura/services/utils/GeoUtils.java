/**
 * 
 */
package com.nebulent.vectura.services.utils;

import java.util.ArrayList;
import java.util.List;

import com.nebulent.vectura.data.model.mongodb.Place;

/**
 * @author mfedorov
 *
 */
public final class GeoUtils {

	/**
	 * @param start
	 * @param end
	 * @return
	 */
	public static double distance(Place start, Place end) {
		return distance(start.getLocation()[1], end.getLocation()[1], start.getLocation()[0], end.getLocation()[0], 0D, 0D);
	}
	
	/**
	 * @param current
	 * @param locations
	 * @param radius
	 * @return
	 */
	public static List<Place> withinRadius(Place current, List<Place> locations, double radius) {
	    List<Place> results = new ArrayList<Place>();
	    for (Place loc : locations) {
	        if (distance(current, loc) <= radius) {
	            results.add(loc);
	        }
	    }
	    return results;
	}
	
	/**
	 * Calculate distance between two points in latitude and longitude taking
	 * into account height difference. If you are not interested in height
	 * difference pass 0.0. Uses Haversine method as its base.
	 * 
	 * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
	 * el2 End altitude in meters.
	 * 
	 * @param lat1
	 * @param lat2
	 * @param lon1
	 * @param lon2
	 * @param el1
	 * @param el2
	 * @return
	 */
	public static double distance(double lat1, double lat2, double lon1, double lon2,
	        double el1, double el2) {

	    final int R = 3959; // Radius of the earth is 6371 meters or 3959 miles
	    
	    Double latDistance = deg2rad(lat2 - lat1);
	    Double lonDistance = deg2rad(lon2 - lon1);
	    Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    double height = el1 - el2;
	    distance = Math.pow(distance, 2) + Math.pow(height, 2);
	    return Math.sqrt(distance);
	}

	/**
	 * @param deg
	 * @return
	 */
	private static double deg2rad(double deg) {
	    return (deg * Math.PI / 180.0);
	}
}
