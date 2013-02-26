package com.surgeryassist.services.interfaces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import com.surgeryassist.core.SearchType;
import com.surgeryassist.core.entity.DayAvailability;
import com.surgeryassist.core.entity.TimeAvailabilities;

/**
 * Allows for searching for different records
 * based on parameters and passed in values.
 * 
 * @author Nick Karpov
 */
public interface SearchService {
	
	/**
	 * Returns a dump of all day availabilities
	 * @return {@link ArrayList} of {@link DayAvailability}
	 */
	public List<TimeAvailabilities> searchAll();
	
	/**
	 * Returns a list of {@link DayAvailability}
	 * for a specific city (fuzzy search)
	 * @param city String of city name
	 * @return {@link ArrayList} of {@link DayAvailability}
	 */
	public List<TimeAvailabilities> searchByCriteria(String city, String zipCode, 
			Date startDate, Date endDate);
	
	/**
	 * Creates a map containing all SelectItems used by the
	 * front end. Currently includes the following:
	 * <ul>
	 * 	<li>{@link SearchType}</li>
	 * </ul>
	 * @return
	 */
	public Map<String, List<SelectItem>> getSelectItemValues();

}