package at.technikum.fh.pprg.test;

import at.technikum.fh.pprg.helper.DataGenerator;
import at.technikum.fh.pprg.test.parallel.ParallelMergeSortTester;
import at.technikum.fh.pprg.test.parallel.ParallelQuickSortTester;
import at.technikum.fh.pprg.test.serial.SerialMergeSortTester;
import at.technikum.fh.pprg.test.serial.SerialQuickSortTester;

import java.util.List;

public class CompareImplementations {
    public static void main(String[] args) {
        int size = TestConstants.NUMBER_OF_ELEMENTS;

        List<Integer> list = DataGenerator.getListOfIntegers(size);
        SerialMergeSortTester smst = new SerialMergeSortTester();
        SerialQuickSortTester sqst = new SerialQuickSortTester();
        System.out.println("**** Serial Implementations ****");
        System.out.println(String.format("Sorted %d elements in %dms using Serial implementation of MergeSort", list.size(), smst.runTest(list)));
        System.out.println(String.format("Sorted %d elements in %dms using Serial implementation of QuickSort", list.size(), sqst.runTest(list)));
        System.out.println();
        System.out.println("**** Parallel Implementations with adaptive threshold ****");
        for(int threshold = TestConstants.START_THREASHOLD; threshold <= size; threshold*=10) {
            runComparableTest(list, threshold);
        }
    }

    public static void runComparableTest(List<Integer> list, int threshold) {
        ParallelMergeSortTester pmst = new ParallelMergeSortTester();
        ParallelQuickSortTester pqst = new ParallelQuickSortTester();

        System.out.println(String.format("Sorted %d elements in %dms using Parallel implementation of MergeSort with a threshold of %d", list.size(), pmst.runTest(list, threshold), threshold));
        System.out.println(String.format("Sorted %d elements in %dms using Parallel implementation of QuickSort with a threshold of %d", list.size(), pqst.runTest(list, threshold), threshold));
        System.out.println("****************************************************************************************************************");
        System.out.println();

    }

}
