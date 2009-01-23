package at.yomi.functor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import at.yomi.functor.f.Functor;

public class FunctorTest extends BaseTest {

    @Test
    public void testAddConst() {
        final Integer c = 2;
        final Integer k = new Functor<Integer, Integer>() {
            @Override
            public Integer apply(final Integer a) {
                return a + c;
            }
        }.apply(2);
        assertEquals(k, Integer.valueOf(4));
    }

    @Test
    public void testConsString() {
        final String prefix = "PRE";
        final String result = new Functor<String, String>() {
            @Override
            public String apply(String a) {
                return prefix + a;
            }
        }.apply("POST");
        assertEquals("PREPOST", result);
    }
}