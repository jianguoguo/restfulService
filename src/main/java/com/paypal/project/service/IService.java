package com.paypal.project.service;

import java.util.List;
import java.util.Map;

import com.paypal.project.domain.UnicodeString;

/**
 *
 * @author guojg
 */
public interface IService {

	UnicodeString registerStrings(String str);

	List<String> fetchStrings(String stringID);

	Map<Integer, List<String>>  fetchAll();
}
