package youga.exerciseapplication;

import org.junit.Test;

import youga.exerciseapplication.sort.impl.BubbleSort;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class StructureMain {


    @Test
    public void main() {
        BubbleSort sort = new BubbleSort();
        sort.sort("ANSDJSDJSDHSUDUSJDSJ");

        sort.sort(new int[]{1, 23, 545, 656, 232, 44, 66, 77, 0, 89});
    }
}