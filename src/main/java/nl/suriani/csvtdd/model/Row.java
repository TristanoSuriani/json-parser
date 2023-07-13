package nl.suriani.csvtdd.model;

import java.util.List;

public record Row(List<Cell> cells) {

    public Row {
        Guards.isNotNull(cells);
        cells = List.copyOf(cells);
    }

    public String valueOf(String columnName) {
        return cells.stream()
                .filter(cell -> cell.columnInfo().name().equals(columnName))
                .map(Cell::value)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Unknown column '" + columnName + "'"));
    }

    public int size() {
        return cells.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Cell get(Integer index) {
        return cells.get(index);
    }
}
