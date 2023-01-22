package alt.java.lang;

import static alt.java.lang.String2Util.fromString2;
import static alt.java.lang.String2Util.toString2;
import static com.google.common.truth.Truth.assertThat;

import java.nio.charset.StandardCharsets;
import org.junit.Test;

public class String2Test {

    @Test
    public void testEmpty() {
        String2 empty = String2.EMPTY;

        assertThat(empty.isEmpty()).isTrue();
        assertThat(empty.length()).isEqualTo(0);
    }

    @Test
    public void concat() {
        String2 bananaString = toString2("ba");
        assertThat(fromString2(bananaString.concat(toString2("nana")))).isEqualTo("banana");
        assertThat(fromString2(bananaString.concat(toString2("")))).isEqualTo("ba");
        assertThat(fromString2(bananaString.concat(toString2("d")))).isEqualTo("bad");
    }

    @Test
    public void hashCode_returnsSameAsString() {
        assertThat(String2.EMPTY.hashCode()).isEqualTo(0);
        assertThat(toString2("test").hashCode()).isEqualTo("test".hashCode());
    }

    @Test
    public void valueOf_withBooleans_returnsExpectedString() {
        String2 trueString = String2.valueOf(true);
        assertThat(trueString.length()).isEqualTo(4);
        assertThat(fromString2(trueString)).isEqualTo("true");

        String2 falseString = String2.valueOf(false);
        assertThat(falseString.length()).isEqualTo(5);
        assertThat(fromString2(falseString)).isEqualTo("false");
    }

    @Test
    public void startsWith() {
        String2 bananaString = toString2("banana");
        assertThat(bananaString.startsWith(toString2("b"))).isTrue();
        assertThat(bananaString.startsWith(toString2("c"))).isFalse();
        assertThat(bananaString.startsWith(toString2("ba"))).isTrue();
        assertThat(bananaString.startsWith(toString2("ban"))).isTrue();
        assertThat(bananaString.startsWith(toString2("banana"))).isTrue();
        assertThat(bananaString.startsWith(toString2("bananaa"))).isFalse();
    }

    @Test
    public void substring() {
        String2 bananaString = toString2("banana");
        assertThat(fromString2(bananaString.substring(0))).isEqualTo("banana");
        assertThat(fromString2(bananaString.substring(2))).isEqualTo("nana");
        assertThat(fromString2(bananaString.substring(3, 4))).isEqualTo("a");
        assertThat(fromString2(bananaString.substring(0, 1))).isEqualTo("b");
    }

    @Test
    public void endsWith() {
        String2 bananaString = toString2("banana");
        assertThat(bananaString.endsWith(toString2("a"))).isTrue();
        assertThat(bananaString.endsWith(toString2("c"))).isFalse();
        assertThat(bananaString.endsWith(toString2("na"))).isTrue();
        assertThat(bananaString.endsWith(toString2("ana"))).isTrue();
        assertThat(bananaString.endsWith(toString2("nana"))).isTrue();
        assertThat(bananaString.endsWith(toString2("banana"))).isTrue();
        assertThat(bananaString.endsWith(toString2("abanana"))).isFalse();
    }
}
