package com.framework.selenium.util;

import java.util.Properties;
import java.util.TreeMap;

public class LoadDataProperties {
	 private TreeMap<String, String> webDataMapping = new TreeMap<String, String>();

	    public TreeMap<String, String> getWebDataMapping(String path) throws Exception {
	        Properties propData = new Properties();
	        // propData.load(this.getClass().getResourceAsStream("/prop/threads/threads_data.properties"));
	        propData.load(this.getClass().getResourceAsStream(path));
	        for (String key : propData.stringPropertyNames()) {
	            String value = propData.getProperty(key);
	            webDataMapping.put(key, value);
	        }
	        return webDataMapping;
	    }
}
