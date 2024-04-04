package seedu.address.logic.messages;


/**
 * Container for user delete command visible messages.
 */
public class DeleteMessages extends Messages {
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Woof! Deleted %1$s successfully! \uD83D\uDC36";
    public static final String MESSAGE_DELETE_NAME_NOT_FOUND = "Failed to delete Pooch Contact - "
            + "Name does not exist in our address book.\uD83D\uDC3E";
    public static final String MESSAGE_DELETE_MISSING_NAME = "Failed to delete Pooch Contact - "
            + "Delete requires a name field. %1$s\uD83D\uDC3E";
    public static final String MESSAGE_DELETE_INVALID_PARAMETERS = "Failed to delete Pooch Contact - "
            + "%1$s \uD83D\uDC3E";


}
