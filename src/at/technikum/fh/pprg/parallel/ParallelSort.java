package at.technikum.fh.pprg.parallel;

import java.util.List;

public interface ParallelSort<T> {
    abstract List<T> sort(List<T> list, int treshold);
}
