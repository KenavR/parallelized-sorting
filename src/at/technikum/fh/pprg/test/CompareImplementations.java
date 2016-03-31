package at.technikum.fh.pprg.test;

import at.technikum.fh.pprg.helper.DataGenerator;
import at.technikum.fh.pprg.test.parallel.ParallelMergeSortTester;
import at.technikum.fh.pprg.test.parallel.ParallelQuickSortTester;
import at.technikum.fh.pprg.test.serial.SerialMergeSortTester;
import at.technikum.fh.pprg.test.serial.SerialQuickSortTester;

import java.util.List;

public class CompareImplementations {
    public static void main(String[] args) {
        int size = 10000000;
        int threshold = (int) (10000);

        SerialMergeSortTester smst = new SerialMergeSortTester();
        SerialQuickSortTester sqst = new SerialQuickSortTester();
        ParallelMergeSortTester pmst = new ParallelMergeSortTester();
        ParallelQuickSortTester pqst = new ParallelQuickSortTester();

        List<Integer> list = DataGenerator.getListOfIntegers(size);

        System.out.println(String.format("Sorted %d elements in %dms using Serial implementation of MergeSort", size, smst.runTest(list)));
        System.out.println(String.format("Sorted %d elements in %dms using Parallel implementation of MergeSort with a threshold of %d", size, pmst.runTest(list, threshold), threshold));
        System.out.println("----------------------------------------------------------------------------------------------------------------");

        System.out.println(String.format("Sorted %d elements in %dms using Serial implementation of QuickSort", size, sqst.runTest(list)));
        System.out.println(String.format("Sorted %d elements in %dms using Parallel implementation of QuickSort with a threshold of %d", size, pqst.runTest(list, threshold), threshold));
    }

}
