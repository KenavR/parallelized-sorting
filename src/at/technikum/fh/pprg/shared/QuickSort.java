package at.technikum.fh.pprg.shared;

import at.technikum.fh.pprg.shared.divideAndConquer.ICombineFunction;
import at.technikum.fh.pprg.shared.divideAndConquer.IDivideFunction;
import at.technikum.fh.pprg.shared.divideAndConquer.ISolveFunction;
import at.technikum.fh.pprg.shared.divideAndConquer.ITrivialFunction;
import at.technikum.fh.pprg.test.TestConstants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class QuickSort {

  interface IQSTrivialFunction<I extends ArrayList<Integer>> extends ITrivialFunction<I> {
  }

  interface IQSSolveFunction<I, R> extends ISolveFunction<I, R> {
  }

  interface IQSDivideFunction extends IDivideFunction<Integer> {
  }

  interface IQSCombineFunction<I extends ArrayList<Integer>> extends ICombineFunction<I> {
  }

  protected final IQSTrivialFunction trivial = (i) -> ((Collection) i).size() <= 1;

  protected final IQSSolveFunction solve = (i) -> i;

  protected final IQSDivideFunction divide = (inputs, left, right) -> {
    addDelay();
    int pivot = inputs.get(left);
    left++;
    int temp;
    while (left <= right) {
      while (left < inputs.size() - 1 && inputs.get(left) < pivot) {
        left++;
      }
      while (inputs.get(right) > pivot) {
        right--;
      }
      if (left >= right) {
        break;
      } else {
        temp = inputs.get(left);
        inputs.set(left, inputs.get(right));
        inputs.set(right, temp);
        left++;
        right--;
      }
    }
    temp = inputs.get(right);
    inputs.set(right, inputs.get(0));
    inputs.set(0, temp);
    return left;
  };

  protected final IQSCombineFunction<ArrayList<Integer>> combine = (i, j) -> {
    return ((ArrayList) Stream.concat(i.stream(), j.stream()).collect(Collectors.toList()));
  };

  private void addDelay() {
    if(TestConstants.DELAY <= 0) return;
    try {
      Thread.sleep(TestConstants.DELAY);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
