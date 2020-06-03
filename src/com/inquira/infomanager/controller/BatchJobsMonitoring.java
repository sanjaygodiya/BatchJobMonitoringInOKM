package com.inquira.infomanager.controller;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.json.JSONException;

import com.inquira.infomanager.mail.SendMail;
import com.inquira.infomanager.util.GenerateMessage;

public class BatchJobsMonitoring {

	private static final Logger log = Logger.getLogger(BatchJobsMonitoring.class);

	public static void main(String[] args) {
		BasicConfigurator.configure();
		String message =null;
		System.out.println("Process started...");
		try {
			message = GenerateMessage.getMessage();
		} catch (JSONException e) {
			log.info(e.getMessage());
		}
		
		try {
			SendMail.sendMail(message);
		} catch (IOException e) {
			log.info(e.getMessage());
		}
		System.out.println("Process complete...");
	}

}
