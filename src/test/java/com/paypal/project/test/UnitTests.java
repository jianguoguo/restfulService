package com.paypal.project.test;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.paypal.project.utils.ConstantTools;

public class UnitTests {

	@Before
	public void setUp() {
		RestAssured.basePath = "http://localhost:8080/restfulService/services";
	}

	@Test
	public void testRegister() {
		String str = ConstantTools.PREFIX_REG + ".unicodeStr";
		String strID = ConstantTools.PREFIX_REG + ".stringID";
		// get
		expect().body(str, equalTo("abc")).when().get(RestAssured.basePath + "/register/abc");
		// post
		expect().body(str, equalTo("abc")).when().post(RestAssured.basePath + "/register/abc");
		// get
		expect().body(strID, equalTo(587)).when().get(RestAssured.basePath + "/register/abbc");
		// post
		expect().body(strID, equalTo(587)).when().post(RestAssured.basePath + "/register/abbc");
	}

	@Test
	public void testFetch() {
		String str = ConstantTools.PREFIX_FETCHID + ".unicodeStr";
		String strID = ConstantTools.PREFIX_FETCHID + ".stringID";
		// get
		expect().body(str, hasItem("abc")).when().get(RestAssured.basePath + "/fetch/489");
		// post
		expect().body(str, hasItem("abc")).when().post(RestAssured.basePath + "/fetch/489");
		// get
		expect().body(strID, hasItem(489)).when().get(RestAssured.basePath + "/fetch/489");
		// post
		expect().body(strID, hasItem(489)).when().post(RestAssured.basePath + "/fetch/489");
	}

	@Test
	public void testFetchAll() {
		String str = ConstantTools.PREFIX_FETCHALL + ".stringID";
		// get
		expect().body(str, hasItem(97)).when().get(RestAssured.basePath + "/fetchall");
		// post
		expect().body(str, hasItem(97)).when().post(RestAssured.basePath + "/fetchall");
	}

}