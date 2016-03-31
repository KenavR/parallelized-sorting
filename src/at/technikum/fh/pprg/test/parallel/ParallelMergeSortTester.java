package at.technikum.fh.pprg.test.parallel;

import at.technikum.fh.pprg.helper.DataGenerator;
import at.technikum.fh.pprg.parallel.MergeSort;
import at.technikum.fh.pprg.test.TestConstants;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ParallelMergeSortTester implements ParallelTester {

    public static void main(String[] args) {
        int size = 1000;
        ParallelMergeSortTester pqst = new ParallelMergeSortTester();
        long time = pqst.runTest(size);

        System.out.println(String.format("Sorted %d elements in %dms using the serial implementation of ParallelQuickSort:", size, time));
    }

    public long runTest(int size) {
        return runTest(size, (int) (size*0.5));
    }

    @Override
    public long runTest(List list) {
        return runTest(list, (int) (list.size() * 0.5));
    }

    @Override
    public long runTest(int size, int threshold) {
        List<Integer> integers = DataGenerator.getListOfIntegers(size);

        return runTest(integers, threshold);
    }

    @Override
    public long runTest(List list, int threshold) {
        MergeSort ms = new MergeSort();
        long start = new Date().getTime();
        try {
            list = ms.sort(list, threshold);
            long finished = new Date().getTime() - start;
            if(TestConstants.SHOW_SORTED_LIST)System.out.println(list);
            return finished;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
