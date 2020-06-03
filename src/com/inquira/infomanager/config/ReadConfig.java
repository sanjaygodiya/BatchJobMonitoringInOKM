package com.inquira.infomanager.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class ReadConfig {

	private static final Logger log = Logger.getLogger(ReadConfig.class);

	public static String getPropValues(String propName) throws IOException {

		String path = System.getProperty("user.dir");
		Properties props = new Properties();
		FileInputStream inputStream = null;
		String propValue = null;
		try {
			inputStream = new FileInputStream(path + "/resources/configuration.properties");
			try {
				props.load(inputStream);
				propValue = props.getProperty(propName);
			} catch (IOException e) {
				log.info(e.getMessage());
			}

		} catch (FileNotFoundException e) {
			log.info(e.getMessage());
		} finally {
			inputStream.close();
		}
		return propValue;

	}

}
