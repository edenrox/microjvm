package com.hopkins.vm1;

import com.hopkins.vm1.constantpool.Constant;
import com.hopkins.vm1.constantpool.ConstantPool;
import java.util.Arrays;
import java.util.Collections;

public final class Code {
    static final ConstantPool EMPTY_CONSTANT_POOL = new ConstantPool(Collections.<Constant>emptyList());

    final int maxStack;
    final int maxLocals;
    final byte[] code;
    final ConstantPool constantPool;

    public Code(int maxStack, int maxLocals, byte[] code, ConstantPool constantPool) {
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.code = code;
        this.constantPool = constantPool;
    }

    public static class Builder {
        private int maxStack;
        private int maxLocals;
        private byte[] code;
        private int length;
        private ConstantPool constantPool = EMPTY_CONSTANT_POOL;

        public Builder() {
            code = new byte[4096];
            length = 0;
        }

        public Builder constantPool(ConstantPool constantPool) {
            this.constantPool = constantPool;
            return this;
        }

        public Builder maxLocals(int maxLocals) {
            this.maxLocals = maxLocals;
            return this;
        }

        public Builder maxStack(int maxStack) {
            this.maxStack = maxStack;
            return this;
        }

        public Builder append(OpCode opCode) {
            code[length++] = (byte) opCode.getValue();
            return this;
        }

        public Builder append(OpCode opCode, int arg0) {
            code[length++] = (byte) opCode.getValue();
            code[length++] = (byte) arg0;
            return this;
        }

        public Builder appendS(OpCode opCode, int argS0) {
            code[length++] = (byte) opCode.getValue();
            code[length++] = (byte) ((argS0 >> 8) & 0xff);
            code[length++] = (byte) (argS0 & 0xff);
            return this;
        }

        public Code build() {
            return new Code(maxStack, maxLocals, Arrays.copyOfRange(code, 0, length), constantPool);
        }
    }
    
}
