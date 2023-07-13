package nl.suriani.json.model;

import java.math.BigInteger;

public final class IntegerValue extends JSONValue<BigInteger> {
    public IntegerValue(BigInteger value) {
        super(value);
    }

    public BigInteger deserialise() {
        return value();
    }

    public int toInt() {
        return deserialise().intValue();
    }

    public long toLong() {
        return deserialise().longValue();
    }
}
