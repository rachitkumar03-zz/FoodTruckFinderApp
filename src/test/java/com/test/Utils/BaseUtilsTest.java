package com.test.Utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.application.utils.BaseUtils;

public class BaseUtilsTest {

	private BaseUtils baseUtils;
	
	@Before
	public void setup() {
		baseUtils = new BaseUtils();
	}
	
	@Test
	public void testIsValidRequest() {
		String positive = "1:2";
		String negative = "12";
		
		assertEquals("isValidRequest failed positive test",true, baseUtils.isValidRequest(positive));
		assertEquals("isValidRequest failed Negative test",false, baseUtils.isValidRequest(negative));
	}
	
	@After
	public void destroy() {
		baseUtils = null;
	}
}
