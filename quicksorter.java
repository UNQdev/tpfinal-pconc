package list;

public class QuickSorter extends Thread {

    private Integer maxThreadsCount;
    private QuickSorter parent;
    private IntegerList listToSort;
    private int sortedLists;

    public QuickSorter(IntegerList listToSort, Integer maxThreadsCount) {
        this.listToSort = listToSort;
        sortedLists = 0;
        this.maxThreadsCount = maxThreadsCount;
    }

    public QuickSorter(IntegerList listToSort, QuickSorter parent) {
        this(listToSort, parent.maxThreadsCount);
        this.parent = parent;
    }

    public synchronized void sort() throws InterruptedException {
        if(listToSort.size() <= 1) return;
        int pivot = listToSort.get(listToSort.size() / 2);
        IntegerList left = listToSort.lessThan(pivot);
        IntegerList right = listToSort.greaterThan(pivot);

        new QuickSorter(left, this).start();
        new QuickSorter(right, this).start();

        while(sortedLists < 2)
            wait();

        left.add(pivot);
        left.concat(right);
        listToSort.sortLike(left, this);
    }

    public synchronized void finishedSorting() {
        sortedLists ++;
        notifyAll();
    }

    @Override
    public void run() {
        try {
            sortAndNotify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sortAndNotify() throws InterruptedException {
        sort();
        parent.finishedSorting();
    }
}