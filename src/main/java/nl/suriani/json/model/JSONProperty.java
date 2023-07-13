package nl.suriani.json.model;

import java.util.Objects;
import java.util.Optional;

public record JSONProperty(String key, Optional<JSONValue> value) {
    public JSONProperty {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
    }
}
