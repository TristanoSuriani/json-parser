package nl.suriani.csvtdd.model;

public record ColumnInfo(String name, Integer index) {
    public ColumnInfo {
        Guards.isNotNull(name);
        Guards.isNotNull(index);
        Guards.isNotEmpty(name);
        Guards.isZeroOrPositive(index);
    }
}
