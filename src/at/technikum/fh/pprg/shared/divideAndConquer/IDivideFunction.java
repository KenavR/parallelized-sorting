package at.technikum.fh.pprg.shared.divideAndConquer;

import java.util.List;

public interface IDivideFunction<I> {
  public int apply(List<I> t, Integer left, Integer right);
}
