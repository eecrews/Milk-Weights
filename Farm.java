import java.time.LocalDate;

public class Farm {
	String farmID;
	mArray entryList;
	
	public Farm(String farmID) {
		this.farmID = farmID;
		entryList = new mArray();
	}
	
	public void addEntry(int milkWeight, LocalDate date) {
		entryList.addDay(milkWeight, date, farmID);
	}
	
	public String getID() {
		return farmID;
	}
	
	public mArray getEntryList() {
		return entryList;
	}
	public int getMilkWeight(LocalDate date) {
		Node[] entries= entryList.year;	
		Node entryFound = null;
		for(Node entry: entries) {
			if(entry.getMilkDate().equals(date))
				entryFound = entry;
		}
		if(entryFound == null)
			return -1;
		return entryFound.milkWeight;
	}
	
}
