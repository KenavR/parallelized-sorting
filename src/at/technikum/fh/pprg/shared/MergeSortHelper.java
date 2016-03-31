package at.technikum.fh.pprg.shared;

import java.util.ArrayList;
import java.util.List;

public class MergeSortHelper {

    private static MergeSortHelper instance;
    
    private MergeSortHelper() {
        
    }
    
    public static MergeSortHelper getInstance() {
        if(instance == null)instance = new MergeSortHelper();
        return instance;
    }
    
    
    
    public int devide(List list) {
        return list.size() / 2;
    }

    public List merge(List<Integer> leftList, List<Integer> rightList) {
        List<Integer> result = new ArrayList<>();
        int i = leftList.size() - 1;
        int j = rightList.size() - 1;
        int k = rightList.size() + leftList.size();
        for (int l = 0; l < k; ++l) { result.add(null); }

        while (k > 0) {
            result.set(--k, (j < 0 || (i >= 0 && leftList.get(i) >= rightList.get(j))) ? leftList.get(i--) : rightList.get(j--));
        }
        return (ArrayList)result;
    }
}
