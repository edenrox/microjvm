package com.hopkins.vm1.heap;

public final class HeapByteArray extends HeapObject implements HeapArray {
    private final byte[] data;

    public HeapByteArray(int id, int length) {
        super(id, 0);
        this.data = new byte[length];
    }

    @Override
    public int length() {
        return data.length;
    }

    public byte get(int index) {
        return data[index];
    }

    public void set(int index, byte value) {
        data[index] = value;
    }
}