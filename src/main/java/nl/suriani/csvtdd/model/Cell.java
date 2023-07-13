package nl.suriani.csvtdd.model;

public record Cell(String value, ColumnInfo columnInfo) {

    public Cell {
        Guards.isNotNull(value);
        Guards.isNotNull(columnInfo);
    }

    public boolean isEmpty() {
        return value.trim().isEmpty();
    }
}
