package at.technikum.fh.pprg.test.parallel;

import at.technikum.fh.pprg.helper.DataGenerator;
import at.technikum.fh.pprg.parallel.QuickSort;
import at.technikum.fh.pprg.test.TestConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParallelQuickSortTester implements ParallelTester{

    public static void main(String[] args) {
        int size = 100000;
        ParallelQuickSortTester pqst = new ParallelQuickSortTester();
        long time = pqst.runTest(size);

        System.out.println(String.format("Sorted %d elements in %dms using the serial implementation of ParallelQuickSort:", size, time));
    }

    public long runTest(int size) {
        return runTest(size, (int) (size*0.5));
    }

    @Override
    public long runTest(List list) {
        return runTest(list, (int) (list.size()*0.5));
    }

    @Override
    public long runTest(int size, int threshold) {
        List<Integer> integers = DataGenerator.getListOfIntegers(size);

        return runTest(integers, threshold);
    }

    @Override
    public long runTest(List list, int threshold) {

        QuickSort qs = new QuickSort();
        long start = new Date().getTime();
        qs.sort((ArrayList<Integer>) list, 0, list.size()-1, threshold);
        long finished = new Date().getTime() - start;
        if(TestConstants.SHOW_SORTED_LIST)System.out.println(list);
        return finished;
    }
}
