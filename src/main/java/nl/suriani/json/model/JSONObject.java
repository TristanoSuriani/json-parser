package nl.suriani.json.model;

import java.lang.reflect.Field;

public final class JSONObject extends JSONValue<JSONProperty[]> {
    public JSONObject(JSONProperty[] value) {
        super(value);
    }

    public <V> V deserialise(Class<V> clazz) {
        try {
            var newInstance = clazz.getConstructor().newInstance();

            for (JSONProperty property: value()) {
                var keyProperty = property.key();
                var field = clazz.getDeclaredField(keyProperty);
                var propertyValue = property.value().orElse(null);
                if (propertyValue instanceof StringValue) {
                    handleStringValue(clazz, newInstance, property, keyProperty);
                } else {
                    handleJSONObject(newInstance, property, keyProperty, field, clazz);
                }
            }

            return newInstance;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static <V> void handleJSONObject(V newInstance, JSONProperty property, String keyProperty, Field field, Class<?> clazz) throws Exception {
        var valueProperty = property.value()
                .map(value -> (JSONObject) value)
                .map(jsonObject -> deserialiseObject(field, jsonObject))
                .orElse(null);

        injectProperty(clazz, newInstance, keyProperty, valueProperty);
    }

    private static <V> void handleStringValue(Class<V> clazz, V newInstance, JSONProperty property, String keyProperty) throws Exception {
        var valueProperty = property.value()
                .map(value -> (StringValue) value)
                .map(StringValue::deserialise)
                .orElse(null);
        injectProperty(clazz, newInstance, keyProperty, valueProperty);
    }

    private static Object deserialiseObject(Field field, JSONObject jsonObject) {
        var result = jsonObject.deserialise(field.getDeclaringClass());
        return result;
    }

    private static void injectProperty(Class clazz, Object newInstance, String keyProperty, Object valueProperty) throws NoSuchFieldException, IllegalAccessException {
        var field = clazz.getDeclaredField(keyProperty);
        field.setAccessible(true);
        field.set(newInstance, valueProperty);
    }
}
