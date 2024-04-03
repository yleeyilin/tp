package seedu.address.logic.messages;

import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;

/**
 * Container for user add command visible messages.
 */
public class AddMessages extends Messages {
    public static final String MESSAGE_ADD_PERSON_SUCCESS = "Woof! Added %1$s successfully! \uD83D\uDC36";
    public static final String MESSAGE_ADD_DUPLICATE_PERSON = "Failed to add Pooch Contact - "
            + "Pooch Contact already exists in the planner \uD83D\uDC3E";
    public static final String MESSAGE_ADD_INVALID_PARAMETERS = "Failed to add Pooch Contact - "
            + "%1$s \uD83D\uDC3E";


}
