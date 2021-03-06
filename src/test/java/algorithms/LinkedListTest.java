package algorithms;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LinkedListTest {
    @Test
    public void test1() {
        LinkedList<Integer> ll = new LinkedList<>();
        ll.addFront(1);
        ll.addFront(2);
        ll.addBack(3);
        ll.addBack(4);
        assertEquals(Integer.valueOf(2), ll.removeFront());
        assertEquals(Integer.valueOf(1), ll.removeFront());
        assertEquals(Integer.valueOf(3), ll.removeFront());
        assertEquals(Integer.valueOf(4), ll.removeFront());
        assertNull(ll.removeFront());
    }

    @Test
    public void test2() {
        LinkedList<Integer> ll = new LinkedList<>();
        ll.addBack(1);
        ll.addBack(2);
        ll.addFront(3);
        ll.addFront(4);
        assertEquals(Integer.valueOf(4), ll.removeFront());
        assertEquals(Integer.valueOf(3), ll.removeFront());
        assertEquals(Integer.valueOf(1), ll.removeFront());
        assertEquals(Integer.valueOf(2), ll.removeFront());
        assertNull(ll.removeFront());
    }
}
