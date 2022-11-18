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

    @Test
    public void testAdd() {
        ArrayDeque<String> ad = new ArrayDeque<>();

        ad.add("a");
        ad.add("b");
        ad.add("c");
        ad.add("d");

        StringBuilder out = new StringBuilder();
        for (String s : ad) {
            out.append(s);
        }

        assertEquals("abcd", out.toString());

        assertEquals(4, ad.size());
    }

    @Test
    public void testOffer() {
        ArrayDeque<String> ad = new ArrayDeque<>();

        ad.offer("a");
        ad.offer("b");
        ad.offer("c");
        ad.offer("d");

        StringBuilder out = new StringBuilder();
        out.append(ad.poll());
        out.append(ad.poll());
        out.append(ad.poll());
        out.append(ad.poll());

        assertEquals("abcd", out.toString());

        assertEquals(0, ad.size());
    }

    @Test
    public void testAddFirst() {
        ArrayDeque<String> ad = new ArrayDeque<>();

        ad.addFirst("a");
        ad.addFirst("b");
        ad.addFirst("c");
        ad.addFirst("d");

        StringBuilder out = new StringBuilder();
        for (String s : ad) {
            out.append(s);
        }

        assertEquals("dcba", out.toString());

        assertEquals(4, ad.size());
    }

    @Test
    public void testAddLast() {
        ArrayDeque<String> ad = new ArrayDeque<>();

        ad.addLast("a");
        ad.addLast("b");
        ad.addLast("c");
        ad.addLast("d");

        StringBuilder out = new StringBuilder();
        for (String s : ad) {
            out.append(s);
        }

        assertEquals("abcd", out.toString());

        assertEquals(4, ad.size());
    }

    @Test
    public void testPush() {
        ArrayDeque<String> ad = new ArrayDeque<>();

        ad.push("a");
        ad.push("b");
        ad.push("c");
        ad.push("d");

        StringBuilder out = new StringBuilder();

        while (ad.size() > 0){
            out.append(ad.pop());
        }

        assertEquals("dcba", out.toString());

        assertEquals(0, ad.size());
    }

    @Test
    public void testInsert() {
        ArrayDeque<String> ad = new ArrayDeque<>();

        ad.add("a");
        ad.add("b");
        ad.add("c");
        ad.add("d");

        ad.insert(0,"1");

        StringBuilder out = new StringBuilder();

        for (String s : ad) {
            out.append(s);
        }

        assertEquals("1abcd", out.toString());

        assertEquals(5, ad.size());



        ad.insert(2,"2");
        out = new StringBuilder();

        for (String s : ad) {
            out.append(s);
        }

        assertEquals("1a2bcd", out.toString());

        assertEquals(6, ad.size());
    }

    @Test
    public void testSet() {
        ArrayDeque<String> ad = new ArrayDeque<>();

        ad.add("a");
        ad.add("b");
        ad.add("c");
        ad.add("d");

        ad.set(0,"1");

        StringBuilder out = new StringBuilder();

        for (String s : ad) {
            out.append(s);
        }

        assertEquals("1bcd", out.toString());

        assertEquals(4, ad.size());



        ad.set(1,"2");
        out = new StringBuilder();

        for (String s : ad) {
            out.append(s);
        }

        assertEquals("12cd", out.toString());

        assertEquals(4, ad.size());
    }

    @Test
    public void testPeek() {
        ArrayDeque<String> ad = new ArrayDeque<>();

        ad.offer("a");
        ad.offer("b");
        ad.offer("c");
        ad.offer("d");

        assertEquals("a", ad.peek());

        ad = new ArrayDeque<>();

        ad.push("a");
        ad.push("b");
        ad.push("c");
        ad.push("d");

        assertEquals("d", ad.peek());

    }

    @Test
    public void testPoll() {
        ArrayDeque<String> ad = new ArrayDeque<>();

        ad.offer("a");
        ad.offer("b");
        ad.offer("c");
        ad.offer("d");

        assertEquals("a", ad.poll());

        assertEquals(3, ad.size());

        assertEquals("b", ad.poll());

        assertEquals(2, ad.size());

    }

    @Test
    public void testPop() {
        ArrayDeque<String> ad = new ArrayDeque<>();

        ad.push("a");
        ad.push("b");
        ad.push("c");
        ad.push("d");

        assertEquals("d", ad.pop());

        assertEquals(3, ad.size());

        assertEquals("c", ad.poll());

        assertEquals(2, ad.size());

    }

    @Test
    public void testGet() {
        ArrayDeque<String> ad = new ArrayDeque<>();

        ad.add("a");
        ad.add("b");
        ad.add("c");
        ad.add("d");

        assertEquals("a", ad.get(0));

        assertEquals(4, ad.size());

        assertEquals("c", ad.get(2));

        assertEquals(4, ad.size());

    }

    @Test
    public void testGet2() {
        ArrayDeque<String> ad = new ArrayDeque<>();

        ad.add("a");
        ad.add("b");
        ad.add("c");
        ad.add("d");

        assertEquals("a", ad.get("a"));

        assertEquals(4, ad.size());

        assertEquals("c", ad.get("c"));

        assertEquals(4, ad.size());

        assertNull(ad.get("e"));

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetError() {
        ArrayDeque<String> ad = new ArrayDeque<>();

        ad.add("a");
        ad.add("b");
        ad.add("c");
        ad.add("d");

//        ad.get(-1);



    }

    @Test
    public void testRemove(){
        ArrayDeque<String> ad = new ArrayDeque<>();

        ad.add("a");
        ad.add("b");
        ad.add("c");
        ad.add("d");

        assertEquals("a", ad.remove(0));

        assertEquals(3, ad.size());

        assertEquals("c", ad.remove(1));

        assertEquals(2, ad.size());
    }

    @Test
    public void testRemove2(){
        ArrayDeque<String> ad = new ArrayDeque<>();

        ad.add("a");
        ad.add("b");
        ad.add("c");
        ad.add("d");

        assertEquals("a", ad.remove("a"));

        assertEquals(3, ad.size());

        assertNull(ad.remove("e"));

        assertEquals(3, ad.size());
    }

    @Test
    public void testRemoveFirst(){
        ArrayDeque<String> ad = new ArrayDeque<>();

        ad.add("a");
        ad.add("b");
        ad.add("c");
        ad.add("d");

        assertEquals("a", ad.removeFirst());

        assertEquals(3, ad.size());

        assertEquals("b", ad.removeFirst());

        assertEquals(2, ad.size());
    }

    @Test
    public void testRemoveLast(){
        ArrayDeque<String> ad = new ArrayDeque<>();

        ad.add("a");
        ad.add("b");
        ad.add("c");
        ad.add("d");

        assertEquals("d", ad.removeLast());

        assertEquals(3, ad.size());

        assertEquals("c", ad.removeLast());

        assertEquals(2, ad.size());
    }

    @Test
    public void testCapacity(){
        ArrayDeque<String> ad = new ArrayDeque<>();

        ad.add("a");
        ad.add("b");
        ad.add("c");

        assertEquals(7, ad.capacity());

        ad.add("d");
        ad.add("d");
        ad.add("d");

        assertEquals(15, ad.capacity());


    }

    @Test
    public void testIsEmpty(){
        ArrayDeque<String> ad = new ArrayDeque<>();

        assertTrue(ad.isEmpty());

        ad.add("a");
        ad.add("b");
        ad.add("c");

        assertFalse(ad.isEmpty());




    }

    @Test
    public void testTrimToSize(){
        ArrayDeque<String> ad = new ArrayDeque<>();

        ad.add("a");
        ad.add("b");
        ad.add("c");

        assertEquals(7, ad.capacity());

        ad.trimToSize();

        assertEquals(3, ad.capacity());




    }



}