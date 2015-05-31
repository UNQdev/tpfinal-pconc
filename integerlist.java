package list;

import java.util.LinkedList;
import java.util.List;

public class IntegerList {

	private List<Integer> list;
	private int maxThreads;

	public IntegerList() {
		list = new LinkedList<>();
	}

	public IntegerList(List<Integer> aList) {
		list = aList;
	}

	public synchronized int size() {
		return list.size();
	}
	
	public synchronized boolean isEmpty() {
		return list.isEmpty();
	}
	
	public synchronized boolean contains(Integer anInteger) {
		return list.contains(anInteger);
	}
	
	public synchronized void add(Integer anInteger) {
		list.add(anInteger);
	}
	
	public synchronized int get(Integer anIndex) {
		return list.get(anIndex);
	}
	
	public synchronized void set(Integer anIndex, Integer anElementToAdd) {
		list.set(anIndex, anElementToAdd);
	}
	
	public synchronized void sort() throws InterruptedException {
		new QuickSorter(this, maxThreads).sort();
	}

	public synchronized IntegerList lessThan(Integer anInteger) {
		IntegerList result = new IntegerList();
		for (Integer listElement : list) {
			if(listElement < anInteger)
				result.add(listElement);
		}
		return result;
	}

	public synchronized IntegerList greaterThan(Integer anInteger) {
		IntegerList result = new IntegerList();
		for (Integer listElement : list) {
			if(listElement > anInteger)
				result.add(listElement);
		}
		return result;
	}

	public synchronized void concat(IntegerList anotherList) {
		list.addAll(anotherList.toLinkedList());
	}

	public synchronized void sortLike(IntegerList aList, QuickSorter aSorter) {
		list = aList.toLinkedList();
	}

	@Override
	public String toString() {
		return list.toString();
	}

	private List<Integer> toLinkedList() {
		return list;
	}

	public void maxThreads(int threadsCount) {
		maxThreads = threadsCount;
	}
}