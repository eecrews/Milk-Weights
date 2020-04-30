package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javafx.application.Application;

/**
 * Driver - TODO Describe purpose of this user-defined type
 * 
 */
public class Driver {
	
	public static class InformationOmittedException extends Exception {
		
		public InformationOmittedException() {
			super();
		}
		
		public InformationOmittedException(String errorMessage) {
			super(errorMessage);
		}
		

	}
	
	public static class FarmDoesNotExistException extends Exception {
		public FarmDoesNotExistException() {
			super();
		}
		public FarmDoesNotExistException(String errorMessage) {
			super(errorMessage);
		}
	}
	
	public static class YearDoesNotExistException extends Exception {
		public YearDoesNotExistException() {
			super();
		}
		public YearDoesNotExistException(String errorMessage) {
			super(errorMessage);
		}
	}

	private static ArrayList<Farm> farmArray = new ArrayList<Farm>();
	private static MilkOperations operator;
	private static ArrayList<String> farmIDs = new ArrayList<String>();
	private static ArrayList<Integer> years = new ArrayList<Integer>();

	public static void parseFile(String fileName) throws FileNotFoundException, IOException, InformationOmittedException {		
		ArrayList<LocalDate> dateArray = new ArrayList<LocalDate>();
		ArrayList<String> farmIDArray = new ArrayList<String>();
		ArrayList<Double> weightArray = new ArrayList<Double>();
		
		DateTimeFormatter formatter = new DateTimeFormatterBuilder()
	            .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d"))
	            .appendOptional(DateTimeFormatter.ofPattern(("yyyy-MM-d")))
	            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
	            .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-dd"))
	            .toFormatter();
		
		boolean informationOmitted = false;
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line = "";
			String splitBy = ",";
			br.readLine();
			while ((line = br.readLine()) != null) {
					String[] lineInfo = line.split(splitBy);
					if(lineInfo[0] == null || lineInfo[1] == null || lineInfo[2] == null) {
						continue;
					}
					if(lineInfo[1].length() < 4 || !lineInfo[1].substring(0,4).contentEquals("Farm")) {
						informationOmitted = true;
						continue;
					}
					
					LocalDate dateToBeAdded;
					String farmIDToBeAdded;
					Double weightToBeAdded;
										
					try {
						dateToBeAdded = LocalDate.parse(lineInfo[0],formatter);
						farmIDToBeAdded = lineInfo[1];
						weightToBeAdded = Double.parseDouble(lineInfo[2]);
					} catch(DateTimeParseException e) {
						informationOmitted = true;
						continue;
					} catch(NumberFormatException e) {
						informationOmitted = true;
						continue;
					}
						dateArray.add(dateToBeAdded);
						farmIDArray.add(farmIDToBeAdded);
						weightArray.add(weightToBeAdded);
		} 


		for (int i = 0; i < farmIDArray.size(); i++) {
			if (farmIDs.isEmpty() || !farmIDs.contains(farmIDArray.get(i))) { 
				
				farmIDs.add(farmIDArray.get(i));
				Farm newFarm = new Farm(farmIDArray.get(i));
				newFarm.addEntry(weightArray.get(i), dateArray.get(i));
				farmArray.add(newFarm);
				
				if(!years.contains(dateArray.get(i).getYear()))
					years.add(dateArray.get(i).getYear());
				
			} else {
				int indexOfRepeat;
				for(indexOfRepeat=0; indexOfRepeat<farmArray.size(); indexOfRepeat++) {
					if(farmArray.get(indexOfRepeat).getID().contentEquals(farmIDArray.get(i)))
						break;
				}
				farmArray.get(indexOfRepeat).addEntry(weightArray.get(i), dateArray.get(i));
			}
		}

		operator = new MilkOperations(farmArray.toArray(new Farm[farmArray.size()]));
		
		}
		
		if(informationOmitted) 
			throw new InformationOmittedException();
		
	}
	
	public static boolean doesFarmExist(String farm) {
		return farmIDs.contains(farm);
	}
	
	public static boolean doesYearExist(int year) {
		return years.contains(year);
	}
	
	public static String printFarms() {
		String output = "";
		for(int i=0; i<farmArray.size(); i++) {
			output+= farmArray.get(i).getID() + "\n";
			output += farmArray.get(i).printAllEntries();
			output += "\n\n";
		}
		
		return output;
	}


	public static String printFarmReport(String farmID, int year) throws FarmDoesNotExistException, YearDoesNotExistException {
		if(!doesFarmExist(farmID)) {
			throw new FarmDoesNotExistException();
		}
		if(!doesYearExist(year)) {
			throw new YearDoesNotExistException();
		}
		ArrayList<MilkOperations.MilkData> milkDataList = operator.farmReport(farmID, year);
		String output = "";
		for (int i = 0; i < milkDataList.size(); i++) {
			output += "\nMonth: " + (i + 1) + "\tTotal Weight: " + milkDataList.get(i).getAmount()
					+ "\tPercent of yearly weight: " + milkDataList.get(i).getPercentage();
		}

		return output;
	}

	public static String printMonthlyReport(int year, int month) {
		DecimalFormat df = new DecimalFormat("#.####");
		ArrayList<MilkOperations.MilkData> milkDataList = operator.MonthlyReport(year, month);
		String output = "";
		for (int i = 0; i < milkDataList.size(); i++) {
			output += "\n" + milkDataList.get(i).getF().getID() + ":\tTotal Weight: " + milkDataList
					.get(i).getAmount() + "\tPercent of total for month: " + df.format(milkDataList.get(i)
							.getPercentage());
		}

		return output;

	}

	public static String printDateRangeReport(int year1, int month1, int day1,
			int month2, int day2) {
		ArrayList<MilkOperations.MilkData> milkDataList = operator.DateRangeReport(year1, month1, day1, month2, day2);
		String output = "";
		for (int i = 0; i < milkDataList.size(); i++) {
			output += "\n" + milkDataList.get(i).getF().getID() + ":\tTotal Weight: " + milkDataList
					.get(i).getAmount() + "\tPercent of total over range: " + milkDataList.get(i)
							.getPercentage();
		}

		return output;
	}


	public static String printAnnualReport(int year){
		DecimalFormat df = new DecimalFormat("#.####");
		ArrayList<MilkOperations.MilkData> milkDataList = operator.AnnualReport(year);
		String output = "";
		for(int i=0; i<milkDataList.size(); i++) {
			output += "\n" + milkDataList.get(i).getF().getID() + ":\tTotal Weight: " +
					milkDataList.get(i).getAmount() + "\tPercent of total for year: " +
					df.format(milkDataList.get(i)
							.getPercentage());
		}

		return output; 

	}
	
	public static void main(String[] args) {
		Application.launch(GUI.class, args);
	}

}
