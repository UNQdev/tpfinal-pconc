package main;

import java.util.LinkedList;
import java.util.List;

public class IntegerList {
	
	List<Integer> list;
	int threadsDisponibles;
	
	public synchronized int size() {
		return list.size();
	}
	public synchronized boolean isEmpty() {
		return list.isEmpty();
	}
	public synchronized boolean contains(Integer element) {
		return list.contains(element);
	}
	public synchronized void add(Integer element) {
		list.add(element);
	}
	public synchronized Integer get(int index) {
		return list.get(index);
	}
	public synchronized void set(Integer element, int index) {
		list.set(index, element);
	}

	public LinkedList<Integer> sort(LinkedList<Integer> list, int threadsDisponibles) {
		LinkedList<Integer> result = new LinkedList<Integer>();
		LinkedList<Integer> leftElements = new LinkedList<Integer>();
		LinkedList<Integer> rightElements = new LinkedList<Integer>();
		Integer pivot;
		
		pivot = this.getPivot();
		
		leftElements = this.lessThan(pivot, list);
		rightElements = this.greaterThan(pivot, list);
		
		//sorting strategy
		
		return result;
	}
	
	
	public Integer getPivot() {
		Integer pivot = this.list.get(this.list.size()/2);
		return pivot;
	}	
	public LinkedList<Integer> lessThan(Integer pivot, LinkedList<Integer> list) {
		LinkedList<Integer> result = new LinkedList<Integer>();
		for (Integer element : list) {
			if (element < pivot) {
				result.add(element);
			}
		}
		return result;
	}	
	public LinkedList<Integer> greaterThan(Integer pivot, LinkedList<Integer> list) {
		LinkedList<Integer> result = new LinkedList<Integer>();
		for (Integer elemento : list) {
			if (elemento >= pivot) {
				result.add(elemento);
			}
		}
		return result;
	}
}
