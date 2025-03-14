package org.example;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashTable {
    private final int size; // number of buckets
    private final ArrayList<LinkedList<Integer>> table;

    public HashTable(int size) {
        this.size = size;
        table = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            table.add(new LinkedList<>());
        }
    }

    private int hash(int key) {
        return Math.abs(key) % size;
    }

    public void insert(int key) {
        int index = hash(key);
        table.get(index).add(key);
    }

    public boolean search(int key) {
        return table.get(hash(key)).contains(key);
    }

    public boolean delete(int key) {
        return table.get(hash(key)).remove((Integer) key);
    }

    // used for white box testing
    public ArrayList<LinkedList<Integer>> getTable() {
        return table;
    }
}
