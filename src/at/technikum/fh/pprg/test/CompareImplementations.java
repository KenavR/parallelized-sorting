package at.technikum.fh.pprg.test;

import at.technikum.fh.pprg.test.parallel.ParallelMergeSortTester;
import at.technikum.fh.pprg.test.parallel.ParallelQuickSortTester;
import at.technikum.fh.pprg.test.serial.SerialMergeSortTester;
import at.technikum.fh.pprg.test.serial.SerialQuickSortTester;

public class CompareImplementations {
    public static void main(String[] args) {
        int size = 10000;
        int treshold = (int) (size*0.3);

        SerialMergeSortTester smst = new SerialMergeSortTester();
        SerialQuickSortTester sqst = new SerialQuickSortTester();
        ParallelMergeSortTester pmst = new ParallelMergeSortTester();
        ParallelQuickSortTester pqst = new ParallelQuickSortTester();

        System.out.println(String.format("Sorted %d elements in %dms using Serial implementation of MergeSort", size, smst.runTest(size)));
        System.out.println(String.format("Sorted %d elements in %dms using Parallel implementation of MergeSort with a treshold of %d", size, pmst.runTest(size, treshold), treshold));
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("Sorted %d elements in %dms using Serial implementation of QuickSort", size, sqst.runTest(size)));
        System.out.println(String.format("Sorted %d elements in %dms using Parallel implementation of QuickSort with a treshold of %d", size, pqst.runTest(size, treshold), treshold));
    }

}
