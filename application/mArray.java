package application;

import java.time.LocalDate;

/**
 * Array of milk weights
 * 
 * @author Tushar
 *
 */
public class mArray {
	Node[] year;

	/**
	 * Constructor, makes an array for a year with 367 nodes (one for each day)
	 */
	public mArray() {
		year = new Node[367];

	}

	/**
	 * 
	 * @param i: index
	 * @return double milkWeight
	 */
	public double getMilkWeight(int i) {
		if (year[i] != null)
			return year[i].getMilkWeight();
		return 0;
	}

	/**
	 * 
	 * @param i: index (day)
	 * @returns Date in mArray index
	 */
	public LocalDate getMilkDate(int i) {
		if (year[i] != null)
			return year[i].getMilkDate();
		return null;
	}

	/**
	 * 
	 * @param i: index (day)
	 * @return: ID of farm in index
	 */
	public String getFarmID(int i) {
		return year[i].getFarmID();
	}

	/**
	 * Gets the next farm in the array
	 * 
	 * @param i: index
	 * @return Node
	 */
	public Node getNext(int i) {
		return year[i].getNext();
	}

	/**
	 * Size of mArray
	 * 
	 * @return
	 */
	public int size() {
		return year.length;
	}

	/**
	 * Adds a new day to the array in the inputted index date
	 * 
	 * @param milk
	 * @param date
	 * @param ID
	 */
	public void addDay(double milk, LocalDate date, String ID) {
		int index = date.getDayOfYear();
		year[index] = new Node(milk, date, ID);
	}

}

/**
 * Represents a day in the year
 *
 */
class Node {
	double milkWeight;
	LocalDate milkDate;
	String farmID;
	Node next;

	/**
	 * Returns milkweight deposited that day
	 * 
	 * @return
	 */
	public double getMilkWeight() {
		return milkWeight;
	}

	/**
	 * Sets milkweight for that day
	 * 
	 * @param milkWeight
	 */
	public void setMilkWeight(double milkWeight) {
		this.milkWeight = milkWeight;
	}

	/**
	 * Gets date for that node
	 * 
	 * @return
	 */
	public LocalDate getMilkDate() {
		return milkDate;
	}

	/**
	 * Sets date for node
	 * 
	 * @param milkDate
	 */
	public void setMilkDate(LocalDate milkDate) {
		this.milkDate = milkDate;
	}

	/**
	 * Gets ID of farm in the node
	 * 
	 * @return
	 */
	public String getFarmID() {
		return farmID;
	}

	/**
	 * Sets ID of farm in the node
	 * 
	 * @param farmID
	 */
	public void setFarmID(String farmID) {
		this.farmID = farmID;
	}

	/**
	 * Gets the next node
	 * 
	 * @return
	 */
	public Node getNext() {
		return next;
	}

	/**
	 * Sets next node
	 * 
	 * @param next
	 */
	public void setNext(Node next) {
		this.next = next;
	}

	/**
	 * Node constructor
	 * 
	 * @param milkWeight
	 * @param milkDate
	 * @param farmID
	 */
	public Node(double milkWeight, LocalDate milkDate, String farmID) {
		this.milkWeight = milkWeight;
		this.milkDate = milkDate;
		this.farmID = farmID;
		next = null;
	}

}
