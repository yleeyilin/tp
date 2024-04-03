package seedu.address.logic.messages;

/**
 * Container for user pin command visible messages.
 */
public class PinMessages {
    public static final String MESSAGE_PIN_PERSON_SUCCESS = "Woof! Pinned %1$s successfully! \uD83D\uDC36";
    public static final String MESSAGE_PIN_INVALID_NAME = "Failed to pin Pooch Contact. %1$s \uD83D\uDC3E";
    public static final String MESSAGE_PIN_NAME_NOT_FOUND = "Failed to pin Pooch Contact - "
            + "Name does not exist in our address book \uD83D\uDC3E";
    public static final String MESSAGE_PIN_MISSING_NAME = "Failed to pin Pooch Contact - "
            + "Pin requires a name field. %1$s \uD83D\uDC3E";

}
