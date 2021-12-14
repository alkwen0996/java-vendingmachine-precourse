package vendingmachine.utils;

public enum MarkingNumber {

    PRODUCT_INFORMATION_NAME_INDEX(0),
    PRODUCT_INFORMATION_PRICE_INDEX(1),
    PRODUCT_INFORMATION_COUNT_INDEX(2);

    private final int number;

    MarkingNumber(final int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

}
