package nl.suriani.json.model;

import java.util.Collection;

public final class JSONArray extends JSONValue<JSONValue[]> {
    public JSONArray(JSONValue[] value) {
        super(value);
    }

    public <C extends Collection<T>, T> C deserialise(Class<T> clazz, Class<C> collectionClazz) {
        return null;
    }

    public <V> V[] deserialiseToArray(Class<V> clazz) {
        var jsonDeserialiser = new JSONDeserialiser();
        return jsonDeserialiser.deserialiseToArray(this, clazz);
    }
}
