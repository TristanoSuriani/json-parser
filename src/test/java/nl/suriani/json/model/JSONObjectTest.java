package nl.suriani.json.model;

import org.junit.jupiter.api.Test;

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