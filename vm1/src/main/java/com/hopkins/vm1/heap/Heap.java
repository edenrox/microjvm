package com.hopkins.vm1.heap;

import java.util.ArrayList;
import java.util.List;

public final class Heap {
    private final List<HeapObject> objectList = new ArrayList<>();

    public Heap() {
        // ID = 0 (resvered for NPE)
        objectList.add(null);
    }

    public HeapObject newObject() {
        int id = objectList.size();
        HeapObject result = new HeapObject(id, 4);
        objectList.add(result);
        return result;
    }

    public HeapByteArray newByteArray(int length) {
        int id = objectList.size();
        HeapByteArray result = new HeapByteArray(id, length);
        objectList.add(result);
        return result;
    }

    public HeapIntArray newIntArray(int length) {
        int id = objectList.size();
        HeapIntArray result = new HeapIntArray(id, length);
        objectList.add(result);
        return result;
    }

    public HeapObject get(int index) {
        return objectList.get(index);
    }
}