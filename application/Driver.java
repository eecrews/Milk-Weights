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
 * Driver - the purpose of this class is to provide the main logic and facillitate the running of
 * the program. It brings together all of the classes of the project to present one single coherent
 * application, provides for exception handling, and also importantly data input is handled in this
 * class.
 */
public class Driver {


  /*
   * this exception is for if one of the data fields in the data file being read in is missing
   * 
   */
  public static class InformationOmittedException extends Exception {


    /*
     * this is the no argument constructor for the exception
     * 
     */
    public InformationOmittedException() {
      super();
    }

    /*
     * this is the exception with parameters
     * 
     * @param String - errorMessage the message that will be in the exception
     */
    public InformationOmittedException(String errorMessage) {
      super(errorMessage);
    }
  }


  /*
   * this is a custom defined exception that we throw if the wrong file is uploaded. there are two
   * constructors to make the exception one to have a message and the other is blank
   * 
   * 
   */
  public static class IncorrectFileTypeException extends Exception {

    /*
     * this is the no argument constructor
     * 
     */
    public IncorrectFileTypeException() {
      super();
    }



    /*
     * 
     * this is the constructor that allows for parameters
     * 
     * @param String - errorMessage the message that will be in the exception
     * 
     * 
     */

    public IncorrectFileTypeException(String errorMessage) {
      super(errorMessage);
    }
  }


  /*
   * this is a custom defined exception that we throw if a farm object does not exist and a user
   * attempts to request that farm
   * 
   * 
   */
  public static class FarmDoesNotExistException extends Exception {

    /*
     * the no argument constructor for the exception
     */
    public FarmDoesNotExistException() {
      super();
    }

    /*
     * 
     * for if we want to pass a string parameter to the exception
     * 
     * @param String - errorMessage the message that will be in the exception
     * 
     */
    public FarmDoesNotExistException(String errorMessage) {
      super(errorMessage);
    }
  }


  /*
   * this exception is for if a user tries to request info on a year that is not in the system
   * 
   * 
   */
  public static class YearDoesNotExistException extends Exception {

    /*
     * this constructor is the no arg
     * 
     */
    public YearDoesNotExistException() {
      super();
    }


    /*
     * this constructor is for if the user has arguments to pass
     * 
     * @param String - errorMessage the message that will be in the exception
     * 
     */
    public YearDoesNotExistException(String errorMessage) {
      super(errorMessage);
    }
  }


  /*
   * this exception for is if the user requests a month that is not in the system
   * 
   */
  public static class MonthDoesNotExistException extends Exception {
    /*
     * this is the no argument constructor for the exception
     * 
     */
    public MonthDoesNotExistException() {
      super();
    }

    /*
     * 
     * this is the constructor with a string parameter for the exception
     * 
     * @param String - errorMessage the message that will be in the exception
     * 
     */

    public MonthDoesNotExistException(String errorMessage) {
      super(errorMessage);
    }
  }

  // variables needed for the running of the program
  private static ArrayList<Farm> farmArray = new ArrayList<Farm>();
  private static MilkOperations operator;
  private static ArrayList<String> farmIDs = new ArrayList<String>();
  private static int year;
  private static ArrayList<Integer> months = new ArrayList<Integer>();


  /*
   * this is a very important and long method that handles the data/file input of the application.
   * It reads in the data in a very particular format (modeled off of how it was represented on
   * canvas) and if it is not in that form it will throw one of many types of errors
   * 
   * @throws FileNotFoundException - if the requested file does not exist
   * 
   * @throws IOException - if the input is poor
   * 
   * @throws InformationOmittedException - if the file has incomplete records/data fields
   * 
   * @throws IncorrectFileTypeException - if the file is not a csv or readable by our program
   * 
   * @return none
   * 
   * @param String - fileName the name of the file that will be searched for and read
   */
  public static void parseFile(String fileName) throws FileNotFoundException, IOException,
      InformationOmittedException, IncorrectFileTypeException {
    if (!fileName.contains(".csv"))
      throw new IncorrectFileTypeException();
    ArrayList<LocalDate> dateArray = new ArrayList<LocalDate>();
    ArrayList<String> farmIDArray = new ArrayList<String>();
    ArrayList<Double> weightArray = new ArrayList<Double>();
    /*
     * this is integral for formatting the data into a readable date form
     * 
     */
    DateTimeFormatter formatter =
        new DateTimeFormatterBuilder().appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d"))
            .appendOptional(DateTimeFormatter.ofPattern(("yyyy-MM-d")))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-dd")).toFormatter();

    boolean informationOmitted = false;

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line = "";
      String splitBy = ",";
      br.readLine();
      while ((line = br.readLine()) != null) {
        String[] lineInfo = line.split(splitBy);
        if (lineInfo[0] == null || lineInfo[1] == null || lineInfo[2] == null) {
          continue;
        }
        if (lineInfo[1].length() < 4 || !lineInfo[1].substring(0, 4).contentEquals("Farm")) {
          informationOmitted = true;
          continue;
        }
        // these lines are where the data file is parsed and broken up
        LocalDate dateToBeAdded;
        String farmIDToBeAdded;
        Double weightToBeAdded;

        try {
          dateToBeAdded = LocalDate.parse(lineInfo[0], formatter);
          farmIDToBeAdded = lineInfo[1];
          weightToBeAdded = Double.parseDouble(lineInfo[2]);
        } catch (DateTimeParseException e) {
          informationOmitted = true;
          continue;
        } catch (NumberFormatException e) {
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

           year = dateArray.get(i).getYear();

          if (!months.contains(dateArray.get(i).getMonthValue()))
            months.add(dateArray.get(i).getMonthValue());

        } else {
          int indexOfRepeat;
          for (indexOfRepeat = 0; indexOfRepeat < farmArray.size(); indexOfRepeat++) {
            if (farmArray.get(indexOfRepeat).getID().contentEquals(farmIDArray.get(i)))
              break;
          }
          farmArray.get(indexOfRepeat).addEntry(weightArray.get(i), dateArray.get(i));
        }
      }

      operator = new MilkOperations(farmArray.toArray(new Farm[farmArray.size()]));

    }

