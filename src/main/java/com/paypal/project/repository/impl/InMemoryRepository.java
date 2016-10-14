package com.paypal.project.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.paypal.project.domain.UnicodeString;
import com.paypal.project.repository.IRepository;
import com.paypal.project.utils.StorageTool;

/**
 *
 * @author guojg
 */
@Repository
public class InMemoryRepository implements IRepository {

	private final Map<String, UnicodeString> mapOfDomain;
	private final Map<Integer, List<String>> listOfDomains;

	public InMemoryRepository() {
		mapOfDomain = StorageTool.fetchMap1();
		listOfDomains = StorageTool.fetchMap2();
	}

	@Override
	public boolean storge(UnicodeString domain) {
		if (mapOfDomain.containsKey(domain.getUnicodeStr())) {
			return true;
		}
		mapOfDomain.put(domain.getUnicodeStr(), domain);
		List<String> list = listOfDomains.get(domain.getStringID());
		if (list == null) {
			list = new ArrayList<>();
		}
		list.add(domain.getUnicodeStr());
		listOfDomains.put(domain.getStringID(), list);
		// store
		StorageTool.storeMap(mapOfDomain, listOfDomains);
		return true;
	}

	@Override
	public List<String> fetch(Integer stringID) {
		return listOfDomains.get(stringID);
	}

	@Override
	public Map<Integer, List<String>> fetchAll() {
		return listOfDomains;
	}

}
