package alt.java.lang;

import static alt.java.lang.String2Util.fromString2;
import static alt.java.lang.String2Util.toString2;
import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

public class Integer2Test {

    @Test
    public void valueOf_returnsCorrectIntValue() {
        assertThat(Integer2.valueOf(12).intValue()).isEqualTo(12);
        assertThat(Integer2.valueOf(0).intValue()).isEqualTo(0);
        assertThat(Integer2.valueOf(-127).intValue()).isEqualTo(-127);
        assertThat(Integer2.valueOf(5000).intValue()).isEqualTo(5000);
    }
    
    @Test
    public void valueOf_returnsCachedValues() {
        assertThat(Integer2.valueOf(12)).isSameAs(Integer2.valueOf(12));
        assertThat(Integer2.valueOf(0)).isSameAs(Integer2.valueOf(0));
        assertThat(Integer2.valueOf(500)).isNotSameAs(Integer2.valueOf(500));
    }

    @Test
    public void toString_returnsExpected() {
        assertThat(fromString2(Integer2.valueOf(0).toString2())).isEqualTo("0");
        assertThat(fromString2(Integer2.valueOf(12).toString2())).isEqualTo("12");
        assertThat(fromString2(Integer2.valueOf(-5).toString2())).isEqualTo("-5");
        assertThat(fromString2(Integer2.valueOf(10000).toString2())).isEqualTo("10000");
        assertThat(fromString2(Integer2.toBinaryString(3))).isEqualTo("11");
        assertThat(fromString2(Integer2.toBinaryString(5))).isEqualTo("101");
    }
}
