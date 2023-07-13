package nl.suriani.json.model;

import java.util.Objects;

public abstract sealed class JSONValue<T> permits DecimalValue, IntegerValue, JSONArray, JSONObject, StringValue {
    private T value;

    public JSONValue(T value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public T value() {
        return value;
    }
}
