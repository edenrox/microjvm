package com.hopkins.vm1;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import com.hopkins.vm1.constantpool.Constant;
import com.hopkins.vm1.constantpool.ConstantPool;
import com.hopkins.vm1.OpCode;
import com.hopkins.vm1.VirtualMachine;
import org.junit.Test;

public class VirtualMachineTest {
    @Test
    public void iconst() {
        Code code = 
            new Code.Builder()
                .append(OpCode.ICONST_0)
                .append(OpCode.ICONST_1)
                .append(OpCode.ICONST_2)
                .append(OpCode.HALT)
                .build();
        VirtualMachine vm = new VirtualMachine();
        int[] result = vm.execute(code);

        assertThat(result).asList().containsExactly(0, 1, 2).inOrder();
    }

    @Test
    public void iadd_afterIconst() {
        Code code = 
            new Code.Builder()
                .append(OpCode.ICONST_1)
                .append(OpCode.ICONST_2)
                .append(OpCode.IADD)
                .append(OpCode.HALT)
                .build();
        VirtualMachine vm = new VirtualMachine();
        int[] result = vm.execute(code);

        assertThat(result).asList().containsExactly(3);
    }

    @Test
    public void isub() {
        Code code = 
            new Code.Builder()
                .append(OpCode.BI_PUSH, 15)
                .append(OpCode.BI_PUSH, 20)
                .append(OpCode.ISUB)
                .append(OpCode.HALT)
                .build();
        VirtualMachine vm = new VirtualMachine();
        int[] result = vm.execute(code);

        assertThat(result).asList().containsExactly(-5);
    }

    @Test
    public void idiv() {
        Code code = 
            new Code.Builder()
                .append(OpCode.BI_PUSH, 15)
                .append(OpCode.BI_PUSH, 3)
                .append(OpCode.IDIV)
                .append(OpCode.HALT)
                .build();
        VirtualMachine vm = new VirtualMachine();
        int[] result = vm.execute(code);

        assertThat(result).asList().containsExactly(5);
    }

    @Test
    public void iadd_afterBipush() {
        Code code = 
            new Code.Builder()
                .append(OpCode.BI_PUSH, 15)
                .append(OpCode.ICONST_2)
                .append(OpCode.IADD)
                .append(OpCode.HALT)
                .build();
        VirtualMachine vm = new VirtualMachine();
        int[] result = vm.execute(code);

        assertThat(result).asList().containsExactly(17);
    }

    @Test
    public void ineg() {
        Code code = 
            new Code.Builder()
                .append(OpCode.BI_PUSH, 21)
                .append(OpCode.INEG)
                .append(OpCode.HALT)
                .build();
        VirtualMachine vm = new VirtualMachine();
        int[] result = vm.execute(code);

        assertThat(result).asList().containsExactly(-21);
    }

    @Test
    public void irem() {
        Code code = 
            new Code.Builder()
                .append(OpCode.BI_PUSH, 33)
                .append(OpCode.BI_PUSH, 10)
                .append(OpCode.IREM)
                .append(OpCode.HALT)
                .build();
        VirtualMachine vm = new VirtualMachine();
        int[] result = vm.execute(code);

        assertThat(result).asList().containsExactly(3).inOrder();
    }

    @Test
    public void pop() {
        Code code = 
            new Code.Builder()
                .append(OpCode.BI_PUSH, 31)
                .append(OpCode.BI_PUSH, 32)
                .append(OpCode.BI_PUSH, 33)
                .append(OpCode.POP)
                .append(OpCode.HALT)
                .build();
        VirtualMachine vm = new VirtualMachine();
        int[] result = vm.execute(code);

        assertThat(result).asList().containsExactly(31, 32).inOrder();
    }

    @Test
    public void pop2() {
        Code code = 
            new Code.Builder()
                .append(OpCode.ICONST_1)
                .append(OpCode.ICONST_2)
                .append(OpCode.ICONST_3)
                .append(OpCode.ICONST_4)
                .append(OpCode.POP2)
                .append(OpCode.HALT)
                .build();
        VirtualMachine vm = new VirtualMachine();
        int[] result = vm.execute(code);

        assertThat(result).asList().containsExactly(1, 2).inOrder();
    }

    @Test
    public void dup() {
        Code code = 
            new Code.Builder()
                .append(OpCode.BI_PUSH, 21)
                .append(OpCode.DUP)
                .append(OpCode.DUP)
                .append(OpCode.HALT)
                .build();
        VirtualMachine vm = new VirtualMachine();
        int[] result = vm.execute(code);

        assertThat(result).asList().containsExactly(21, 21, 21);
    }

