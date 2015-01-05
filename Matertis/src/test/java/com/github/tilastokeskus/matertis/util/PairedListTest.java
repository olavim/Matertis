package com.github.tilastokeskus.matertis.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tilastokeskus
 */
public class PairedListTest {
    
    private PairedList list;
    
    public PairedListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        list = new PairedList();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void constructor_shouldInitializeEmptyList() {
        assertTrue(list.isEmpty());
        assertTrue(list.size() == 0);
    }
    
    @Test
    public void method_add1_shouldAddElements() {
        assertTrue(list.add("first", "second"));
        assertTrue(list.get(0).equals(new Pair("first", "second")));
        assertTrue(list.size() == 1);
    }
    
    @Test
    public void method_add2_shouldAddElements() {
        assertTrue(list.add("first", 1));
        assertTrue(list.add("first", 2));
        assertTrue(list.add("first", 3));
        assertTrue(list.add("first", 4));
        list.add(1, "second", 100);
        assertTrue(list.get(1).equals(new Pair("second", 100)));
        assertTrue(list.size() == 5);
    }
    
    @Test
    public void method_add3_shouldAddElements() {
        assertTrue(list.add(new Pair("first", "second")));
        assertTrue(list.get(0).equals(new Pair("first", "second")));
        assertTrue(list.size() == 1);
    }
    
    @Test
    public void method_add4_shouldAddElements() {
        assertTrue(list.add("first", 1));
        assertTrue(list.add("first", 2));
        assertTrue(list.add("first", 3));
        assertTrue(list.add("first", 4));
        list.add(1, new Pair("second", 100));
        assertTrue(list.get(1).equals(new Pair("second", 100)));
        assertTrue(list.size() == 5);
    }
    
    @Test
    public void method_getByFirst_shouldReturnCorrectList() {
        list.add(1, 1);
        list.add(1, 2);
        list.add(1, 3);
        list.add(1, 4);
        list.add(2, 5);
        list.add(2, 5);
        list.add(3, 7);
        list.add(3, 8);
        
        assertTrue(list.getByFirst(1).size() == 4);
        assertTrue(list.getByFirst(2).size() == 2);
        assertTrue(list.getByFirst(3).size() == 2);
    }
    
    @Test
    public void method_getBySecond_shouldReturnCorrectList() {
        list.add(1, 1);
        list.add(2, 1);
        list.add(3, 1);
        list.add(4, 1);
        list.add(5, 2);
        list.add(6, 2);
        list.add(7, 3);
        list.add(8, 3);
        
        assertTrue(list.getBySecond(1).size() == 4);
        assertTrue(list.getBySecond(2).size() == 2);
        assertTrue(list.getBySecond(3).size() == 2);
    }
    
    @Test
    public void method_getFirstElements_shouldReturnCorrectList() {
        list.add(1, 1);
        list.add(2, 1);
        list.add(3, 1);
        list.add(4, 1);
        list.add(5, 2);
        list.add(6, 2);
        list.add(7, 3);
        list.add(8, 3);
        
        ArrayList<Integer> l = new ArrayList() {{
            add(1); add(2); add(3); add(4); add(5); add(6); add(7); add(8);
        }};
        
        assertEquals(l, list.getFirstElements());
    }
    
    @Test
    public void method_getSecondElements_shouldReturnCorrectList() {
        list.add(1, 1);
        list.add(2, 1);
        list.add(3, 1);
        list.add(4, 1);
        list.add(5, 2);
        list.add(6, 2);
        list.add(7, 3);
        list.add(8, 3);
        
        ArrayList<Integer> l = new ArrayList() {{
            add(1); add(1); add(1); add(1); add(2); add(2); add(3); add(3);
        }};
        
        assertEquals(l, list.getSecondElements());
    }
    
    @Test
    public void method_get_shouldReturnCorrectElement() {
        Pair p1 = new Pair(1, 1);
        Pair p2 = new Pair(1, 1);
        Pair p3 = new Pair(1, 1);
        Pair p4 = new Pair(1, 1);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        
        assertEquals(p1, list.get(0));
        assertEquals(p2, list.get(1));
        assertEquals(p3, list.get(2));
        assertEquals(p4, list.get(3));
    }
    
    @Test
    public void method_size_shouldReturnCorrectSize() {
        for (int i = 0; i < 1000; i++) {
            list.add(new Pair(0, 0));
        }
        
        assertTrue(list.size() == 1000);
    }
    
    @Test
    public void method_contains_shouldReturnTrueWhenElementIsContained() {
        Pair p1 = new Pair(1, 1);
        list.add(p1);
        
        assertTrue(list.contains(p1));
    }
    
    @Test
    public void method_contains_shouldReturnFalseWhenElementIsNotContained() {
        Pair p1 = new Pair(1, 1);
        Pair p2 = new Pair(1, 2);
        list.add(p1);
        
        assertFalse(list.contains(p2));
    }
    
