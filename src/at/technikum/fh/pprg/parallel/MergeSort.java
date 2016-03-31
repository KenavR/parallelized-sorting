package at.technikum.fh.pprg.parallel;

import at.technikum.fh.pprg.shared.MergeSortHelper;
import at.technikum.fh.pprg.test.TestConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MergeSort {

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(0, TestConstants.NUMBER_OF_WORKER_THREADS, 60L, TimeUnit.NANOSECONDS, new SynchronousQueue<Runnable>());

    private final MergeSortHelper helper = MergeSortHelper.getInstance();

    public List sort(List list, int threshold) throws ExecutionException, InterruptedException {
        if (list.size() <= 1) return list;

        final int splitIdx = helper.devide(list);
        final ArrayList<Integer> left = new ArrayList<>(list.subList(0, splitIdx));
        final ArrayList<Integer> right = new ArrayList<>(list.subList(splitIdx, list.size()));

        Future<List<Integer>> leftFuture;
        Future<List<Integer>> rightFuture;

        leftFuture = (left.size() > threshold) ? executor.submit(() -> sort(left, threshold)) : CompletableFuture.completedFuture(sort(left, threshold));
        rightFuture = (right.size() > threshold) ? executor.submit(() -> sort(right,threshold)) : CompletableFuture.completedFuture(sort(right, threshold));

        return helper.merge(leftFuture.get(), rightFuture.get());
    }

}
