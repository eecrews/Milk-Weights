package application;

import java.time.LocalDate;

public class mArray {
	Node[] year;

	public mArray() {
		year = new Node[367];

	}

	public double getMilkWeight(int i) {
		return year[i].getMilkWeight();
	}

	public LocalDate getMilkDate(int i) {
		if(year[i] != null)
			return year[i].getMilkDate();
		return null;
	}

	public String getFarmID(int i) {
		return year[i].getFarmID();
	}

	public Node getNext(int i) {
		return year[i].getNext();
	}

	public int size() {
		return year.length;
	}

	public void addDay(double milk, LocalDate date, String ID) {
		int index = date.getDayOfYear();
		year[index] = new Node(milk, date, ID);
	}

}

class Node {
	double milkWeight;
	LocalDate milkDate;
	String farmID;
	Node next;

	public double getMilkWeight() {
		return milkWeight;
	}

	public void setMilkWeight(double milkWeight) {
		this.milkWeight = milkWeight;
	}

	public LocalDate getMilkDate() {
		return milkDate;
	}

	public void setMilkDate(LocalDate milkDate) {
		this.milkDate = milkDate;
	}

	public String getFarmID() {
		return farmID;
	}

	public void setFarmID(String farmID) {
		this.farmID = farmID;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node(double milkWeight, LocalDate milkDate, String farmID) {
		this.milkWeight = milkWeight;
		this.milkDate = milkDate;
		this.farmID = farmID;
		next = null;
	}

}
