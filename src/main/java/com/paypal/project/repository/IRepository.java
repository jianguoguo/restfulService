package com.paypal.project.repository;

import java.util.List;
import java.util.Map;

import com.paypal.project.domain.UnicodeString;

/**
 *
 * @author guojg
 */
public interface IRepository {

	boolean storge(UnicodeString domain);

	List<String> fetch(Integer stringID);

	Map<Integer, List<String>> fetchAll();

}
