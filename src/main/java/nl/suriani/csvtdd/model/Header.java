package nl.suriani.csvtdd.model;

public record Header(Row row) {

    public Header {
        Guards.isNotNull(row);
    }
}
