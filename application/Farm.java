package application;

import java.time.LocalDate;

public class Farm {
	String farmID;
	mArray entryList;

	public Farm(String farmID) {
		this.farmID = farmID;
		entryList = new mArray();
	}

	public void addEntry(double milkWeight, LocalDate date) {
		entryList.addDay(milkWeight, date, farmID);
	}

	public String getID() {
		return farmID;
	}

	public mArray getEntryList() {
		return entryList;
	}

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
	
	public String printAllEntries() {
		String output = "";
		for(int i=0; i<entryList.size(); i++) {
			if(entryList.getMilkWeight(i) != 0 && 
					entryList.getMilkDate(i) != null)
				output += entryList.getMilkWeight(i) + "\t\t" + entryList.getMilkDate(i).getDayOfMonth() + "\n";
		}
		
		return output;
	}

}
