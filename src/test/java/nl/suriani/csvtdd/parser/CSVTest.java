package nl.suriani.csvtdd.parser;

import nl.suriani.csvtdd.model.Row;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;


public class CSVTest {
    private final static Function<Row, Address> ROW_TO_ADDRESS_MAPPER = row ->
         new Address(row.valueOf("street"),
                row.valueOf("houseNumber"),
                row.valueOf("houseNumberSuffix"),
                row.valueOf("zipCode"),
                row.valueOf("location"));

    @Test
    void testMapping() {
        var csv = Address.addressCSV();
        var addresses = csv.map(ROW_TO_ADDRESS_MAPPER);

        Assertions.assertEquals("straatlaan", addresses.get(0).street());
        Assertions.assertEquals("1", addresses.get(0).houseNumber());
        Assertions.assertEquals("", addresses.get(0).houseNumberSuffix());
        Assertions.assertEquals("10123", addresses.get(0).zipCode());
        Assertions.assertEquals("Lelijkstad", addresses.get(0).location());

        Assertions.assertEquals("laanpad", addresses.get(1).street());
        Assertions.assertEquals("10", addresses.get(1).houseNumber());
        Assertions.assertEquals("A", addresses.get(1).houseNumberSuffix());
        Assertions.assertEquals("213AS4", addresses.get(1).zipCode());
        Assertions.assertEquals("Amsterdaamseveen", addresses.get(1).location());
    }
}
