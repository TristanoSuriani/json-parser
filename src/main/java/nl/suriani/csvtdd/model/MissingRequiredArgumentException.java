package nl.suriani.csvtdd.model;

public class MissingRequiredArgumentException extends DomainValidationException {
    public MissingRequiredArgumentException() {
        super("Value is required");
    }
}
