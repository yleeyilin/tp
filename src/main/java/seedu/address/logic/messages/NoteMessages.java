package seedu.address.logic.messages;

/**
 * Container for user note command visible messages.
 */
public class NoteMessages extends Messages {
    public static final String MESSAGE_ADD_NOTE_SUCCESS =
            "Woof! Added note to %1$s successfully! \uD83D\uDC36";
    public static final String MESSAGE_NOTE_NAME_NOT_FOUND = "Failed to add note to Pooch Contact - "
            + "Name does not exist in our address book \uD83D\uDC3E";
    public static final String MESSAGE_NOTE_NOT_SPECIFIED = "Failed to add note to Pooch Contact - "
            + "Note is not specified \uD83D\uDC3E";
    public static final String MESSAGE_NAME_NOT_SPECIFIED = "Failed to add note to Pooch Contact - "
            + "Name is not specified \uD83D\uDC3E";
    public static final String MESSAGE_DEADLINE_NOT_SPECIFIED = "Failed to add note to Pooch Contact - "
            + "Deadline is not specified \uD83D\uDC3E";
    public static final String MESSAGE_NOTE_INVALID_PARAMETERS = "Failed to add note to Pooch Contact - "
            + "%1$s \uD83D\uDC3E";
}
