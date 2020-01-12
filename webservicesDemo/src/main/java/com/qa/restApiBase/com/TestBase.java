package com.qa.restApiBase.com;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

public class TestBase {

	public Properties prop;
/*the constructor loads the property file and prop object reads it*/
	public TestBase() {
		try {
			prop = new Properties();
			FileReader reader = new FileReader(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
