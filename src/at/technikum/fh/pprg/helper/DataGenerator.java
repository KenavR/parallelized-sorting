package at.technikum.fh.pprg.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class DataGenerator {

    public static List<Integer> getListOfIntegers(int size) {
        final Random random = new Random();
        List<Integer> integers = new ArrayList<>();
        IntStream.range(0, size).forEach(i -> integers.add(random.nextInt(size)));

        return integers;
    }

}
