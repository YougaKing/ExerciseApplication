package youga.app;

import org.junit.Test;

import youga.app.pro.Person;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void testCall() {

        StringBuffer a = new StringBuffer("TOM");
        StringBuffer b = new StringBuffer("JERRY");
        oop(b, a);
        System.out.println(a + "," + b);

        int i = 0;
        add(i);
        int j = i++;
        System.out.println(i);
        System.out.println(j);
    }


    public void oop(StringBuffer x, StringBuffer y) {
        x.append("TOM");
        y = x;
    }


    public void add(int i) {
        i++;
    }
}