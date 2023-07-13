package nl.suriani.csvtdd.model;

import java.util.List;

public record Body(List<Row> rows) {

    public Body {
        Guards.isNotNull(rows);
        rows = List.copyOf(rows);
    }

    public boolean isEmpty() {
        return rows.isEmpty();
    }
}
