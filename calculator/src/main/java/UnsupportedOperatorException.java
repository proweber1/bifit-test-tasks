class UnsupportedOperatorException extends Exception {
    UnsupportedOperatorException(String operator) {
        super("Unsupported expression operator: " + operator);
    }
}
