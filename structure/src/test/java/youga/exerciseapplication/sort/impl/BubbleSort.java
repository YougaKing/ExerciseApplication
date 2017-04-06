package youga.exerciseapplication.sort.impl;

import java.util.Arrays;
import java.util.Comparator;

import youga.exerciseapplication.sort.Sorter;

/**
 * Created by YougaKing on 2017/1/19.
 */

public class BubbleSort implements Sorter {

    Comparator mComparator;

    public BubbleSort() {
        this(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });
    }

    public BubbleSort(Comparator comparator) {
        mComparator = comparator;
    }

    @Override
    public void sort(CharSequence sequence) {
        int n = sequence.length();
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {

            }
        }

    }


    public void sort(int[] ints) {
        System.out.println(Arrays.toString(ints));
        for (int i = 0; i < ints.length - 1; i++) {
            for (int j = 0; j < ints.length - 1 - i; j++) {
                if (ints[j] > ints[j + 1]) {
                    int b = ints[j];
                    ints[j] = ints[j + 1];
                    ints[j + 1] = b;
                }
            }
        }
        System.out.println(Arrays.toString(ints));
    }
}
