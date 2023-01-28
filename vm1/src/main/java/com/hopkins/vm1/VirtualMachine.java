package com.hopkins.vm1;

import java.util.Arrays;

public final class VirtualMachine {
    
    public int[] execute(Code code) {
        int[] stack = new int[4096];
        int[] locals = new int[255];

        byte[] buffer = code.code;
        boolean isDone = false;
        int pc = 0;
        int sp = 0;
        int tmp;

        System.out.println("Input code:");
        System.out.println(Arrays.toString(buffer));
        
        while (!isDone) {
            OpCode opCode = OpCode.find(buffer[pc++]);
            int arg0 = pc < buffer.length ? (0xff & buffer[pc]) : 0;
            int arg1 = pc+1 < buffer.length ? (0xff & buffer[pc + 1]) : 0;
            int argS0 = (short) (((0xff & arg0) << 8) + arg1);

            pc += opCode.argLength;
            int temp = 0;
            switch (opCode) {
                // Constants
                case ACONST_NULL:
                    stack[sp] = 0;
                    sp++;
                    break;
                case ICONST_M1:
                case ICONST_0:
                case ICONST_1:
                case ICONST_2:
                case ICONST_3:
                case ICONST_4:
                case ICONST_5:
                    stack[sp++] = opCode.getValue() - OpCode.ICONST_M1.getValue() - 1;
                    break;

                // Conversions
                case I2B:
                    stack[sp - 1] = (byte) stack[sp - 1];
                    break;
                case I2S:
                    stack[sp - 1] = (short) stack[sp - 1];
                    break;

                // Local Variables
                case ILOAD:
                    stack[sp++] = locals[arg0];
                    break;
                case ILOAD_0:
                case ILOAD_1:
                case ILOAD_2:
                case ILOAD_3:
                    temp = opCode.getValue() - OpCode.ILOAD_0.getValue();
                    stack[sp++] = locals[temp];
                    break;
                case ISTORE:
                    locals[arg0] = stack[--sp];
                    break;
                case ISTORE_0:
                case ISTORE_1:
                case ISTORE_2:
                case ISTORE_3:
                    temp = opCode.getValue() - OpCode.ISTORE_0.getValue();
                    locals[temp] = stack[--sp];
                    break;

                // Arithmatic
                case IADD:
                    temp = stack[sp - 2] + stack[sp - 1];
                    sp--;
                    stack[sp - 1] = temp;
                    break;
                case IDIV:
                    temp = stack[sp - 2] / stack[sp - 1];
                    sp--;
                    stack[sp - 1] = temp;
                    break;
                case IINC:
                    locals[arg0] += arg1;
                    break;
                case IMUL:
                    temp = stack[sp - 2] * stack[sp - 1];
                    sp--;
                    stack[sp - 1] = temp;
                    break;
                case ISUB:
                    temp = stack[sp - 2] - stack[sp - 1];
                    sp--;
                    stack[sp - 1] = temp;
                    break;
                case INEG:
                    stack[sp - 1] = -stack[sp - 1];
                    break;
                case IREM:
                    temp = stack[sp - 2] % stack[sp - 1];
                    sp--;
                    stack[sp - 1] = temp;
                    break;

                // Logic
                case IAND:
                    temp = stack[sp - 1] & stack[sp - 2];
                    sp--;
                    stack[sp-1] = temp;
                    break;
                case ISHL:
                    temp = stack[sp - 1] << (0x1f & stack[sp - 2]);
                    sp--;
                    stack[sp - 1] = temp;
                    break;
                case ISHR:
                    temp = stack[sp - 1] >> (0x1f & stack[sp - 2]);
                    sp--;
                    stack[sp - 1] = temp;
                    break;
                case IUSHR:
                    temp = stack[sp - 1] >>> (0x1f & stack[sp - 2]);
                    sp--;
                    stack[sp - 1] = temp;
                    break;
                case IOR:
                    temp = stack[sp - 1] | stack[sp - 2];
                    sp--;
                    stack[sp - 1] = temp;
                    break;
                case IXOR:
                    temp = stack[sp - 1] ^ stack[sp - 2];
                    sp--;
                    stack[sp - 1] = temp;
                    break;

                // Branching
                case GOTO:
                    pc += argS0 - 2;
                    break;
                case IF_ACMPEQ:
                    if (stack[sp - 2] == stack[sp - 1]) {
                        pc += argS0 - 2;
                    }
                    sp -= 2;
                    break;
                case IF_ACMPNE:
                    if (stack[sp - 2] != stack[sp - 1]) {
                        pc += argS0 - 2;
                    }
                    sp -= 2;
                    break;
                
                // Stack Manipulation
                case BI_PUSH:
                    stack[sp++] = 0xff & arg0;
                    break;
                case DUP:
                    stack[sp] = stack[sp - 1];
                    sp++;
                    break;
                case DUP_X1:
                    stack[sp] = stack[sp - 2];
                    sp++;
                    break;
                case DUP_X2:
                    stack[sp] = stack[sp - 3];
                    sp++;
                    break;
                case DUP2:
                    stack[sp + 1] = stack[sp - 1];
                    stack[sp] = stack[sp - 2];
                    sp += 2;
                    break;
                case DUP2_X1:
                    stack[sp + 1] = stack[sp - 2];
                    stack[sp] = stack[sp - 3];
                    sp += 2;
                    break;
                case DUP2_X2:
                    stack[sp + 1] = stack[sp - 3];
                    stack[sp] = stack[sp - 4];
                    sp += 2;
                    break;
                case POP:
                    sp--;
                    break;
                case POP2:
                    sp -= 2;
                    break;
                case SI_PUSH:
                    stack[sp++] = argS0;
                    break;
                case SWAP:
                    temp = stack[sp - 1];
                    stack[sp - 1] = stack[sp - 2];
                    stack[sp - 2] = temp;
                    break;

                // Other
                case NOP:
                    break;

                // Implementation dependent
                case PRINT_INT:
                    System.out.println(stack[--sp]);
                    break;
                case HALT:
                    return Arrays.copyOfRange(stack, 0, sp);
            }
        }
        throw new AssertionError("should hit this");
    }
}