package vendingmachine.model;

public class Name {

    private final String name;

    public Name(final String name) {
        this.name = name;
    }

    public boolean compareProductName(final String purchasingProductName) {
        return name.equals(purchasingProductName);
    }

    public String getName() {
        return name;
    }

}
