package application;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;

/**
 * Driver - TODO Describe purpose of this user-defined type
 * 
 */
public class Driver {
	
	private static ArrayList<Farm> farmArray = new ArrayList<Farm>();
	
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
			if(!farmArray.contains(farmIDArray.get(i))) {
				Farm newFarm = new Farm(farmIDArray.get(i));
				newFarm.addEntry(weightArray.get(i), dateArray.get(i));
				farmArray.add(newFarm);
				
			}
		}
		
	}
	
	public static String printFarmReport(String farmID) {
		ArrayList<MilkData> milkDataList = MilkOperations.farmReport(farmID);
		String output = milkDataList.getTotal() + "\n";
		for(int i=0; i<milkDataList.size(); i++) {
			output += "Month: " + (i+1) + "\tPercent of total: " + milkDataList.get(i).getPercentage();
		}
		
		return output;
	}

	

}
