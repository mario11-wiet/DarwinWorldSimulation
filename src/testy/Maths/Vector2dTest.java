
package Maths;

import org.junit.Test;

import static org.junit.Assert.*;

public class Vector2dTest {

    @Test
    public void testEquals(){
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v3 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(3, 1);
        assertTrue(v1.equals(v3));
        assertFalse(v1.equals(v2));
    }
    @Test
    public void toStringTesty(){
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v3 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(3, 1);
        assertEquals(v1.toString(),"(1,2)");
        assertEquals(v2.toString(),"(3,1)");
    }
    @Test
    public void precedesTesty(){
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v3 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(3, 2);
        assertTrue(v1.precedes(v3));
        assertTrue(v1.precedes(v2));
    }
    @Test
    public void followsTesty(){
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v3 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(3, 2);
        assertTrue(v2.follows(v2));
        assertFalse(v1.follows(v2));
    }
    @Test
    public void upperRightTesty(){
        Vector2d v1 = new Vector2d(1, 4);
        Vector2d v3 = new Vector2d(3, 4);
        Vector2d v2 = new Vector2d(3, 2);
        assertEquals(v1.upperRight(v2),v3);
    }
    @Test
    public void lowerLeftTesty(){
        Vector2d v1 = new Vector2d(1, 4);
        Vector2d v3 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(3, 2);
        assertEquals(v1.lowerLeft(v2),v3);
    }
    @Test
    public void addTesty(){
        Vector2d v1 = new Vector2d(1, 4);
        Vector2d v3 = new Vector2d(4, 6);
        Vector2d v2 = new Vector2d(3, 2);
        assertEquals(v1.add(v2),v3);
    }
    @Test
    public void subtractTesty(){
        Vector2d v1 = new Vector2d(1, 4);
        Vector2d v3 = new Vector2d(-2, 2);
        Vector2d v2 = new Vector2d(3, 2);
        assertEquals(v1.subtract(v2),v3);
    }
    @Test
    public void oppositeTesty(){
        Vector2d v1 = new Vector2d(1, 4);
        assertEquals(v1.opposite(),new Vector2d(-1,-4));
    }

}
