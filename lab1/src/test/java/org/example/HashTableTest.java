package org.example;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class HashTableTest {
    private HashTable table;
    private final static int SIZE = 10;

    @Before
    public void setUp() {
        table = new HashTable(SIZE);
    }

    @Test
    public void testInsert() {
        Integer firstNumber = 1;
        Integer secondNumber = SIZE + firstNumber; // hash function will be the same
        int hash = firstNumber % SIZE;

        // insert with collision
        table.insert(firstNumber);
        table.insert(secondNumber);
        assertTrue(table.search(firstNumber));
        assertTrue(table.search(secondNumber));

        // check full state
        var state = table.getTable();
        assertEquals(2, state.get(hash).size());
        assertEquals(firstNumber, state.get(hash).get(0));
        assertEquals(secondNumber, state.get(hash).get(1));
    }

    @Test
    public void testDelete() {
        int firstNumber = 7;

        // insert
        table.insert(firstNumber);
        assertTrue(table.search(firstNumber));

        // delete
        table.delete(firstNumber);
        assertFalse(table.search(firstNumber));
        assertEquals(0, table.getTable().get(firstNumber).size());
    }

    @Test
    public void testDeleteNonExistentElement() {
        table.insert(3);

        assertFalse(table.delete(10));
        assertFalse(table.search(10));
        assertTrue(table.search(3));
    }

    @Test
    public void testInsertDuplicate() {
        table.insert(8);
        table.insert(8);

        var state = table.getTable();
        assertEquals(2, state.get(8).size());
    }

    @Test
    public void testLargeNumberOfElements() {
        for (int i = 0; i < 1000; i++) {
            table.insert(i);
        }

        for (int i = 0; i < 1000; i++) {
            assertTrue(table.search(i));
        }

        for (int i = 0; i < 1000; i++) {
            table.delete(i);
        }

        for (int i = 0; i < 1000; i++) {
            assertFalse(table.search(i));
        }
    }
}
