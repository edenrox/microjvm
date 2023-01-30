package com.hopkins.vm1.heap;

public final class HeapIntArray extends HeapObject implements HeapArray {
    private final int[] data;

    public HeapIntArray(int id, int length) {
        super(id, 0);
        this.data = new int[length];
    }

    @Override
    public int length() {
        return data.length;
    }

    public int get(int index) {
        return data[index];
    }

    public void set(int index, int value) {
        data[index] = value;
    }
}