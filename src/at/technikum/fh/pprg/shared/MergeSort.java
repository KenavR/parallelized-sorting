package at.technikum.fh.pprg.shared;

import at.technikum.fh.pprg.shared.divideAndConquer.ICombineFunction;
import at.technikum.fh.pprg.shared.divideAndConquer.IDivideFunction;
import at.technikum.fh.pprg.shared.divideAndConquer.ISolveFunction;
import at.technikum.fh.pprg.shared.divideAndConquer.ITrivialFunction;
import at.technikum.fh.pprg.test.TestConstants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class MergeSort {

  interface IMSTrivialFunction<I extends ArrayList<Integer>> extends ITrivialFunction<I> {
  }

  interface IMSSolveFunction<I, R> extends ISolveFunction<I, R> {
  }

  interface IMSDivideFunction extends IDivideFunction<Integer> {
  }

  interface IMSCombineFunction<I extends ArrayList<Integer>> extends ICombineFunction<I> {
  }

  protected IMSTrivialFunction trivial = (i) -> ((Collection) i).size() <= 1;

  protected IMSSolveFunction solve = (i) -> i;

  protected IMSDivideFunction divide = (inputs, left, right) -> inputs.size() / 2;

  protected IMSCombineFunction<ArrayList<Integer>> combine = (leftList, rightList) -> {
    addDelay();
    List<Integer> result = new ArrayList<>();
    int i = leftList.size() - 1;
    int j = rightList.size() - 1;
    int k = rightList.size() + leftList.size();
    for (int l = 0; l < k; ++l) { result.add(null); }

    while (k > 0) {
      result.set(--k, (j < 0 || (i >= 0 && leftList.get(i) >= rightList.get(j))) ? leftList.get(i--) : rightList.get(j--));
    }
    return (ArrayList)result;
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
