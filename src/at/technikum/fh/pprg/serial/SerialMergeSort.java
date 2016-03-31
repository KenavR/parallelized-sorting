package at.technikum.fh.pprg.serial;

import at.technikum.fh.pprg.shared.MergeSortHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SerialMergeSort {

    private final MergeSortHelper helper = MergeSortHelper.getInstance();

    public List sort(List list){
        if (list.size() <= 1) return list;

        final int splitIdx = helper.devide(list);
        final ArrayList<Integer> left = new ArrayList<>(list.subList(0, splitIdx));
        final ArrayList<Integer> right = new ArrayList<>(list.subList(splitIdx, list.size()));

        return helper.merge(sort(left), sort(right));
    }



}
