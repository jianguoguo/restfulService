package com.paypal.project.domain;

import java.io.Serializable;

/**
 *
 * @author guojg
 */
public class UnicodeString implements Serializable {

	private static final long serialVersionUID = 1L;
	private String unicodeStr;
	private Integer stringID;

	public UnicodeString(String unicodeStr, Integer stringID) {
		this.unicodeStr = unicodeStr;
		this.stringID = stringID;
	}

	public UnicodeString(String unicodeStr) {
		this.unicodeStr = unicodeStr;
		this.stringID = 0;
	}

	public String getUnicodeStr() {
		return unicodeStr;
	}

	public void setUnicodeStr(String unicodeStr) {
		this.unicodeStr = unicodeStr;
	}

	public Integer getStringID() {
		return stringID;
	}

	public void setStringID(Integer stringID) {
		this.stringID = stringID;
	}

}
