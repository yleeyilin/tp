package seedu.address.logic.messages;


/**
 * Container for user unpin command visible messages.
 */
public class UnpinMessages {
    public static final String MESSAGE_UNPIN_PERSON_SUCCESS = "Woof! Unpinned %1$s successfully! \uD83D\uDC36";
    public static final String MESSAGE_UNPIN_INVALID_NAME = "Failed to unpin Pooch Contact - %1$s \uD83D\uDC3E";
    public static final String MESSAGE_UNPIN_NAME_NOT_FOUND = "Failed to unpin Pooch Contact - "
            + "Name does not exist in our address book \uD83D\uDC3E";
    public static final String MESSAGE_UNPIN_MISSING_NAME = "Failed to unpin Pooch Contact - "
            + "Unpin requires a name field. %1$s \uD83D\uDC3E";

}
