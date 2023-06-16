package com.xyz;

import java.io.FileWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CompareDataMain {

	public static final String FILE_PATH = "TestData/empdata.xlsx";
	public static List<String> list = new ArrayList<String>();

	public static void main(String[] args) {
		//create Excel Util instance
		ExcelUtil de = new ExcelUtil();
		List<InputData> readExcelFile = de.readExcelFile(FILE_PATH);
		System.out.println("Data from Excel"+readExcelFile);
		
		List<InputData> matchList = new LinkedList<>(readExcelFile);
		List<InputData> diffList = new LinkedList<>(readExcelFile);
		
		//create DB Util instance
		DatabaseUtil db = new DatabaseUtil();
		List<InputData> dataFromDb = db.dataFromDb();
		System.out.println("Data from db" +dataFromDb);

		StringBuilder sb = new StringBuilder();
		
		//Find common elements
		matchList.retainAll(dataFromDb); 
		System.out.println("Matching Data :: " + matchList);
		sb.append("Matching Data :: " + matchList);
		
		// Difference elements
		diffList.removeAll(dataFromDb); 
		
		System.out.println("Unmatched Data :: " + diffList);
		System.out.println("Please check file for more details");
		sb.append("\n");
		sb.append("Unmatched Data are :: " + diffList);
		for (InputData d : diffList) {
			for (InputData d1 : dataFromDb) {
				if (d.getId().equalsIgnoreCase(d1.getId()) && d.getSalary().equalsIgnoreCase(d1.getSalary())) {
					if (!d.getName().equalsIgnoreCase(d1.getName())) {
						list.add("\n Name failed  Excel data : " + d.getName() + " DB Data : " + d1.getName());
						break;
					}
					if (!d.getSirname().equalsIgnoreCase(d1.getSirname())) {
						list.add("\n Sirname failed Excel data : " + d.getSirname() + " DB Data : " + d1.getSirname());
						break;
					}
					if (!d.getCity().equalsIgnoreCase(d1.getCity())) {
						list.add("\n City failed  Excel data : " + d.getCity() + " DB Data : " + d1.getCity());
						break;
					}
				}
			}
		}
		sb.append("\n");
		sb.append("Reason for unmatched data : Values not matched ");
		list.forEach(e -> sb.append(e));
		Format formatter = new SimpleDateFormat("MM-dd-yyyy_hh-mm-ss");
		String s = formatter.format(new Date());
		try (FileWriter fw = new FileWriter("TestData/compareOutput" + s + ".txt")) {
			fw.write(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
