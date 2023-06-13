package com.xyz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class DatabaseUtil {

	public DatabaseUtil() {
		dataFromDb();
	}

	public static void main(String[] args) {

	}

	public List<InputData> dataFromDb() {

		MysqlConnect mysqlConnect = new MysqlConnect();
		List<InputData> lstInputData = new LinkedList<InputData>();
		String sql = "SELECT * FROM `employee_info`";
		try {
			PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				InputData inputData = new InputData();
				inputData.setId(String.valueOf(rs.getInt("id")));
				inputData.setName(String.valueOf(rs.getString("name")));
				inputData.setSirname(String.valueOf(rs.getString("sirname")));
				inputData.setSalary(String.valueOf(rs.getInt("salary")));
				inputData.setCity(String.valueOf(rs.getString("city")));
				lstInputData.add(inputData);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}
		return lstInputData;
	}
}
