package application;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import application.MilkOperations.MilkData;
import javafx.application.Application;

/**
 * Driver - TODO Describe purpose of this user-defined type
 * 
 */
public class Driver {
	
	private static ArrayList<Farm> farmArray = new ArrayList<Farm>();
	private static MilkOperations<Farm> operator;
	
	public static void parseFile(String fileName) {
		ArrayList<LocalDate> dateArray = new ArrayList<LocalDate>();
		ArrayList<String> farmIDArray = new ArrayList<String>();
		ArrayList<Integer> weightArray = new ArrayList<Integer>();
		
		
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line = "";
			String splitBy = ",";
			while((line = br.readLine()) != null) {
				String[] lineInfo = line.split(splitBy);
				dateArray.add(LocalDate.parse(lineInfo[0]));
				farmIDArray.add(lineInfo[1]);
				weightArray.add(Integer.parseInt(lineInfo[2]));
				
			}
		}
		
		for(int i=0; i<farmIDArray.size(); i++) {
			if(!farmArray.contains(farmIDArray.get(i))) { // this line might not work, will .contains be effective
				Farm newFarm = new Farm(farmIDArray.get(i));
				newFarm.addEntry(weightArray.get(i), dateArray.get(i));
				farmArray.add(newFarm);
			}
			else {
				farmArray.get(i).addEntry(weightArray.get(i), dateArray.get(i));
			}
		}
		
		operator = new MilkOperations<Farm>(Arrays.toArray(farmArray));
		
		
	}
	
	public static String printFarmReport(String farmID, int year) {
		ArrayList<MilkData> milkDataList = operator.farmReport(farmID, year);
		String output = "";
		for(int i=0; i<milkDataList.size(); i++) {
			output += "Month: " + (i+1) + "\tTotal Weight: " + 
					milkDataList.get(i).getAmount() + "\tPercent of yearly weight: " + 
						milkDataList.get(i).getPercentage();
		}
		
		return output;
	}
	
	public static String printMonthlyReport(int year, int month) {
		ArrayList<MilkData> milkDataList = operator.MonthlyReport(year, month);
		String output = "";
		for(int i=0; i<milkDataList.size(); i++) {
			output += milkDataList.get(i).getF().getID() + ":\tTotal Weight: " + 
					milkDataList.get(i).getAmount() + "\tPercent of total for month: " + 
						milkDataList.get(i).getPercentage();
		}
		
		return output;
		
	}
	
	public static String printDateRangeReport(int year1, int month1, int day1, int year2, int month2, int day2) {
		ArrayList<MilkData> milkDataList = operator.DateRangeReport(year1, month1, day1, year2, month2, day2);
		String output = "";
		for(int i=0; i<milkDataList.size(); i++) {
			output += milkDataList.get(i).getF().getID() + ":\tTotal Weight: " + 
					milkDataList.get(i).getAmount() + "\tPercent of total over range: " + 
						milkDataList.get(i).getPercentage();
		}
		
		return output;
	}

}
