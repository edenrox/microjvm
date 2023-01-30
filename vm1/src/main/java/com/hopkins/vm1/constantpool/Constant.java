package com.hopkins.vm1.constantpool;

import java.util.Objects;

public final class Constant {
    public enum Type {
        INTEGER,
        STRING
    }

    private final Type type;
    private final Object value;

    private Constant(Type type, Object value) {
        this.type = type;
        this.value = Objects.requireNonNull(value);
    }

    public Type getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public static Constant of(String value) {
        return new Constant(Type.STRING, value);
    }

    public static Constant of(int value) {
        return new Constant(Type.INTEGER, value);
    }
}
