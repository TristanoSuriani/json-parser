package nl.suriani.csvtdd.model;

import java.util.List;
import java.util.function.Function;

public record CSV(Header header, Body body) {

    public CSV {
        Guards.isNotNull(header);
        Guards.isNotNull(body);
    }

    public <T> List<T> map(Function<Row, T> mapper) {
        Guards.isNotNull(mapper);
        return body.rows().stream()
                .map(mapper)
                .toList();
    }

    public boolean isEmpty() {
        return body.isEmpty();
    }
}
