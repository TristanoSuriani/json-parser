package nl.suriani.csvtdd.model;

public class MalformedArgumentException extends DomainValidationException {
    public MalformedArgumentException(String message) {
        super(message);
    }
}
