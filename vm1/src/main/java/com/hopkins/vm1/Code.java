package com.hopkins.vm1;

import java.util.Arrays;

public final class Code {
    final int maxStack;
    final int maxLocals;
    final byte[] code;

    public Code(int maxStack, int maxLocals, byte[] code) {
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.code = code;
    }

    public static class Builder {
        private int maxStack;
        private int maxLocals;
        private byte[] code;
        private int length;

        public Builder() {
            code = new byte[4096];
            length = 0;
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
            return new Code(maxStack, maxLocals, Arrays.copyOfRange(code, 0, length));
        }
    }
    
}
