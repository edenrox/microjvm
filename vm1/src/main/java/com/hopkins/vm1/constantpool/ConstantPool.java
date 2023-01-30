package com.hopkins.vm1.constantpool;

import java.util.Collections;
import java.util.List;

public final class ConstantPool {
    private final List<Constant> constantList;
    
    public ConstantPool(List<Constant> constantList) {
        this.constantList = Collections.unmodifiableList(constantList);
    }

    public int size() {
        return constantList.size();
    }

    public Constant get(int index) {
        return constantList.get(index);
    }
}
