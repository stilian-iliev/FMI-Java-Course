package implementations;

import junit.framework.TestCase;
import org.junit.Test;

public class ArrayDequeTest extends TestCase {

    @Test
    public void testAD() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

//        ad.addFirst(0);
//        ad.addLast(1);
//        ad.addLast(2);
//        ad.addLast(3);
//        ad.addLast(4);
//        ad.addLast(5);
//        ad.addLast(6);


        ad.addFirst(0);
        ad.addFirst(-1);
        ad.addLast(1);
        ad.insert(2, 9);
        ad.addLast(3);

//        ad.forEach(System.out::println);



    }

    @Test
    public void testAD2() {
        ArrayDeque<String> ad = new ArrayDeque<>();


        ad.addFirst("0");
        ad.addFirst("-1");
        ad.addFirst("-2");
        ad.addLast("1");
        ad.addLast("2");
        ad.remove("0");

//        ad.forEach(System.out::println);



    }

}