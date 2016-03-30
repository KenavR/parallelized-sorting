package at.technikum.fh.pprg.serial;

import at.technikum.fh.pprg.shared.MergeSort;
import at.technikum.fh.pprg.shared.divideAndConquer.ICombineFunction;
import at.technikum.fh.pprg.shared.divideAndConquer.IDivideFunction;
import at.technikum.fh.pprg.shared.divideAndConquer.ISolveFunction;
import at.technikum.fh.pprg.shared.divideAndConquer.ITrivialFunction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class SerialMergeSort extends MergeSort implements SerialSort<Integer>{

  public List<Integer> sort(List<Integer> list) {
    return SerialDivideAndConquer.divideAndConquer(super.trivial, super.solve, divide, combine, (ArrayList<Integer>) list);
  }
}
