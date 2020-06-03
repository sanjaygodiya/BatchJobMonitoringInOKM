package com.inquira.infomanager.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.inquira.infomanager.config.*;

public class BatchMonitoringData {
	private static final Logger log = Logger.getLogger(BatchMonitoringData.class);

	private static final String sql = "select bj2.jobname, bs2.datefired ,bj2.delay , bs2.status from (select bj.jobname, max(bs.datefired) lastfiredate from batchjobs bj join batchstatus bs on bs.jobid=bj.recordid group by bj.jobname) tab1 join batchjobs bj2 on bj2.jobname=tab1.jobname join batchstatus bs2 on bs2.jobid=bj2.recordid where tab1.lastfiredate=bs2.datefired";

	public static Connection getDatbaseConnection() throws IOException {
		Connection conn = null;
		String driver = ReadConfig.getPropValues("jdbc.driver");
		if (driver != null) {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				log.info(e.getMessage());
			}
		}
		String url = ReadConfig.getPropValues("jdbc.url");
		String username = ReadConfig.getPropValues("jdbc.username");
		String password = ReadConfig.getPropValues("jdbc.password");
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			log.info(e.getMessage());
		}
		return conn;
	}

	public static JSONArray getBatchJobData() throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		JSONArray jsonArray = new JSONArray();
		try {
			conn = getDatbaseConnection();
			if(conn!=null)
			log.info("Database connection successful....");
		} catch (IOException e1) {
			log.info("Unable to make database connection :" + e1.getMessage());
		}
		try {
			stmt = conn.prepareStatement(sql);
		} catch (SQLException localSQLException1) {
		}
		try {
			result = stmt.executeQuery();
			log.info("SQL query executed successfully....");
			log.debug(sql);
		} catch (SQLException e1) {
			log.info("Unable to execute SQL query : " + e1.getMessage());
			log.debug(sql);
		}
		try {
			while (result.next()) {
				String value1 = result.getString("jobname");
				String value2 = result.getString("datefired");
				String value3 = result.getString("delay");
				String value4 = result.getString("status");
				JSONObject jObj = new JSONObject();
				jObj.put("jobname", value1);
				jObj.put("datefired", value2);
				jObj.put("delay", value3);
				jObj.put("status", value4);
				jsonArray.put(jObj);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

}
