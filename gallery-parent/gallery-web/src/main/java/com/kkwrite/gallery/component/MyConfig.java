package com.kkwrite.gallery.component;

/**
 * gallery.properties 配置文件 
 */
//@Configuration
public class MyConfig {

	//@Value("${test.testParam}")
	private String testParam;

	public String getTestParam() {
		return testParam;
	}

	public void setTestParam(String testParam) {
		this.testParam = testParam;
	}
	
	
}
