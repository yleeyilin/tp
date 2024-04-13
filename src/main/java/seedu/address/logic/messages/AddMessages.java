package seedu.address.logic.messages;

/**
 * Container for user add command visible messages.
 */
public class AddMessages extends Messages {
    public static final String FAILED_TO_ADD = "Failed to add Pooch Contact - ";
    public static final String MESSAGE_ADD_PERSON_SUCCESS = "Woof! Added %1$s successfully! \uD83D\uDC36";
    public static final String MESSAGE_ADD_DUPLICATE_PERSON = FAILED_TO_ADD
            + "Pooch Contact already exists in the planner \uD83D\uDC3E";
    public static final String MESSAGE_ADD_INVALID_PARAMETERS = FAILED_TO_ADD
            + "%1$s \uD83D\uDC3E";


}
