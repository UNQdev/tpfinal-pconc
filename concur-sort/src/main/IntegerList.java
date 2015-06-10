package main;

import java.util.ArrayList;

public class IntegerList {

	private ArrayList<Integer> list;
	private int availableThreads;
	/*
	 * GETTERS AND SETTERS
	 */
	public ArrayList<Integer> getList() { return list; }
	public void setList(ArrayList<Integer> list) { this.list = list; }
	public synchronized int getAvailableThreads() { return availableThreads; }
	public void setAvailableThreads(int availableThreads) { this.availableThreads = availableThreads; }
	/*
	 * SETUP
	 */
	public IntegerList(int availableThreads, ArrayList<Integer> list) {
		this.availableThreads = availableThreads;
		this.list = list;
	}
	public void generateElements(int quantity) {
		for (int i = 0; i < quantity; i++) {
			list.add((int) (Math.random() * 4132 + 1));
		}
	}
	/*
	 * IntegerList API
	 */
	public synchronized int size() { return list.size(); }
	public synchronized boolean isEmpty() { return list.isEmpty(); }
	public synchronized boolean contains(Integer element) { return list.contains(element); }
	public synchronized void add(Integer element) { list.add(element); }
	public synchronized Integer get(int index) { return list.get(index); }
	public synchronized void set(Integer element, int index) { list.set(index, element); }
	public synchronized ArrayList<Integer> sort() {
		ArrayList<Integer> sortedElements = new ArrayList<Integer>();

		if (!this.isEmpty()) {
			this.waitAvailableThreads();
			
//			IntegerList leftIntegerListElements = new IntegerList(lessThan(getPivot()));
//			IntegerList rightIntegerListElements = new IntegerList(greaterThan(getPivot()));

			System.out.println("SORT IZQ");
			Sorter minorElements = this.sendToSorter(lessThan(getPivot()), this);
			while (availableThreads <= 1) { this.waitLinealSort(); }
			System.out.println("SORT DER");
			Sorter greaterElements = this.sendToSorter(greaterThan(getPivot()), this);
			while (!minorElements.isSorted() && !greaterElements.isSorted()) { this.waitSorted(); }
			
			sortedElements.addAll(minorElements.getSortedElements());
			sortedElements.add(this.getPivot());
			sortedElements.addAll(greaterElements.getSortedElements());
		
		}
		
		return sortedElements; 
	}
	private Sorter sendToSorter(ArrayList<Integer> integerListElements,
			IntegerList integerList) {
		IntegerList asortedIntergerList = new IntegerList(this.getAvailableThreads(), integerListElements);
		Sorter sorter = new Sorter(asortedIntergerList, this);
		
		sorter.start();
		
		return sorter;
	}
	/*
	 * LIST STRIP
	 */
	public synchronized Integer getPivot() { return get(size() / 2); }
	public synchronized ArrayList<Integer> lessThan(Integer pivot) {
		ArrayList<Integer> lessThanPivot = new ArrayList<Integer>();
		for (Integer element : this.list) {
			if (element < pivot) {
				lessThanPivot.add(element);
			}
		}
		return lessThanPivot;
	}
	public synchronized ArrayList<Integer> greaterThan(Integer pivot) {
		ArrayList<Integer> greaterThanPivot = new ArrayList<Integer>();
		for (Integer element : this.list) {
			if (element > pivot) {
				greaterThanPivot.add(element);
			}
		}
		return greaterThanPivot;
	}
	/*
	 * THREAD CONTROL
	 */
	public void waitAvailableThreads() {
		try {
			if (this.getAvailableThreads() == 0) {
				System.out.println("BEGIN WAITING FOR AVAILABLE THREADS");
				this.wait();
				System.out.println("FINISHED WAITING THREADS");
			}
		} catch (InterruptedException e) {
			System.out.println("WAITING_AVAILABLE_THREADS ERROR: "
					+ e.getMessage());
		}		
	}
	public void waitLinealSort() {
		try {
			System.out.println("BEGIN WAITING FOR LINEAL SORT");
			this.wait();
			System.out.println("FINISHED WAITING FOR LINEAL SORT");
		} catch (InterruptedException e) {
			System.out.println("WAITING_LINEAL_SORT ERROR: " + e.getMessage());
		}		
	}
	public void waitSorted() {
		try {
			System.out.println("BEGIN WAITING FOR BOTH TO BE SORTED");
			this.wait();
			System.out.println("FINISHED WAITING FOR BOTH TO BE SORTED");
		} catch (InterruptedException e) {
			System.out.println("WAITING_SORTED ERROR: " + e.getMessage());
		}
	}
	public synchronized void decAvailableThreads() { this.availableThreads--; }
	public synchronized void incAvailableThreads() { this.availableThreads++; }
	public synchronized void wakeUpWaiter() { notify(); }
}
