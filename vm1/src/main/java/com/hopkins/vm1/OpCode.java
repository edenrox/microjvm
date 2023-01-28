package com.hopkins.vm1;

public enum OpCode {
    // Constants
    ACONST_NULL(0x01),
    ICONST_M1(0x02),
    ICONST_0(0x03),
    ICONST_1(0x04),
    ICONST_2(0x05),
    ICONST_3(0x06),
    ICONST_4(0x07),
    ICONST_5(0x08),

    // Conversions
    I2B(0x91),
    I2S(0x93),

    // Local Variables
    ILOAD(0x15, 1),
    ILOAD_0(0x1a),
    ILOAD_1(0x1b),
    ILOAD_2(0x1c),
    ILOAD_3(0x1d),
    ISTORE(0x36, 1),
    ISTORE_0(0x3b),
    ISTORE_1(0x3c),
    ISTORE_2(0x3d),
    ISTORE_3(0x3e),

    // Arithmatic
    IADD(0x60),
    IDIV(0x6c),
    IINC(0x84, 2),
    IMUL(0x68),
    INEG(0x74),
    IREM(0x70),
    ISUB(0x64),

    // Logic
    IAND(0x7e),
    ISHL(0x78),
    ISHR(0x7a),
    IUSHR(0x7c),
    IOR(0x80),
    IXOR(0x82),

    // Branching
    GOTO(0xa7, 2),
    IF_ACMPEQ(0xa5, 2),
    IF_ACMPNE(0xa6, 2),
    IFEQ(0x99, 2),
    IFNE(0x9a, 2),
    IFNULL(0xc6, 2),
    IFNONNULL(0xc7, 2),

    // Stack Manipulation
    BI_PUSH(0x10, 1),
    DUP(0x59),
    DUP_X1(0x5a),
    DUP_X2(0x5b),
    DUP2(0x5c),
    DUP2_X1(0x5d),
    DUP2_X2(0x5e),
    POP(0x57),
    POP2(0x58),
    SI_PUSH(0x11),
    SWAP(0x5f),

    // Other
    NOP(0x00),

    // Implementation dependent
    PRINT_INT(0xfe),
    HALT(0xff),
    ;

    private static final OpCode[] lookup = new OpCode[256];

    static {
        for (OpCode code : values()) {
            lookup[code.value] = code;
        }
    }

    public static OpCode find(byte value) {
        return find(0xff & ((int) value));
    }

    public static OpCode find(int value) {
        return lookup[value];
    }

    private final int value;
    final int argLength;

    OpCode(int value) {
        this.value = value;
        this.argLength = 0;
    }

    OpCode(int value, int argLength) {
        this.value = value;
        this.argLength = argLength;
    }

    public int getValue() {
        return value;
    }
}
