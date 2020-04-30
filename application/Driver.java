package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.application.Application;

/**
 * Driver - TODO Describe purpose of this user-defined type
 * 
 */
public class Driver {
	
	public static class InformationOmittedException extends Exception {
		ArrayList<Integer> linesOmitted;
		
		public InformationOmittedException() {
			super();
		}
		public InformationOmittedException(ArrayList<Integer> linesOmitted) {
			this.linesOmitted = linesOmitted;
		}
		
		public InformationOmittedException(String errorMessage) {
			super(errorMessage);
		}
		
		public ArrayList<Integer> getLinesOmitted() {
			return linesOmitted;
		}
	}

	private static ArrayList<Farm> farmArray = new ArrayList<Farm>();
	private static MilkOperations operator;
	private static ArrayList<String> farmIDs = new ArrayList<String>();

	public static void parseFile(String fileName) throws FileNotFoundException, IOException, InformationOmittedException {
		ArrayList<LocalDate> dateArray = new ArrayList<LocalDate>();
		ArrayList<String> farmIDArray = new ArrayList<String>();
		ArrayList<Integer> weightArray = new ArrayList<Integer>();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-M-d");
		
		ArrayList<Integer> linesOmitted = new ArrayList<Integer>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line = "";
			String splitBy = ",";
			br.readLine();
			int lineNumber = 1;
			while ((line = br.readLine()) != null) {
				String[] lineInfo = line.split(splitBy);
				if(lineInfo[0] == null || lineInfo[1] == null || lineInfo[2] == null) {
					linesOmitted.add(lineNumber);
					lineNumber++;
					continue;
				}
					dateArray.add(LocalDate.parse(lineInfo[0],df));
					farmIDArray.add(lineInfo[1]);
					weightArray.add(Integer.parseInt(lineInfo[2]));
					lineNumber++;
			} 
		} 

		for (int i = 0; i < farmIDArray.size(); i++) {
			if (farmIDs.isEmpty() || !farmIDs.contains(farmIDArray.get(i))) { 
				
				farmIDs.add(farmIDArray.get(i));
				Farm newFarm = new Farm(farmIDArray.get(i));
				newFarm.addEntry(weightArray.get(i), dateArray.get(i));
				farmArray.add(newFarm);
				
			} else {
				int indexOfRepeat;
				for(indexOfRepeat=0; i<farmArray.size(); i++) {
					if(farmArray.get(indexOfRepeat).getID().contentEquals(farmIDArray.get(i)))
						break;
				}
				farmArray.get(indexOfRepeat).addEntry(weightArray.get(i), dateArray.get(i));
			}
		}

		operator = new MilkOperations(farmArray.toArray(new Farm[farmArray.size()]));
		
		if(!linesOmitted.isEmpty()) {
			throw new InformationOmittedException(linesOmitted);
		}

	}
	


	public static String printFarmReport(String farmID, int year) {
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
		ArrayList<MilkOperations.MilkData> milkDataList = operator.DateRangeReport(year1,
				month1, day1, month2, day2);
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
