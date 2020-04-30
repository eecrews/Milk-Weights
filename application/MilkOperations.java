package application;

import javafx.scene.control.SpinnerValueFactory;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MilkOperations {

    Farm[] Farms;

    MilkOperations(Farm[] temp) {
        Farms = temp;
    }
    public class MilkData implements Comparable<MilkData> {
        Farm F;
        int amount;
        double percentage;

        public MilkData(Farm temp, int a, double p) {
            F=temp;
            amount=a;
            percentage=p;
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
            amount+=add;
        }
        public void setPercentage(double p) {
            percentage=p;
        }

        @Override
        public int compareTo(MilkData k) {
            return k.getF().getID().compareTo(this.getF().getID());
        }
    }

    public boolean inputWorks() {
        for(int i=0;i<Farms.length;i++) {
            for(int j=0;j<Farms[i].getEntryList().size();j++) {
                if(Farms[i].getEntryList().getMilkDate(j)!=null&&Farms[i].getEntryList().getFarmID(j)!=Farms[i].getID()) {
                    return false;
                }
                else {
                    System.out.println(Farms[i].getID());
                    System.out.println(i);
                    System.out.println(Farms[i].getEntryList().getMilkWeight(j));
                }
            }
        }
        return true;
    }
    /**
     * Returns a farm's output for each month in a given year as well as its percentage of total output for each month
     * @param farmID
     * @param year
     * @return
     */
    public ArrayList<MilkData> farmReport(String farmID, int year) {//210,093 for 2019-1.csv in small (wrong)
        System.out.println(inputWorks());
        int ind=-1;
        for(int i=0;i<Farms.length;i++) {
            if(Farms[i].getID().equals(farmID)) {
                ind=i;//finding the farm with the specified ID in the array
                break;
            }
        }
        int weight=0;
       // MilkData[] weights = new MilkData[12];
        ArrayList<MilkData> weights = new ArrayList<MilkData>();
        for(int i=0;i<12;i++) {
            weights.add(new MilkData(Farms[ind], 0, 0));//initializing all 12 months to be 0
        }
        for(int i=0;i<Farms[ind].getEntryList().size();i++) {
            if(Farms[ind].getEntryList().getMilkDate(i) != null) {
                System.out.println(Farms[ind].getID());
                System.out.println(i);
                System.out.println(Farms[ind].getEntryList().getMilkWeight(i));
            }

        }
        for(int i=0;i<Farms[ind].getEntryList().size();i++) {
            if (Farms[ind].getEntryList().getMilkDate(i) != null && Farms[ind].getEntryList().getMilkDate(i).getYear()==year) {
                weight += Farms[ind].getEntryList().getMilkWeight(i);
                weights.get(Farms[ind].getEntryList().getMilkDate(i).getMonthValue()-1).addToAmount((int)Farms[ind].getEntryList().getMilkWeight(i));//Adding amount to specific month
                //System.out.println(Farms[ind].getEntryList().getMilkWeight(i));
            }
        }

        for(int i=0;i<weights.size()-1;i++) {
            weights.get(i).setPercentage((weights.get(i).getAmount()/weight)*100);
        }

        System.out.println(weight);
        return weights;
        //return format: First 12 values are percentages and amounts for each month, last entry is total.
    }

    public int getTotal(ArrayList<MilkData> temp) {
        int count=0;
        for(int i=0;i<temp.size();i++) {
            count+=temp.get(i).getAmount();
        }
        return count;
    }

    /**
     * Returns list of farms in the given year along with the output and percentages of said farms for that year.
     * @param year
     * @return
     */
    public ArrayList<MilkData> AnnualReport(int year) {
        ArrayList<MilkData> weights = new ArrayList<MilkData>();
        ArrayList<Farm> farms = new ArrayList<Farm>();
        int sold = 0;
        for(int i=0;i<Farms.length;i++) {
            for(int j=0;j<Farms[i].entryList.size();j++) {
                if(Farms[i].getEntryList().getMilkDate(j) != null && Farms[i].getEntryList().getMilkDate(j).getYear()==year) {
                    sold+=Farms[i].getEntryList().getMilkWeight(j);
                    if(!farms.contains(Farms[i])) farms.add(Farms[i]);
                }
            }
        }

        for(int i=0;i<farms.size();i++) {
        	double total = getTotal(farmReport(farms.get(i).getID(), year));
        	double percent = total * 100 / sold;
        	
            weights.add(new MilkData(farms.get(i), getTotal(farmReport(farms.get(i).getID(), year)), percent));

        }
        Collections.sort(weights);
        return weights;
    }

    /**
     * Returns list of farms for given month as well as output and percentage for each farm.
     * @param year
     * @param month
     * @return
     */
    public ArrayList<MilkData> MonthlyReport(int year, int month) {
        ArrayList<MilkData> weights = new ArrayList<MilkData>();
        ArrayList<Farm> farms = new ArrayList<Farm>();
        int sold = 0;
        for(int i=0;i<Farms.length;i++) {
            for(int j=0;j<Farms[i].entryList.size();j++) {
            	
                if(Farms[i].getEntryList().getMilkDate(j) != null && Farms[i].getEntryList().getMilkDate(j).getYear()==year && Farms[i].getEntryList().getMilkDate(j).getMonthValue()-1==month) {
                    sold+=Farms[i].getEntryList().getMilkWeight(j);
                    if(!farms.contains(Farms[i])) farms.add(Farms[i]);
                }
            }
        }

        for(int i=0;i<farms.size();i++) {
        	double total = getTotal(farmReport(farms.get(i).getID(), year));
        	double percent = total * 100 / sold;
        	
            weights.add(new MilkData(farms.get(i), getTotal(farmReport(farms.get(i).getID(), year)), percent));

        }
        Collections.sort(weights);
        return weights;
    }

    public ArrayList<MilkData> DateRangeReport(int year1, int month1, int day1, int month2, int day2) {
        LocalDate D1 = LocalDate.of((Integer)year1, (Integer)month1, (Integer)day1);
        LocalDate D2 = LocalDate.of((Integer)year1, (Integer)month2, (Integer)day2);
        ArrayList<MilkData> weights = new ArrayList<MilkData>();
        ArrayList<Farm> farms = new ArrayList<Farm>();
        int sold = 0;
        for(int i=0;i<Farms.length;i++) {
            for(int j=0;j<Farms[i].entryList.size();j++) {
            	if(Farms[i].getEntryList().getMilkDate(j) != null) {
            		LocalDate D3 = Farms[i].getEntryList().getMilkDate(j);
            		if(D3.compareTo(D1)==1&&D3.compareTo(D2)==-1) {//is in year range
            			sold+=Farms[i].getEntryList().getMilkWeight(j);
            			if(!farms.contains(Farms[i])) farms.add(Farms[i]);
            		}
            	}
            }
        }

        for(int i=0;i<farms.size();i++) {
            weights.add(new MilkData(farms.get(i), getTotal(farmReport(farms.get(i).getID(), year1)), getTotal(farmReport(farms.get(i).getID(), year1))/sold));

        }
        Collections.sort(weights);
        return weights;
    }








}
