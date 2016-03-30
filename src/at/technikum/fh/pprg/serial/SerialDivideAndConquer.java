package at.technikum.fh.pprg.serial;

import at.technikum.fh.pprg.shared.divideAndConquer.ICombineFunction;
import at.technikum.fh.pprg.shared.divideAndConquer.IDivideFunction;
import at.technikum.fh.pprg.shared.divideAndConquer.ISolveFunction;
import at.technikum.fh.pprg.shared.divideAndConquer.ITrivialFunction;

import java.util.ArrayList;

public class SerialDivideAndConquer {

  public static <T> ArrayList<T> divideAndConquer(ITrivialFunction trivial, ISolveFunction solve, IDivideFunction divide, ICombineFunction combine, ArrayList<T> inputs) {
    if (trivial.apply(inputs)) {
      return (ArrayList<T>) solve.apply(inputs);
    }

    int splitIdx =  divide.apply(inputs, 0, inputs.size()-1);
     
    ArrayList<T> left = divideAndConquer(trivial, solve, divide, combine, new ArrayList<>(inputs.subList(0, splitIdx)));
    ArrayList<T> right = divideAndConquer(trivial, solve, divide, combine, new ArrayList<>(inputs.subList(splitIdx, inputs.size())));    
    
    return (ArrayList<T>) combine.apply(left, right);
  }

}
