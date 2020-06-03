package com.inquira.infomanager.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.inquira.infomanager.dao.BatchMonitoringData;

public class GenerateMessage {
	private static final Logger log = Logger.getLogger(GenerateMessage.class);

	public static String getMessage() throws JSONException {
		String header = "<html><head><style>table,th,td{border:1px solid black;border-collapse:collapse;}</style></head><body><table style=width:55% align=left><tr><th align=left>Job Name</th><th align=left>Last Execution Date Time (CST)</th><th align=left>Frequency(in Mins)</th><th align=left>Current System Date Time (CST)</th><th align=left>Status</th></tr>";
		String footer = "</table></body></html>";
		String rows = "";
		String message = "";
		JSONArray jsonArray = new JSONArray();
		try {
			jsonArray = BatchMonitoringData.getBatchJobData();
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		String systime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		for (int i = 0; i < jsonArray.length(); i++) {
			String jobname = null;
			String lastrun = null;
			String delay = null;
			String status = null;
			JSONObject jObj = jsonArray.getJSONObject(i);

			jobname = jObj.getString("jobname");
			lastrun = jObj.getString("datefired");
			delay = jObj.getString("delay");
			status = jObj.getString("status");
			rows = rows + "<tr><td>" + jobname + "</td><td>" + lastrun + "</td><td>" + delay + "</td><td>" + systime
					+ "</td><td>" + status + "</td></tr>";
		}
		message = header + rows + footer;
		return (message);

	}

}
