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
	public IntegerList(ArrayList<Integer> list, int availableThreads) {
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
	public synchronized void sort() {
		Buffer buffer = new Buffer();
		Counter counter = new Counter(size()-1);
		for(int i = 0; i < availableThreads; i++) {
			new Sorter(this, buffer, counter).start();
		}
		Range init_range = new Range(0, size()-1);
		buffer.push(init_range);
		counter.waitZero();
		Range invalid_range = new Range(-1,0);
		for (int i = 0; i < availableThreads ; i++) {
			buffer.push(invalid_range);
		}
	}
	
	public int partition(int begin, int end) {
    	int lessThanPivot = 0;
        Integer pivot = this.get(begin); 
    	for (int i = begin+1; i <= end; i++) {    		
    		if (this.get(i) < pivot ) {
    	        swap(i, begin+1 + lessThanPivot);
    	        lessThanPivot++;
    		}
    	}
    	System.out.println(lessThanPivot);
		return lessThanPivot;
    }
    
	private void swap(int firstIndex, int secondIndex) {    	
		Integer elementoEnA = this.get(firstIndex);
		Integer elementoEnB = this.get(secondIndex);
		this.set(secondIndex, elementoEnA);
		this.set(firstIndex, elementoEnB);
	}
	
	@Override
	public String toString() { return this.list.toString(); }
	
}
