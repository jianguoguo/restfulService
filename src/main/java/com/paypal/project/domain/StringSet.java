package com.paypal.project.domain;

import java.util.List;

public class StringSet {
	private Integer stringID;
	private List<String> strings;

	public StringSet(Integer stringID, List<String> strings) {
		super();
		this.stringID = stringID;
		this.strings = strings;
	}

	public StringSet() {
	}

	public Integer getStringID() {
		return stringID;
	}

	public void setStringID(Integer stringID) {
		this.stringID = stringID;
	}

	public List<String> getStrings() {
		return strings;
	}

	public void setStrings(List<String> strings) {
		this.strings = strings;
	}

}