    @Test
    public void method_iterator_shouldReturnFunctionalIterator() {
        Pair p1 = new Pair(1, 1);
        Pair p2 = new Pair(1, 2);
        Pair p3 = new Pair(1, 3);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        
        int i = 0;
        for (Iterator it = list.iterator(); it.hasNext();) {
            Object o = it.next();
            assertEquals(list.get(i), o);
            i++;
        }
        
        assertTrue(i == list.size());
    }
    
    @Test
    public void method_toArray_shouldReturnCorrectArray() {
        Pair p1 = new Pair(1, 1);
        Pair p2 = new Pair(1, 2);
        Pair p3 = new Pair(1, 3);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        
        Pair[] oa = list.toArray();
        assertEquals(list.get(0), oa[0]);
        assertEquals(list.get(1), oa[1]);
        assertEquals(list.get(2), oa[2]);
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void method_toArray2_shouldBeUnsupported() {
        list.toArray(new Object[] {});
    }
    
    @Test
    public void method_remove1_shouldRemoveElements() {
        Pair p1 = new Pair(1, 1);
        Pair p2 = new Pair(1, 2);
        Pair p3 = new Pair(1, 3);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        assertEquals(p1, list.get(0));
        assertTrue(list.remove(0) == p1);
        assertEquals(p2, list.get(0));
        assertTrue(list.remove(0) == p2);
        assertEquals(p3, list.get(0));
        assertTrue(list.remove(0) == p3);
        assertTrue(list.isEmpty());
    }
    
    @Test
    public void method_remove2_shouldRemoveElements() {
        Pair p1 = new Pair(1, 1);
        Pair p2 = new Pair(1, 2);
        Pair p3 = new Pair(1, 3);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        assertEquals(p1, list.get(0));
        assertTrue(list.remove(p1));
        assertEquals(p2, list.get(0));
        assertTrue(list.remove(p2));
        assertEquals(p3, list.get(0));
        assertTrue(list.remove(p3));
        assertTrue(list.isEmpty());
    }
    
    @Test
    public void method_containsAll_shouldReturnTrueWhenAllElementsAreContained() {
        Pair p1 = new Pair(1, 1);
        Pair p2 = new Pair(1, 2);
        Pair p3 = new Pair(1, 3);
        
        List<Pair> l = new ArrayList();
        l.add(p1);
        l.add(p2);
        l.add(p3);
        
        list.add(p1);
        list.add(p2);
        list.add(p3);
        
        assertTrue(list.containsAll(l));
    }
    
    @Test
    public void method_containsAll_shouldReturnFalseWhenAllElementsAreNotContained() {
        Pair p1 = new Pair(1, 1);
        Pair p2 = new Pair(1, 2);
        Pair p3 = new Pair(1, 3);
        Pair p4 = new Pair(1, 4);
        
        List<Pair> l = new ArrayList();
        l.add(p1);
        l.add(p2);
        l.add(p3);
        l.add(p4);
        
        list.add(p1);
        list.add(p2);
        list.add(p3);
        
        assertFalse(list.containsAll(l));
    }
    
    @Test
    public void method_removeAll_shouldRemoveAllContainedElements() {
        Pair p1 = new Pair(1, 1);
        Pair p2 = new Pair(1, 2);
        Pair p3 = new Pair(1, 3);
        
        List<Pair> l = new ArrayList();
        l.add(p1);
        l.add(p2);
        l.add(p3);
        
        list.add(p1);
        list.add(p2);
        list.add(p3);
        
        assertTrue(list.removeAll(l));
        assertTrue(list.isEmpty());
    }
    
    @Test
    public void method_retainAll_shouldRetainAllContainedElements() {
        Pair p1 = new Pair(1, 1);
        Pair p2 = new Pair(1, 2);
        Pair p3 = new Pair(1, 3);
        Pair p4 = new Pair(1, 4);
        
        List<Pair> l = new ArrayList();
        l.add(p1);
        l.add(p2);
        l.add(p3);
        
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        
        assertTrue(list.retainAll(l));
        assertTrue(list.size() == 3);
        assertFalse(list.contains(p4));
    }
    
    @Test
    public void method_clear_shouldRemoveAllElements() {
        Pair p1 = new Pair(1, 1);
        Pair p2 = new Pair(1, 2);
        Pair p3 = new Pair(1, 3);
        
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.clear();
        assertTrue(list.isEmpty());
    }
    
    @Test
    public void method_set_shouldSetElementsAtCorrectPlaces() {
        Pair p1 = new Pair(1, 1);
        Pair p2 = new Pair(1, 2);
        Pair p3 = new Pair(1, 3);
        
        list.add(p1);
        list.add(p2);
        assertTrue(list.set(0, p3) == p1);
        assertTrue(list.get(0) == p3);
        assertTrue(list.set(1, p3) == p2);
        assertTrue(list.get(1) == p3);
        
        assertTrue(list.size() == 2);
    }
    
    @Test
    public void method_indexOf_shouldReturnFirstIndex() {
        Pair p1 = new Pair(1, 1);
        Pair p2 = new Pair(1, 2);
        Pair p3 = new Pair(1, 3);
        
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        assertTrue(list.indexOf(p1) == 0);
        assertTrue(list.indexOf(p2) == 1);
        assertTrue(list.indexOf(p3) == 2);
    }
    
    @Test
    public void method_lastIndexOf_shouldReturnLastIndex() {
        Pair p1 = new Pair(1, 1);
        Pair p2 = new Pair(1, 2);
        Pair p3 = new Pair(1, 3);
        
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        assertTrue(list.lastIndexOf(p1) == 3);
        assertTrue(list.lastIndexOf(p2) == 4);
        assertTrue(list.lastIndexOf(p3) == 5);
    }
    
    @Test
    public void method_addAll1_shouldAddAllElements() {
        List<Pair> l = new ArrayList<>();
        Pair p1 = new Pair(1, 1);
        Pair p2 = new Pair(1, 2);
        Pair p3 = new Pair(1, 3);        
        l.add(p1);
        l.add(p2);
        l.add(p3);
        
        list.add(new Pair(9, 9));
        assertTrue(list.addAll(l));
        
        assertTrue(list.get(1) == p1);
        assertTrue(list.get(2) == p2);
        assertTrue(list.get(3) == p3);
    }
    
    @Test
    public void method_addAll2_shouldAddAllElements() {
        List<Pair> l = new ArrayList<>();
        Pair p1 = new Pair(1, 1);
        Pair p2 = new Pair(1, 2);
        Pair p3 = new Pair(1, 3);        
        l.add(p1);
        l.add(p2);
        l.add(p3);
        
        list.add(new Pair(9, 9));
        assertTrue(list.addAll(0, l));
        
        assertTrue(list.get(0) == p1);
        assertTrue(list.get(1) == p2);
        assertTrue(list.get(2) == p3);
    }
    
    @Test
    public void method_listIterator1_shouldReturnFunctionalListIterator() {
        Pair p1 = new Pair(1, 1);
        Pair p2 = new Pair(1, 2);
        Pair p3 = new Pair(1, 3);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        
        int i = 0;
        ListIterator it = list.listIterator();
        Object o = null;
        for (; it.hasNext();) {
            o = it.next();
            assertEquals(list.get(i), o);
            i++;
        }
        
        assertTrue(i == list.size());
        
        for (; it.hasPrevious();) {
            i--;
            o = it.previous();
            assertEquals(list.get(i), o);
        }
        
        assertTrue(i == 0);
    }
    
    @Test
    public void method_listIterator2_shouldReturnFunctionalListIterator() {
        Pair p1 = new Pair(1, 1);
        Pair p2 = new Pair(1, 2);
        Pair p3 = new Pair(1, 3);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        
        int i = 1;
        ListIterator it = list.listIterator(i);
        Object o = null;
        for (; it.hasNext();) {
            o = it.next();
            assertEquals(list.get(i), o);
            i++;
        }
        
        assertTrue(i == list.size());
        
        for (; it.hasPrevious();) {
            i--;
            o = it.previous();
            assertEquals(list.get(i), o);
        }
        
        assertTrue(i == 0);
    }
    
    @Test
    public void method_subList_shouldReturnCorrectSubList() {
        Pair p1 = new Pair(1, 1);
        Pair p2 = new Pair(1, 2);
        Pair p3 = new Pair(1, 3);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        
        PairedList l = list.subList(1, 3);
        assertTrue(l.get(0) == p2);
        assertTrue(l.get(1) == p3);
        assertTrue(l.size() == 2);
    }
    
    @Test
    public void method_equals_shouldReturnTrueOnEqualElements() {
        Pair p1 = new Pair(1, 1);
        Pair p2 = new Pair(1, 2);
        Pair p3 = new Pair(1, 3);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        
        PairedList l = new PairedList<>();
        l.addAll(list);
        
        assertTrue(list.equals(l));
        assertTrue(l.equals(list));
        assertTrue(list.hashCode() == l.hashCode());
    }
    
    @Test
    public void method_equals_shouldReturnFalseOnNonEqualElements() {
        Pair p1 = new Pair(1, 1);
        Pair p2 = new Pair(1, 2);
        Pair p3 = new Pair(1, 3);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        
        PairedList l = new PairedList<>();
        l.addAll(list);
        l.add(new Pair(1, 4));
        
        assertFalse(list.equals(l));
        assertFalse(l.equals(list));
        assertFalse(list.hashCode() == l.hashCode());
    }
    
}
