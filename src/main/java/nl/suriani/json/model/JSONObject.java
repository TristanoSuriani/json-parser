package nl.suriani.json.model;

public final class JSONObject extends JSONValue<JSONProperty[]> {
    public JSONObject(JSONProperty[] value) {
        super(value);
    }

    public <V> V deserialise(Class<V> clazz) {
        final var deserialiser = new JSONDeserialiser();
        return deserialiser.deserialise(this, clazz);
    }
}
