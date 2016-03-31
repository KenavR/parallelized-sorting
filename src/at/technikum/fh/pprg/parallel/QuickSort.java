package at.technikum.fh.pprg.parallel;

import at.technikum.fh.pprg.test.TestConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class QuickSort {

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(0, TestConstants.NUMBER_OF_WORKER_THREADS, 60L, TimeUnit.NANOSECONDS, new SynchronousQueue<Runnable>());

    public void sort(List<Integer> list, int left, int right, int threshold) {
        int i = left, j = right;
        int tmp;
        int pivot = list.get((left + right) / 2);

        int len = right - left + 1;

        if (len <= 1)
            return;

	/* partition */
        while (i <= j) {
            while (list.get(i) < pivot)
                i++;
            while (list.get(j) > pivot)
                j--;
            if (i <= j) {
                Collections.swap(list, i, j);
                i++;
                j--;
            }
        }


	/* recursion */

        Future future = runSublist(list, left, j, threshold);
        sort(list, i, right, threshold);

        try {
            future.get(1000, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Future runSublist(List<Integer> list, int low, int high, int threshold) {
        if (low < high) {
            if (high - low > threshold)
                return (Future) executor.submit(() -> sort(list, low, high, threshold));
            else
                sort(list, low, high, threshold);

        }
        return CompletableFuture.completedFuture(list);
    }
}
