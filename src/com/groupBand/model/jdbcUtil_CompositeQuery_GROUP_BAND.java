package com.groupBand.model;

import java.util.*;

/**
 * Servlet implementation class jdbcUtil_CompositeQuery_GROUP_BAND
 */
public class jdbcUtil_CompositeQuery_GROUP_BAND {
	private static final long serialVersionUID = 1L;
       
	public static String get_aCondition_For_Oracle(String columnName, String value) {
		
		String aCondition = null;
		
		if("GROUP_KIND".equals(columnName))
		{
			aCondition = columnName + "=" + value;
			
		}
		
		
		
		if("GROUP_TYPE".equals(columnName))
		{
			aCondition = columnName + "=" + "'"+value+"'";
			
		}else if("GROUP_NAME".equals(columnName)||"START_LOC".equals(columnName))
		{
			aCondition = columnName + " like '%" + value + "%'";			
		}else if("START_TIME".equals(columnName))
		{
			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
		}	
		return aCondition + " ";
	
	}
	
	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		return whereCondition.toString();
	}
	
    public jdbcUtil_CompositeQuery_GROUP_BAND() {
        super();
     
    }

	
	

}
