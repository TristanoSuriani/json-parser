package nl.suriani.json.model;

import java.math.BigDecimal;

public final class DecimalValue extends JSONValue<BigDecimal> {
    public DecimalValue(BigDecimal value) {
        super(value);
    }

    public BigDecimal deserialise() {
        return deserialise();
    }

    public Float toFloat() {
        return deserialise().floatValue();
    }

    public Double toDouble() {
        return deserialise().doubleValue();
    }
}
