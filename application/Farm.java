package application;

import java.time.LocalDate;


/*
 * this class is a custom designed class that describes the construction of a Farm Object. the point
 * of a farm object is to represent a single individual farm. To this end the farm object keeps
 * track of the farmID in string form as well as a modified form of an array which is used to keep
 * track of milk production by day.
 * 
 * 
 * 
 */
public class Farm {
  String farmID;
  mArray entryList;
  // class variables


  /*
   * constructor to make a farm
   * 
   * @param String - farmID the id of the farm
   * 
   * 
   */
  public Farm(String farmID) {
    this.farmID = farmID;
    entryList = new mArray();
  }


  /*
   * this method is used to add an entry to a farm (a day's production of milk)
   * 
   * @param double - milkWeight the weight of the milk produced on that day
   * 
   * @param LocalDate - date the day month year in question
   * 
   */
  public void addEntry(double milkWeight, LocalDate date) {
    entryList.addDay(milkWeight, date, farmID);
  }


  /*
   * this is a simple getter method to get the farm ID
   * 
   * @param none
   * 
   * @returns String - farmID which is the unique id of the farm
   * 
   * 
   */
  public String getID() {
    return farmID;
  }


  /*
   * 
   * this method gets the milk production log for a given farm (the modified array list)
   * 
   * @param none
   * 
   * @return mArray - an array that holds info about milk production for a farm in a year
   * 
   */
  public mArray getEntryList() {
    return entryList;
  }


  /*
   * this method gets the amount of milk produced at a given farm on a given date
   * 
   * @param LocalDate - date the date of interest
   * 
   * @return double - the amount of milk
   * 
   * 
   */
  public double getMilkWeight(LocalDate date) {
    Node[] entries = entryList.year;
    Node entryFound = null;
    for (Node entry : entries) {
      if (entry.getMilkDate().equals(date))
        entryFound = entry;
    }
    if (entryFound == null)
      return -1;
    return entryFound.milkWeight;
  }


  /*
   * this method is used to print out all of the entries within a given mArray for a farm
   * 
   * @param none
   * 
   * @returns String - all of the entries within the mArray
   * 
   * 
   */
  public String printAllEntries() {
    String output = "";
    for (int i = 0; i < entryList.size(); i++) {
      if (entryList.getMilkWeight(i) != 0 && entryList.getMilkDate(i) != null)
        output +=
            entryList.getMilkWeight(i) + "\t\t" + entryList.getMilkDate(i).getDayOfMonth() + "\n";
    }

    return output;
  }

}
