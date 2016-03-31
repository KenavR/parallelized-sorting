package at.technikum.fh.pprg.applications;

import at.technikum.fh.pprg.helper.DataGenerator;
import at.technikum.fh.pprg.parallel.MergeSort;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class MergeSortApplication {

    public static void main(String[] args) {
        final int elements = contains(args, 0) ? Integer.valueOf(args[0]) : 100000;
        final int threshold = contains(args, 1) ? Integer.valueOf(args[1]) : (int) (elements * 0.3);

        MergeSort ms = new MergeSort();
        long start = new Date().getTime();
        try {
            ms.sort(DataGenerator.getListOfIntegers(elements), threshold);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(String.format("Sorted %d elements in %dms using Parallel implementation of MergeSort with a threshold of %d", elements, new Date().getTime() - start, threshold));

        exit();
    }

    public static boolean contains(String[] args, int index) {
        return (args.length > index && args[index] != null);
    }

    public static void exit() {
        System.out.println("Press enter to exit");
        try {
            int key = 0;
            do {
                key = System.in.read();
                if (key == 10) System.exit(0);
            } while (key != 10);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
