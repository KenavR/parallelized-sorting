package at.technikum.fh.pprg.parallel;

import at.technikum.fh.pprg.shared.QuickSort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ParallelQuickSort extends QuickSort implements ParallelSort<Integer>{

  public List<Integer> sort(List<Integer> list, int treshold) {
    try {
      return ParallelDivideAndConquer.divideAndConquer(super.trivial, super.solve, super.divide, super.combine, (ArrayList<Integer>) list, treshold);
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

}
