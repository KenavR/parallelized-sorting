package at.technikum.fh.pprg.serial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class SerialQuickSort {

    public void sort(List<Integer> list, int left, int right) {
        int i = left, j = right;
        int pivot = list.get((left + right) / 2);

        int len = right - left + 1;

        if (len <= 1)
            return;

	/* partition */
        while (i <= j) {
            while (list.get(i) < pivot)
                i++;
            while (list.get(j) > pivot)
                j--;
            if (i <= j) {
                Collections.swap(list, i, j);
                i++;
                j--;
            }
        }


	/* recursion */
        sort(list, left, j);
        sort(list, i, right);

    }

}
