package nl.suriani.json.model;

import java.lang.reflect.Field;

class JSONDeserialiser {
    <V> V deserialise(JSONObject jsonObject, Class<V> clazz) {
        try {
            var newInstance = clazz.getConstructor().newInstance();

            for (JSONProperty property: jsonObject.value()) {
                var keyProperty = property.key();
                var field = clazz.getDeclaredField(keyProperty);
                var propertyValue = property.value().orElse(null);
                if (propertyValue instanceof StringValue) {
                    handleStringValue(clazz, newInstance, property, keyProperty);
                } else if (propertyValue instanceof IntegerValue) {
                    handleIntegerValue(clazz, newInstance, property, keyProperty);
                } else if (propertyValue instanceof DecimalValue) {
                    handleDecimalValue(clazz, newInstance, property, keyProperty);
                } else {
                    handleJSONObject(newInstance, property, keyProperty, field, clazz);
                }
            }

            return newInstance;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private <V> void handleIntegerValue(Class<V> clazz, V newInstance, JSONProperty property, String keyProperty) throws Exception {
        var valueProperty = property.value()
                .map(value -> (IntegerValue) value)
                .orElse(null);

        if (valueProperty == null) {
            return;
        }
        injectProperty(clazz, newInstance, keyProperty, resolveNumberType(clazz.getDeclaredField(keyProperty), valueProperty));
    }

    private <V> void handleDecimalValue(Class<V> clazz, V newInstance, JSONProperty property, String keyProperty) throws Exception {
        var valueProperty = property.value()
                .map(value -> (DecimalValue) value)
                .orElse(null);

        if (valueProperty == null) {
            return;
        }

        injectProperty(clazz, newInstance, keyProperty, resolveNumberType(clazz.getDeclaredField(keyProperty), valueProperty));
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

    private static Object resolveNumberType(Field field, IntegerValue integerValue) {
        final var type = field.getType().getName();
        return switch (type) {
            case "int", "java.lang.Integer" -> integerValue.toInt();
            case "long", "java.lang.Long" -> integerValue.toLong();
            case "java.math.BigInteger" -> integerValue.deserialise();
            default -> throw new UnsupportedOperationException("Unsupported type " + type);
        };
    }

    private static Object resolveNumberType(Field field, DecimalValue decimalValue) {
        final var type = field.getType().getName();
        return switch (type) {
            case "float", "java.lang.Float" -> decimalValue.toFloat();
            case "double", "java.lang.Double" -> decimalValue.toDouble();
            case "java.math.BigDecimal" -> decimalValue.deserialise();
            default -> throw new UnsupportedOperationException("Unsupported type " + type);
        };
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
