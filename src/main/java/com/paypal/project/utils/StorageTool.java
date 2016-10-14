package com.paypal.project.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.Lists;
import com.paypal.project.domain.UnicodeString;

public class StorageTool {
	static Map<String, UnicodeString> map = null;
	static Map<Integer, List<String>> map2 = null;

	public static boolean storeMap(Map<String, UnicodeString> map, Map<Integer, List<String>> map2) {
		StorageTool.map = map;
		StorageTool.map2 = map2;
		Collection<UnicodeString> values = map.values();
		List<String> lines = Lists.newArrayList();
		// map1, key,value;key2,value2;...
		StringBuffer stringBuffer = new StringBuffer("");
		for (UnicodeString domain : values) {
			stringBuffer.append(";" + domain.getUnicodeStr() + "," + domain.getStringID());
		}
		lines.add(stringBuffer.toString().substring(1));
		// map2, key:val1,val2;key2:val3,val4;...
		StringBuffer stringBuffer2 = new StringBuffer("");
		Set<Entry<Integer, List<String>>> entrySet = map2.entrySet();
		for (Entry<Integer, List<String>> entry : entrySet) {
			Integer key = entry.getKey();
			List<String> value = entry.getValue();
			StringBuffer sb = new StringBuffer("");
			for (String str : value) {
				sb.append(",").append(str);
			}
			stringBuffer2.append(";" + key + ":" + sb.toString().substring(1));
		}
		lines.add(stringBuffer2.toString().substring(1));
		try {
			File file = new File(getPropValues());
			if (!file.exists()) {
				file.createNewFile();
			}
			FileUtils.writeLines(file, "UTF-8", lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static Map<String, UnicodeString> fetchMap1() {
		fetchMap();
		return map;
	}

	public static Map<Integer, List<String>> fetchMap2() {
		fetchMap();
		return map2;
	}

	@SuppressWarnings("unchecked")
	private static boolean fetchMap() {
		// cache
		if (map != null && map2 != null) {
			return true;
		}
		map = new HashMap<>();
		map2 = new HashMap<>();
		List<String> lines;
		try {
			File file = new File(getPropValues());
			if (!file.exists()) {
				file.createNewFile();
			}
			// first line is map
			// second line is map2
			lines = FileUtils.readLines(file, "UTF-8");
			if (lines.size() != 2) {
				return false;
			}
			// map1, key,value;key2,value2;...
			String firstLine = lines.get(0);
			String secondLine = lines.get(1);
			String[] split = firstLine.split(";");
			for (String str : split) {
				String[] split2 = str.split(",");
				UnicodeString domain = new UnicodeString(split2[0], Integer.parseInt(split2[1]));
				map.put(split2[0], domain);
			}
			// map2, key:val1,val2;key2:val3,val4;...
			String[] split2 = secondLine.split(";");
			for (String str : split2) {
				String[] split3 = str.split(":");
				String[] split4 = split3[1].split(",");
				List<String> list = new ArrayList<>();
				for (String str2 : split4) {
					list.add(str2);
				}
				map2.put(Integer.parseInt(split3[0]), list);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	private static String getPropValues() {
		InputStream inputStream = null;
		String filePath = "";
		try {
			Properties prop = new Properties();
			inputStream = StorageTool.class.getClassLoader().getResourceAsStream(ConstantTools.PROP_FILENAME);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException(
						"property file '" + ConstantTools.PROP_FILENAME + "' not found in the classpath");
			}

			filePath = prop.getProperty("com.paypal.project.filePath");
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return filePath;
	}

}
