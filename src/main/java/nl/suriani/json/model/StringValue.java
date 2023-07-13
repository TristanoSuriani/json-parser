package nl.suriani.json.model;

public final class StringValue extends JSONValue<String> {
    public StringValue(String value) {
        super(value);
    }

    public String deserialise() {
        return value();
    }
}
