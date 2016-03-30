package at.technikum.fh.pprg.test.parallel;

import at.technikum.fh.pprg.helper.DataGenerator;
import at.technikum.fh.pprg.parallel.ParallelQuickSort;

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
    public long runTest(int size, int treshold) {
        List<Integer> integers = DataGenerator.getListOfIntegers(size);

        ParallelQuickSort qs = new ParallelQuickSort();
        long start = new Date().getTime();
        integers = qs.sort(integers, treshold);
        long finished = new Date().getTime() - start;
        //System.out.println(integers);
        return finished;
    }
}
