package com.paypal.project.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.collect.Lists;
import com.paypal.project.domain.StringSet;
import com.paypal.project.domain.UnicodeString;
import com.paypal.project.service.IService;
import com.paypal.project.utils.ConstantTools;

/**
 *
 * @author guojg
 */
@Controller
@RequestMapping(value = "/services")
public class ServiceController {

	@Autowired
	private IService service;

	// register, get
	@RequestMapping(value = "/register/{unicodeString}", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, UnicodeString> registerStrings(@PathVariable(value = "unicodeString") String uniStr) {
		return register(uniStr);
	}

	// register, post
	@RequestMapping(value = "/register/{unicodeString}", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, UnicodeString> registerStringsPost(@PathVariable(value = "unicodeString") String uniStr) {
		return register(uniStr);
	}

	// register
	private HashMap<String, UnicodeString> register(String uniStr) {
		HashMap<String, UnicodeString> map = new HashMap<>();
		UnicodeString registerStrings = service.registerStrings(uniStr);
		map.put(ConstantTools.PREFIX_REG, registerStrings);
		return map;
	}

	// fetch, get
	@RequestMapping(value = "/fetch/{stringID}", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, List<UnicodeString>> fetchStrings(@PathVariable(value = "stringID") String strID) {
		return fetch(strID);
	}

	// fetch, post
	@RequestMapping(value = "/fetch/{stringID}", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, List<UnicodeString>> fetchStringsPost(@PathVariable(value = "stringID") String strID) {
		return fetch(strID);
	}

	// fetchID
	private HashMap<String, List<UnicodeString>> fetch(String strID) {
		List<String> fetchStrings = service.fetchStrings(strID);
		if (fetchStrings == null) {
			return null;
		}
		List<UnicodeString> list = Lists.newArrayList();
		for (String str : fetchStrings) {
			list.add(new UnicodeString(str, Integer.parseInt(strID)));
		}
		HashMap<String, List<UnicodeString>> map = new HashMap<>();
		map.put(ConstantTools.PREFIX_FETCHID, list);
		return map;
	}

	// fetch all, get
	@RequestMapping(value = "/fetchall", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, List<StringSet>> fetchAll() {
		return fetchAllContent();
	}

	// fetch all, post
	// {"stringID":96,"strings":["00","`"]}
	@RequestMapping(value = "/fetchall", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, List<StringSet>> fetchAllPost() {
		return fetchAllContent();
	}

	// fetch all
	private HashMap<String, List<StringSet>> fetchAllContent() {
		Map<Integer, List<String>> fetchAll = service.fetchAll();
		if (fetchAll == null) {
			return null;
		}
		Set<Entry<Integer, List<String>>> entrySet = fetchAll.entrySet();
		Iterator<Entry<Integer, List<String>>> iterator = entrySet.iterator();
		List<StringSet> list = Lists.newArrayList();
		while (iterator.hasNext()) {
			Entry<Integer, List<String>> next = iterator.next();
			Integer key = next.getKey();
			List<String> value = next.getValue();
			StringSet stringSet = new StringSet(key, value);
			list.add(stringSet);
		}
		HashMap<String, List<StringSet>> map = new HashMap<>();
		map.put(ConstantTools.PREFIX_FETCHALL, list);
		return map;
	}

}
