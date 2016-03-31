package at.technikum.fh.pprg.test.parallel;

import at.technikum.fh.pprg.test.Tester;

import java.util.List;

public interface ParallelTester extends Tester {
    long runTest(int size, int threshold);
    long runTest(List list , int threshold);
}
