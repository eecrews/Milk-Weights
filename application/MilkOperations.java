package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class MilkOperations {

	Farm[] Farms;

	/**
	 * Constructor
	 * 
	 * @param temp: Array of farms
	 */
	MilkOperations(Farm[] temp) {
		Farms = temp;
	}

	/**
	 * Inner class for the milk data in each farm
	 * 
	 * @author Akshay
	 *
	 */
	public class MilkData implements Comparable<MilkData> {
		Farm F;
		int amount;
		double percentage;

		/**
		 * Constructor
		 * 
		 * @param temp: farm in question
		 * @param a:    Amount of milk (weight)
		 * @param p:    percentage of milk
		 */
		public MilkData(Farm temp, int a, double p) {
			F = temp;
			amount = a;
			percentage = p;
		}

		public Farm getF() {
			return F;
		}

		public int getAmount() {
			return amount;
		}

		public double getPercentage() {
			return percentage;
		}

		public void addToAmount(int add) {
			amount += add;
		}

		public void setPercentage(double p) {
			percentage = p;
		}

		/**
		 * Compares IDs of two farms (numbers only)
		 */
		@Override
		public int compareTo(MilkData k) {
			Integer kID = Integer.parseInt(k.getF().getID().substring(4).trim());
			Integer FID = Integer.parseInt(this.getF().getID().substring(4).trim());
			return kID.compareTo(FID);
		}
	}

	/**
	 * Returns a farm's output for each month in a given year as well as its
	 * percentage of total output for each month
	 * 
	 * @param farmID
	 * @param year
	 * @return
	 */
	public ArrayList<MilkData> farmReport(String farmID, int year) {
		int ind = -1;
		for (int i = 0; i < Farms.length; i++) {
			if (Farms[i].getID().equals(farmID)) {
				ind = i;// finding the farm with the specified ID in the array
				break;
			}
		}
		int weight = 0;
		// MilkData[] weights = new MilkData[12];
		ArrayList<MilkData> weights = new ArrayList<MilkData>();
		for (int i = 0; i < 12; i++) {
			weights.add(new MilkData(Farms[ind], 0, 0));// initializing all 12
														// months to be 0
		}

		for (int i = 0; i < Farms[ind].getEntryList().size(); i++) {
			if (Farms[ind].getEntryList().getMilkDate(i) != null && Farms[ind].getEntryList()
					.getMilkDate(i).getYear() == year) {
				weight += Farms[ind].getEntryList().getMilkWeight(i);
				weights.get(Farms[ind].getEntryList().getMilkDate(i).getMonthValue() - 1)
						.addToAmount((int) Farms[ind].getEntryList().getMilkWeight(i));// Adding
																						// amount to
																						// specific
																						// month
			}
		}

		for (int i = 0; i < weights.size() - 1; i++) {
			weights.get(i).setPercentage((weights.get(i).getAmount() / weight) * 100);
		}

		return weights;
		// return format: First 12 values are percentages and amounts for each
		// month, last entry is total.
	}

	public int getTotal(ArrayList<MilkData> temp) {
		int count = 0;
		for (int i = 0; i < temp.size(); i++) {
			count += temp.get(i).getAmount();
		}
		return count;
	}

	/**
	 * Returns list of farms in the given year along with the output and percentages
	 * of said farms for that year.
	 * 
	 * @param year
	 * @return
	 */
	public ArrayList<MilkData> AnnualReport(int year) {
		ArrayList<MilkData> weights = new ArrayList<MilkData>();
		ArrayList<Farm> farms = new ArrayList<Farm>();
		int sold = 0;
		int F0 = 0;
		int F1 = 0;
		int F2 = 0;
		for (int i = 0; i < Farms.length; i++) {
			for (int j = 0; j < Farms[i].entryList.size(); j++) {
				if (Farms[i].getEntryList().getMilkDate(j) != null && Farms[i].getEntryList()
						.getMilkDate(j).getYear() == year) {
					sold += Farms[i].getEntryList().getMilkWeight(j);
					if (Farms[i].getID().equals("Farm 0"))
						F0++;
					if (Farms[i].getID().equals("Farm 1"))
						F1++;
					if (Farms[i].getID().equals("Farm 2"))
						F2++;
					if (!farms.contains(Farms[i]))
						farms.add(Farms[i]);
				}
			}
		}

		for (int i = 0; i < farms.size(); i++) {
			double total = getTotal(farmReport(farms.get(i).getID(), year));
			double percent = total * 100 / sold;

			weights.add(new MilkData(farms.get(i), getTotal(farmReport(farms.get(i).getID(), year)),
					percent)); // should be farm, total output of farm for that month, and percent
								// of output for that month
		}

		Collections.sort(weights, Collections.reverseOrder());

		return weights;
	}

	/**
	 * Returns list of farms for given month as well as output and percentage for
	 * each farm.
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public ArrayList<MilkData> MonthlyReport(int year, int month) {
		ArrayList<MilkData> weights = new ArrayList<MilkData>();
		ArrayList<Farm> farms = new ArrayList<Farm>();
		int sold = 0;
		int F0 = 0;
		int F1 = 0;
		int F2 = 0;
		for (int i = 0; i < Farms.length; i++) {
			for (int j = 0; j < Farms[i].entryList.size(); j++) {

				if (Farms[i].getEntryList().getMilkDate(j) != null && Farms[i].getEntryList()
						.getMilkDate(j).getYear() == year && Farms[i].getEntryList().getMilkDate(j)
								.getMonthValue() == month) {
					// if statement checks if current entry is null, then checks
					// if it's in the correct year and month to be added.
					sold += Farms[i].getEntryList().getMilkWeight(j);
					if (Farms[i].getID().equals("Farm 0"))
						F0++;
					if (Farms[i].getID().equals("Farm 1"))
						F1++;
					if (Farms[i].getID().equals("Farm 2"))
						F2++;

					if (!farms.contains(Farms[i])) {
						farms.add(Farms[i]);
					}
				}
			}
		}

		for (int i = 0; i < farms.size(); i++) {
			double total = farmReport(farms.get(i).getID(), year).get(month - 1).getAmount();
			double percent = total * 100 / sold;
			System.out.println(farms.get(i).getID() + ": " + farmReport(farms.get(i).getID(), year)
					.get(month - 1).getAmount());
			weights.add(new MilkData(farms.get(i), farmReport(farms.get(i).getID(), year).get(month
					- 1).getAmount(), percent));// should be farm, total output of farm for that
												// month, and percent of output for that month.

		}
		Collections.sort(weights, Collections.reverseOrder());
		return weights;
	}

	/**
	 * Returns list of farms between two certain dates, in the same year, as well as
	 * output and percentage for each
	 * 
	 * @param year1
	 * @param month1
	 * @param day1
	 * @param month2
	 * @param day2
	 * @return
	 */
	public ArrayList<MilkData> DateRangeReport(int year1, int month1, int day1, int month2,
			int day2) throws Driver.MonthDoesNotExistException {
		LocalDate D1 = LocalDate.of((Integer) year1, (Integer) month1, (Integer) day1);
		LocalDate D2 = LocalDate.of((Integer) year1, (Integer) month2, (Integer) day2);
		ArrayList<MilkData> weights = new ArrayList<MilkData>();
		ArrayList<Farm> farms = new ArrayList<Farm>();
		int sold = 0;
		for (int i = 0; i < Farms.length; i++) {
			for (int j = 0; j < Farms[i].entryList.size(); j++) {
				if (Farms[i].getEntryList().getMilkDate(j) != null) {
					LocalDate D3 = Farms[i].getEntryList().getMilkDate(j);
					if (D3.getDayOfYear() >= D1.getDayOfYear() && D3.getDayOfYear() <= D2
							.getDayOfYear()) {// D3 is
												// between
												// D1
												// and
												// D2
						sold += Farms[i].getEntryList().getMilkWeight(j);
						if (!farms.contains(Farms[i])) {// Farm has not already
														// been added
							farms.add(Farms[i]);
							weights.add(new MilkData(Farms[i], (int) Farms[i].getEntryList()
									.getMilkWeight(j), Farms[i].getEntryList().getMilkWeight(j)
											/ sold));
						} else {// farm is already there
							int tempInd = -1;
							for (int n = 0; n < weights.size(); n++) {
								if (weights.get(i).getF().equals(Farms[i]))
									tempInd = i;
							}
							weights.get(tempInd).addToAmount((int) Farms[i].getEntryList()
									.getMilkWeight(j));
						}
					}
				}
			}
			if(farms.size()==0) throw new Driver.MonthDoesNotExistException();
		}

		for (int i = 0; i < weights.size(); i++) {
			weights.get(i).setPercentage(((weights.get(i).getAmount() * 1.0) / (sold * 1.0)) * 100);
		}

		Collections.sort(weights, Collections.reverseOrder());
		return weights;
	}

}
