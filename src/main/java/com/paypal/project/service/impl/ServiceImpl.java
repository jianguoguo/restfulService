package com.paypal.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.project.domain.UnicodeString;
import com.paypal.project.repository.IRepository;
import com.paypal.project.service.IService;

/**
 *
 * @author guojg
 */
@Service
public class ServiceImpl implements IService {

	@Autowired
	IRepository iRepository;

	@Override
	public UnicodeString registerStrings(String str) {
		UnicodeString domain = new UnicodeString(str);
		getStringID(domain, 0, 1);
		iRepository.storge(domain);
		return domain;
	}

	// recursive
	private int getStringID(UnicodeString domain, int first, int second) {
		String unicodeStr = domain.getUnicodeStr();
		if (unicodeStr == null || unicodeStr.length() == 0) {
			return 0;
		}
		if (first == 0) {
			domain.setStringID(domain.getStringID() + unicodeStr.charAt(first));
		}
		if (second >= unicodeStr.length()) {
			return domain.getStringID();
		}
		if (unicodeStr.charAt(first) == unicodeStr.charAt(second)) {
			domain.setStringID(domain.getStringID() + unicodeStr.charAt(second));
		} else {
			domain.setStringID(domain.getStringID() + unicodeStr.charAt(first));
			domain.setStringID(domain.getStringID() + unicodeStr.charAt(second));
		}
		first++;
		second++;
		getStringID(domain, first, second);
		return domain.getStringID();
	}

	public static void main(String[] args) {
		// System.out.println(getStringID(new Domain("abbc", 0), 0, 1));
	}

	@Override
	public List<String> fetchStrings(String stringID) {
		try {
			Integer id = Integer.parseInt(stringID);
			List<String> list = iRepository.fetch(id);
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Map<Integer, List<String>> fetchAll() {
		return iRepository.fetchAll();
	}

}
