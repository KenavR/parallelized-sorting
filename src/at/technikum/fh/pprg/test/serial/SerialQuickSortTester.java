package at.technikum.fh.pprg.test.serial;

import at.technikum.fh.pprg.helper.DataGenerator;
import at.technikum.fh.pprg.serial.SerialQuickSort;
import at.technikum.fh.pprg.test.Tester;

import java.util.Date;
import java.util.List;

public class SerialQuickSortTester implements Tester {

    public static void main(String[] args) {
        int size = 100000;
        SerialQuickSortTester pqst = new SerialQuickSortTester();
        long time = pqst.runTest(size);

        System.out.println(String.format("Sorted %d elements in %dms using the serial implementation of ParallelQuickSort:", size, time));
    }

    public long runTest(int size) {
        List<Integer> integers = DataGenerator.getListOfIntegers(size);

        SerialQuickSort qs = new SerialQuickSort();
        long start = new Date().getTime();
        integers = qs.sort(integers);
        long finished = new Date().getTime() - start;
        //System.out.println(integers);
        return finished;
    }
}
