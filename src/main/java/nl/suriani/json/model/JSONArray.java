package nl.suriani.json.model;

public final class JSONArray extends JSONValue<JSONValue[]> {
    public JSONArray(JSONValue[] value) {
        super(value);
    }

    public <V> V[] deserialise(Class<V> clazz) {
        var jsonDeserialiser = new JSONDeserialiser();
        return jsonDeserialiser.deserialise(this, clazz);
    }
}
