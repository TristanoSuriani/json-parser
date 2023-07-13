package nl.suriani.csvtdd.model;

public interface Guards {
    static void isNotNull(Object o) {
        if (o == null) {
            throw new MissingRequiredArgumentException();
        }
    }

    static void isNotEmpty(String s) {
        if (s.trim().isEmpty()) {
            throw new MalformedArgumentException("Value cannot be empty");
        }
    }

    static void isZeroOrPositive(Integer i) {
        if (i < 0) {
            throw new MalformedArgumentException("Value should be > 0 but is '" + i + "'");
        }
    }
}
