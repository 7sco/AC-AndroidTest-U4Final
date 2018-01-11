package nyc.c4q.androidtest_unit4final;

import android.util.Log;

import java.util.List;

/**
 * Created by justiceo on 1/7/18.
 */

public class Sort {

    /**
     * Sorts a list using the selection sort algorithm.
     * See lecture on sorting: https://github.com/C4Q/AC-Android/tree/v2/DSA/sorting
     *
     * When `isAscending` is true, the list is sorted in ascending alphabetical order from a to z,
     * otherwise it is sorted in descending order from z to a.
     * @param list
     * @param isAscending
     */
    public static void selectionSort(List<String> list, boolean isAscending) {
        // TODO: Implement selection sort.
        // You may not use Collections.sort or its equivalent
        // You may not implement another sorting algorithm that is not "selection sort"
        // Tip: Try a version without ordering first.

//        for (int i=0; i<list.size(); i++) {
//
//            int pos=i;
//            String aux=list.get(i);
//
//            while (pos>0 &&list.get(i-1).compareTo(aux) > 0){
//
//                list.set(pos, list.get(pos-1));
//                pos--;
//            }
//            list.set(pos,aux );
        if(isAscending) {
            for (int i = list.size(); i > 0; i--) {
                int lastObject = 0;
                for (int j = 0; j < i; j++) {

                    if (list.get(j).
                            compareTo(list.get(lastObject)) > 0) {
                        lastObject = j;
                    }
                    String temp = list.get(lastObject);

                    list.set(lastObject, list.get(i - 1));
                    list.set((i - 1), temp);
                }
            }
        }
        if(!isAscending){
            for (int i = list.size(); i > 0; i--) {
                int lastObject = 0;
                for (int j = 0; j < i; j++) {

                    if (list.get(j).
                            compareTo(list.get(lastObject)) < 0) {
                        lastObject = j;
                    }
                    String temp = list.get(lastObject);

                    list.set(lastObject, list.get(i - 1));
                    list.set((i - 1), temp);
                }
            }
        }

//            
        }


}
