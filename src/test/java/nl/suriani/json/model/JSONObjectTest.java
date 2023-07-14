package nl.suriani.json.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class JSONObjectTest {
    @Test
    void emptyObject() {
        var jsonObject = new JSONObject(new JSONProperty[0]);
        var something = jsonObject.deserialise(Something.class);
        assertNotNull(something);
    }

    @Test
    void onePropertyWithAString() {
        var jsonObject = new JSONObject(new JSONProperty[] {
                new JSONProperty("aString", Optional.of(new StringValue("aValue")))
        });

        var something = jsonObject.deserialise(Something.class);
        assertEquals("aValue", something.getaString());
    }

    @Test
    void onePropertyWithAnInt() {
        var jsonObject = new JSONObject(new JSONProperty[] {
                new JSONProperty("anInt", Optional.of(new IntegerValue(BigInteger.valueOf(1))))
        });
        var something = jsonObject.deserialise(Something.class);
        assertEquals(1, something.getAnInt());
    }

    @Test
    void onePropertyWithAnInteger() {
        var jsonObject = new JSONObject(new JSONProperty[] {
                new JSONProperty("anInteger", Optional.of(new IntegerValue(BigInteger.valueOf(1))))
        });
        var something = jsonObject.deserialise(Something.class);
        assertEquals(1, something.getAnInteger());
    }

    @Test
    void onePropertyWithALong() {
        var jsonObject = new JSONObject(new JSONProperty[] {
                new JSONProperty("aLong", Optional.of(new IntegerValue(BigInteger.valueOf(1L))))
        });
        var something = jsonObject.deserialise(Something.class);
        assertEquals(1, something.getaLong());
    }

    @Test
    void onePropertyWithALongBigL() {
        var jsonObject = new JSONObject(new JSONProperty[] {
                new JSONProperty("aLongBigL", Optional.of(new IntegerValue(BigInteger.valueOf(1L))))
        });
        var something = jsonObject.deserialise(Something.class);
        assertEquals(1, something.getaLongBigL());
    }

    @Test
    void onePropertyWithABigInteger() {
        var jsonObject = new JSONObject(new JSONProperty[] {
                new JSONProperty("aBigInteger", Optional.of(new IntegerValue(BigInteger.valueOf(1L))))
        });
        var something = jsonObject.deserialise(Something.class);
        assertEquals(0, BigInteger.valueOf(1).compareTo(something.getaBigInteger()));
    }

    @Test
    void onePropertyWithAFloat() {
        var jsonObject = new JSONObject(new JSONProperty[] {
                new JSONProperty("aFloat", Optional.of(new DecimalValue(BigDecimal.valueOf(1.01f))))
        });
        var something = jsonObject.deserialise(Something.class);
        assertEquals(1.01f, something.getaFloat());
    }

    @Test
    void onePropertyWithAFloatBigF() {
        var jsonObject = new JSONObject(new JSONProperty[] {
                new JSONProperty("aFloatBigF", Optional.of(new DecimalValue(BigDecimal.valueOf(1.01f))))
        });
        var something = jsonObject.deserialise(Something.class);
        assertEquals(1.01f, something.getaFloatBigF());
    }

    @Test
    void onePropertyWithADouble() {
        var jsonObject = new JSONObject(new JSONProperty[] {
                new JSONProperty("aDouble", Optional.of(new DecimalValue(BigDecimal.valueOf(1.01d))))
        });
        var something = jsonObject.deserialise(Something.class);
        assertEquals(1.01d, something.getaDouble());
    }

    @Test
    void onePropertyWithADoubleBigD() {
        var jsonObject = new JSONObject(new JSONProperty[] {
                new JSONProperty("aDoubleBigD", Optional.of(new DecimalValue(BigDecimal.valueOf(1.01d))))
        });
        var something = jsonObject.deserialise(Something.class);
        assertEquals(1.01d, something.getaDoubleBigD());
    }

    @Test
    void onePropertyWithABigDecimal() {
        var jsonObject = new JSONObject(new JSONProperty[] {
                new JSONProperty("aBigDecimal", Optional.of(new DecimalValue(BigDecimal.valueOf(1L))))
        });
        var something = jsonObject.deserialise(Something.class);
        assertEquals(0, BigDecimal.valueOf(1).compareTo(something.getaBigDecimal()));
    }

    @Test
    void onePropertyWithAnObjectWithAString() {
        var jsonObject = new JSONObject(new JSONProperty[] {
                new JSONProperty("something",
                        Optional.of(new JSONObject(new JSONProperty[] {
                                new JSONProperty("aString", Optional.of(new StringValue("aValue")))
                        })))
        });

        var something = jsonObject.deserialise(Something.class);
        var inception = something.getSomething();
        assertEquals("aValue", inception.getaString());
    }

    @Test
    void onePropertyWithAnObjectWithAnObjectWithAString() {
        var inceptionLevel2 = new JSONObject(new JSONProperty[] {
                new JSONProperty("aString", Optional.of(new StringValue("aValue")))
        });
        var inceptionLevel1 = new JSONObject(new JSONProperty[] {
                new JSONProperty("something", Optional.of(inceptionLevel2))
        });
        var jsonObject = new JSONObject(new JSONProperty[] {
                new JSONProperty("something",
                        Optional.of(inceptionLevel1))
        });

        var something = jsonObject.deserialise(Something.class);
        var inception = something.getSomething().getSomething();
        assertEquals("aValue", inception.getaString());
    }
}