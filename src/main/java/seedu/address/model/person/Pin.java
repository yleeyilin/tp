package seedu.address.model.person;

/**
 * Represents a Person's Pin in the address book.
 */
public class Pin {

    private Boolean isPin;

    /**
     * Constructs a {@code Pin}.
     */
    public Pin() {
        isPin = false;
    }

    public void setPin() {
        this.isPin = true;
    }

    public void setUnpin() {
        this.isPin = false;
    }

    public boolean getIsPinned() {
        return this.isPin;
    }

    @Override
    public String toString() {
        return isPin ? "true" : "false";
    }
}
