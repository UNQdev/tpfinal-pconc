package main;

import java.util.ArrayList;

public class Sorter extends Thread {

    private IntegerList asortedElements;
    private IntegerList fatherIntegerList;
    private boolean sorted;
    private ArrayList<Integer> sortedElements;
    //setup
    public Sorter(IntegerList asortedIntegerListElements, IntegerList father) {
    	setAsortedList(asortedIntegerListElements);
    	setFather(father);
    }
	//getters and setters
	public IntegerList getAsortedList() { return asortedElements; }
	public void setAsortedList(IntegerList list) { this.asortedElements = list; }
	private void setFather(IntegerList father) { this.fatherIntegerList = father; }
	public boolean isSorted() {
		return this.sorted;
	}
	public void run() {
		fatherIntegerList.decAvailableThreads();
		if (getAsortedList().getList().size() > 1) {
			this.sortedElements = getAsortedList().sort();
		} else {
			this.sortedElements = getAsortedList().getList();
		}
		sorted = true;
		fatherIntegerList.incAvailableThreads();
		fatherIntegerList.wakeUpWaiter();
	}
	public ArrayList<Integer> getSortedElements() { return sortedElements; }
	
	public static void main(String[] args)  {
		ArrayList<Integer> asortedList = new ArrayList<Integer>();
		Integer i = 10;
		while(i > 0){
			asortedList.add((int)(Math.random()*5000+1));
			i--;
		}

		IntegerList asortedIntegerList = new IntegerList(16, asortedList);

		ArrayList<Integer> sortedIntegerList = asortedIntegerList.sort();

		Printer.printList(sortedIntegerList);
	}
}
