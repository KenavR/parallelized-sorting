package at.technikum.fh.pprg.parallel;

import at.technikum.fh.pprg.shared.divideAndConquer.ICombineFunction;
import at.technikum.fh.pprg.shared.divideAndConquer.IDivideFunction;
import at.technikum.fh.pprg.shared.divideAndConquer.ISolveFunction;
import at.technikum.fh.pprg.shared.divideAndConquer.ITrivialFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelDivideAndConquer {

    static ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.NANOSECONDS, new SynchronousQueue<Runnable>());

    public static <T> ArrayList<T> divideAndConquer(ITrivialFunction trivial, ISolveFunction solve, IDivideFunction divide, ICombineFunction combine, ArrayList<T> inputs, int treshold) throws Exception {
        //System.out.println("THREADS: "+executorService.getActiveCount());
        if (trivial.apply(inputs)) {
            return (ArrayList<T>) solve.apply(inputs);
        }

        final int splitIdx = divide.apply(inputs, 0, inputs.size() - 1);
        final ArrayList<T> left = new ArrayList<>(inputs.subList(0, splitIdx));
        final ArrayList<T> right = new ArrayList<>(inputs.subList(splitIdx, inputs.size()));

        Future<ArrayList<T>> leftFuture;
        Future<ArrayList<T>> rightFuture;

        leftFuture = (left.size() > treshold) ? executorService.submit(() -> divideAndConquer(trivial, solve, divide, combine, left, treshold)) : CompletableFuture.completedFuture(divideAndConquer(trivial, solve, divide, combine, left, treshold));
        rightFuture = (right.size() > treshold) ? executorService.submit(() -> divideAndConquer(trivial, solve, divide, combine, right, treshold)) : CompletableFuture.completedFuture(divideAndConquer(trivial, solve, divide, combine, right, treshold));


        /*left = new ArrayList<>(inputs.subList(0, splitIdx));
        final ArrayList<T> finalLeft = left;
        left = processList(left, treshold, new Callable<ArrayList<T>>() {
            @Override
            public ArrayList<T> call() throws Exception {
                return divideAndConquer(trivial, solve, divide,combine, finalLeft, treshold);
            }
        });

        right = new ArrayList<>(inputs.subList(splitIdx, inputs.size()));
        final ArrayList<T> finalRight = right;
        right = processList(left, treshold, new Callable<ArrayList<T>>() {
            @Override
            public ArrayList<T> call() throws Exception {
                return divideAndConquer(trivial, solve, divide,combine, finalRight, treshold);
            }
        });*/


        return (ArrayList<T>)combine.apply(leftFuture.get(), rightFuture.get());
    }

    private static <T> ArrayList<T> processList(List<T> list, int treshold, Callable<ArrayList<T>> dacFunc) throws Exception {
        if(list.size() > treshold) {
            //System.out.println("LEFT THREAD size: " + left.size() + " t: " + treshold);
            return (ArrayList<T>) spawnThread(dacFunc).get();
        } else {
            return (ArrayList<T>) dacFunc.call();
        }
    }

    private static <T> Future<T> spawnThread(Callable<ArrayList<T>> func) {
        //System.out.println("SPAWN THREAD!!!");
        return (Future<T>) executorService.submit(() -> func.call());
    }
}
