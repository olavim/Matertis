package com.github.tilastokeskus.matertis.util;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
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
public class PairTest {
    
    public PairTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void constructor_shouldSetFirst() {
        Pair p = new Pair("first", null);
        assertEquals("first", p.first);
    }

    @Test
    public void constructor_shouldSetSecond() {
        Pair p = new Pair(null, "second");
        assertEquals("second", p.second);
    }

    @Test
    public void method_toString_shouldGiveCorrectStringRepresentation() {
        Pair p = new Pair("first", "second");
        assertEquals("1: first, 2: second", p.toString());
    }

    @Test
    public void method_equals_shouldReturnTrueOnEqualElements() {
        Pair p1 = new Pair("first", "second");
        Pair p2 = new Pair(new Point(1, 2), "second");
        Pair p3 = new Pair("dilemma", new ArrayList<Integer>());
        
        Pair p1t = new Pair("first", "second");
        Pair p2t = new Pair(new Point(1, 2), "second");
        Pair p3t = new Pair("dilemma", new ArrayList<Integer>());
        
        assertTrue(p1t.equals(p1) && p1.equals(p1t));
        assertTrue(p2t.equals(p2) && p2.equals(p2t));
        assertTrue(p3t.equals(p3) && p3.equals(p3t));
        assertTrue(p1.hashCode() == p1t.hashCode());
        assertTrue(p2.hashCode() == p2t.hashCode());
        assertTrue(p3.hashCode() == p3t.hashCode());
    }

    @Test
    public void method_equals_and_hashCode_shouldReturnFalseOnNonEqualElements() {
        Pair p1 = new Pair("first", "second");
        Pair p2 = new Pair(new Point(1, 2), "second");
        
        Pair p1f = new Pair("third", "second");
        Pair p2f = new Pair(new Point(1, 2), "third");
        
        assertFalse(p1.equals(p1f) || p1f.equals(p1));
        assertFalse(p2.equals(p2f) || p2f.equals(p2));
        assertFalse(p1.hashCode() == p1f.hashCode());
        assertFalse(p2.hashCode() == p2f.hashCode());
    }

    @Test
    public void method_equals_and_hashCode_shouldReturnFalseOnNullElements() {
        Pair p1 = new Pair("first", "second");
        Pair p2 = new Pair(new Point(1, 2), "second");
        Pair p3 = new Pair("dilemma", new ArrayList<Integer>());
        
        assertFalse(new Pair("first", "second").equals(null));
        assertFalse(new Pair(new Point(1, 2), "second").equals(null));
        assertFalse(new Pair("dilemma", new ArrayList<Integer>()).equals(null));
    }
}
