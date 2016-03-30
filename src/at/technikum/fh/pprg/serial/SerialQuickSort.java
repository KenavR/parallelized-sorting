package at.technikum.fh.pprg.serial;

import at.technikum.fh.pprg.shared.QuickSort;

import java.util.ArrayList;
import java.util.List;

public class SerialQuickSort extends QuickSort implements SerialSort<Integer> {
  
  public List<Integer> sort(List<Integer> list) {
    return SerialDivideAndConquer.divideAndConquer(super.trivial, super.solve, super.divide, super.combine, (ArrayList<Integer>) list);
  }

}
