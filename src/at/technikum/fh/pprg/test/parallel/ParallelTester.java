package at.technikum.fh.pprg.test.parallel;

import at.technikum.fh.pprg.test.Tester;

public interface ParallelTester extends Tester {
    long runTest(int size, int treshold);
}