    if (informationOmitted)
      throw new InformationOmittedException();

  }

	/*
    * Returns the current year being analyzed.
    *
    * @return int - year
    */
	public static int currYear() {	
		return year;	
	}

  /*
   * this method is to check to see if a given farm exists
   * 
   * @param String - farm that will be searched for
   * 
   * @returns boolean - false if the farm does not exist true if it does
   * 
   */
  public static boolean doesFarmExist(String farm) {
    return farmIDs.contains(farm);
  }


  /*
   * this method is to check if a given year has been inputed into the system
   * 
   * @param int - year the year to be searched
   * 
   * @return boolean - false if the year is not in the system true otherwise
   * 
   */
  public static boolean doesYearExist(int inputYear) {
    return inputYear == year;
  }


  /*
   * this method is to check if a given month has been inputed into the system
   * 
   * @param int - month the month to be searched
   * 
   * @return boolean - false if the month is not in the system true otherwise
   * 
   */
  public static boolean doesMonthExist(int month) {
    return months.contains(month);
  }


  /*
   * this method simply prints out all the farms within the given array
   * 
   * @param none
   * 
   * @return String a string of all the farms to be printed
   * 
   * 
   */
  public static String printFarms() {
    String output = "";
    for (int i = 0; i < farmArray.size(); i++) {
      output += farmArray.get(i).getID() + "\n";
      output += farmArray.get(i).printAllEntries();
      output += "\n\n";
    }

    return output;
  }



  /*
   * this method prints out a report on a given farm for the year
   * 
   * @param String - farmID string of the farm ID
   * 
   * @param int - year the year in integer form that will be searched for
   * 
   * @returns String - the associated stats on the farm in the given year if found
   * 
   * 
   */
  public static String printFarmReport(String farmID, int year)
      throws FarmDoesNotExistException, YearDoesNotExistException {
    if (!doesFarmExist(farmID)) {
      throw new FarmDoesNotExistException();
    }
    if (!doesYearExist(year)) {
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

  /*
   * this method prints out a report for all farms given month within a year
   * 
   * @param int - month the month in integer form that will be searched for
   * 
   * @param int - year the year in integer form that will be searched for
   * 
   * @returns String - the associated stats on the farm in the given month year if found
   * 
   * 
   */
  public static String printMonthlyReport(int year, int month)
      throws MonthDoesNotExistException, YearDoesNotExistException {
    if (!doesMonthExist(month))
      throw new MonthDoesNotExistException();
    if (!doesYearExist(year))
      throw new YearDoesNotExistException();
    DecimalFormat df = new DecimalFormat("#.####");
    ArrayList<MilkOperations.MilkData> milkDataList = operator.MonthlyReport(year, month);
    String output = "";
    for (int i = 0; i < milkDataList.size(); i++) {
      output += "\n" + milkDataList.get(i).getF().getID() + ":\tTotal Weight: "
          + milkDataList.get(i).getAmount() + "\tPercent of total for month: "
          + df.format(milkDataList.get(i).getPercentage());
    }

    return output;

  }

  /*
   * this method prints out a report for farms given a date range
   * 
   * @param int - year1 the year that the date range will be found inside of
   * 
   * @param int - month1 the first month range that will be used to generate a report off of
   * 
   * @param int - day1 the first day in the month range that will be used to generate a report
   * 
   * @param int - month2 the ending bound of the month range that the report will be generated from
   * 
   * @param int - day2 the ending bound day of the month range that the report will be generated
   * from
   * 
   * @returns String - the associated stats on the farm in the given month year if found
   * 
   * 
   */
  public static String printDateRangeReport(int year1, int month1, int day1, int month2, int day2)
      throws MonthDoesNotExistException, YearDoesNotExistException {
    if (!doesYearExist(year1))
      throw new YearDoesNotExistException();
    /*if (!doesMonthExist(month1) || !doesMonthExist(month2))
      throw new MonthDoesNotExistException(); */

    ArrayList<MilkOperations.MilkData> milkDataList =
        operator.DateRangeReport(year1, month1, day1, month2, day2);
    String output = "";
    for (int i = 0; i < milkDataList.size(); i++) {
      output += "\n" + milkDataList.get(i).getF().getID() + ":\tTotal Weight: "
          + milkDataList.get(i).getAmount() + "\tPercent of total over range: "
          + milkDataList.get(i).getPercentage();
    }

    return output;
  }


  /*
   * this method prints out a report for all farms given a year
   * 
   * @param int - year the year in integer form that will be searched for
   * 
   * @returns String - the associated stats on the farm in the given month year if found
   * 
   * 
   */

  public static String printAnnualReport(int year) {
    DecimalFormat df = new DecimalFormat("#.####");
    ArrayList<MilkOperations.MilkData> milkDataList = operator.AnnualReport(year);
    String output = "";
    for (int i = 0; i < milkDataList.size(); i++) {
      output += "\n" + milkDataList.get(i).getF().getID() + ":\tTotal Weight: "
          + milkDataList.get(i).getAmount() + "\tPercent of total for year: "
          + df.format(milkDataList.get(i).getPercentage());
    }

    return output;

  }


  /*
   * main method that begins the program
   * 
   */
  public static void main(String[] args) {
    Application.launch(GUI.class, args);
  }

}