    @Test
    public void swap() {
        Code code = 
            new Code.Builder()
                .append(OpCode.BI_PUSH, 15)
                .append(OpCode.BI_PUSH, 31)
                .append(OpCode.SWAP)
                .append(OpCode.HALT)
                .build();
        VirtualMachine vm = new VirtualMachine();
        int[] result = vm.execute(code);

        assertThat(result).asList().containsExactly(31, 15).inOrder();
    }

    @Test
    public void imul() {
        Code code = 
            new Code.Builder()
                .append(OpCode.ICONST_4)
                .append(OpCode.BI_PUSH, 25)
                .append(OpCode.IMUL)
                .append(OpCode.HALT)
                .build();
        VirtualMachine vm = new VirtualMachine();
        int[] result = vm.execute(code);

        assertThat(result).asList().containsExactly(100);
    }

    @Test
    public void testGoto() {
        Code code = 
            new Code.Builder()
                .appendS(OpCode.GOTO, 3)
                .append(OpCode.NOP)
                .append(OpCode.NOP)
                .append(OpCode.NOP)
                .append(OpCode.ICONST_1)
                .append(OpCode.ICONST_2)
                .append(OpCode.HALT)
                .build();
        VirtualMachine vm = new VirtualMachine();
        int[] result = vm.execute(code);

        assertThat(result).asList().containsExactly(1, 2).inOrder();
    }

    @Test
    public void istore() {
        Code code = 
            new Code.Builder()
                .append(OpCode.BI_PUSH, 14)
                .append(OpCode.ISTORE_0)
                .append(OpCode.ICONST_1)
                .append(OpCode.ISTORE_1)
                .append(OpCode.ICONST_2)
                .append(OpCode.ISTORE_2)
                .append(OpCode.ILOAD_2)
                .append(OpCode.ILOAD_1)
                .append(OpCode.ILOAD_0)
                .append(OpCode.HALT)
                .build();
                VirtualMachine vm = new VirtualMachine();
        int[] result = vm.execute(code);

        assertThat(result).asList().containsExactly(2, 1, 14).inOrder();
    }

    @Test
    public void ldc() {

        ConstantPool constantPool =
            new ConstantPool(Arrays.asList(Constant.of(10000), Constant.of(-21786), Constant.of(40)));
        Code code = 
            new Code.Builder()
                .constantPool(constantPool)
                .append(OpCode.LDC, 0)
                .append(OpCode.LDC, 1)
                .appendS(OpCode.LDC_W, 2)
                .append(OpCode.HALT)
                .build();
                VirtualMachine vm = new VirtualMachine();
        int[] result = vm.execute(code);

        assertThat(result).asList().containsExactly(10000, -21786, 40).inOrder();
    }

    @Test
    public void newarray() {
        Code code = 
            new Code.Builder()
                .append(OpCode.BI_PUSH, 10)
                .append(OpCode.NEWARRAY, 6)
                .append(OpCode.BI_PUSH, 4)
                .append(OpCode.NEWARRAY, 10)
                .append(OpCode.HALT)
                .build();
                VirtualMachine vm = new VirtualMachine();
        int[] result = vm.execute(code);

        assertThat(result).asList().containsExactly(1, 2).inOrder();
    }

    @Test
    public void arraylength() {
        Code code = 
            new Code.Builder()
                .append(OpCode.BI_PUSH, 15)
                .append(OpCode.NEWARRAY, 6)
                .append(OpCode.ARRAYLENGTH)
                .append(OpCode.HALT)
                .build();
                VirtualMachine vm = new VirtualMachine();
        int[] result = vm.execute(code);

        assertThat(result).asList().containsExactly(15).inOrder();
    }

    @Test
    public void iastore() {
        Code code = 
            new Code.Builder()
                .append(OpCode.ICONST_5)
                .append(OpCode.NEWARRAY, 10)
                .append(OpCode.BI_PUSH, 11)
                
                .append(OpCode.ICONST_2)
                .append(OpCode.DUP_X2)
                
                .append(OpCode.IASTORE)
                
                .append(OpCode.ICONST_2)
                .append(OpCode.DUP_X1)
                .append(OpCode.IALOAD) /**/
                .append(OpCode.HALT)
                .build();
                VirtualMachine vm = new VirtualMachine();
        int[] result = vm.execute(code);

        assertThat(result).asList().containsExactly(1, 11).inOrder();
    }

}
