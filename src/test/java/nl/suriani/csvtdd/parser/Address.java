package nl.suriani.csvtdd.parser;

import nl.suriani.csvtdd.model.*;

import java.util.List;

public record Address(String street, String houseNumber, String houseNumberSuffix, String zipCode, String location) {


    static CSV addressCSV() {
        var streetHeader = new Cell("street", new ColumnInfo("street", 0));
        var houseNumberHeader = new Cell("houseNumber", new ColumnInfo("houseNumber", 0));
        var houseNumberSuffixHeader = new Cell("houseNumberSuffix", new ColumnInfo("houseNumberSuffix", 0));
        var zipCodeHeader = new Cell("zipCode", new ColumnInfo("zipCode", 0));
        var locationHeader = new Cell("location", new ColumnInfo("location", 0));

        var street0 = new Cell("straatlaan", new ColumnInfo("street", 0));
        var houseNumber0 = new Cell("1", new ColumnInfo("houseNumber", 0));
        var houseNumberSuffix0 = new Cell("", new ColumnInfo("houseNumberSuffix", 0));
        var zipCode0 = new Cell("10123", new ColumnInfo("zipCode", 0));
        var location0 = new Cell("Lelijkstad", new ColumnInfo("location", 0));

        var street1 = new Cell("laanpad", new ColumnInfo("street", 0));
        var houseNumber1 = new Cell("10", new ColumnInfo("houseNumber", 0));
        var houseNumberSuffix1 = new Cell("A", new ColumnInfo("houseNumberSuffix", 0));
        var zipCode1 = new Cell("213AS4", new ColumnInfo("zipCode", 0));
        var location1 = new Cell("Amsterdaamseveen", new ColumnInfo("location", 0));

        return new CSV(new Header(new Row(List.of(streetHeader, houseNumberHeader, houseNumberSuffixHeader, zipCodeHeader, locationHeader))),
                new Body(List.of(
                        new Row(List.of(street0, houseNumber0, houseNumberSuffix0, zipCode0, location0)),
                        new Row(List.of(street1, houseNumber1, houseNumberSuffix1, zipCode1, location1))
                )));
    }

    static String addressString = """
            street,houseNumber,houseNumberSuffix,zipCode,location
            straatlaan,1,,10123,Lelijkstad
            laanpad,10,A,213AS4,Amsterdaamseveen""";
}
