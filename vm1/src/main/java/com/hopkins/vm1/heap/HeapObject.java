package com.hopkins.vm1.heap;

public class HeapObject {
    private static final int[] EMPTY_FIELDS = new int[0];

    private final int id;
    private final int[] fields;

    public HeapObject(int id, int numFields) {
        this.id = id;
        this.fields = numFields == 0 ? EMPTY_FIELDS : new int[numFields];
    }

    public int getId() {
        return id;
    }

    public int getField(int index) {
        return fields[index];
    }

    public void setField(int index, int value) {
        fields[index] = value;
    }
}