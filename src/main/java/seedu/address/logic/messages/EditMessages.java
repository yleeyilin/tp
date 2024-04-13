package seedu.address.logic.messages;

/**
 * Container for user edit command visible messages.
 */
public class EditMessages extends Messages {
    public static final String EDIT = "edit";
    public static final String EDIT_STAFF = "edit-staff";
    public static final String EDIT_MAINTAINER = "edit-maintainer";
    public static final String EDIT_SUPPLIER = "edit-supplier";
    public static final String FAILED_TO_EDIT = "Failed to edit Pooch Contact - ";
    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Woof! Edited %1$s successfully! \uD83D\uDC36";
    public static final String MESSAGE_EDIT_EMPTY_FIELD = FAILED_TO_EDIT
            + "Field is empty or invalid! \uD83D\uDC3E";
    public static final String MESSAGE_EDIT_MISSING_FIELD = FAILED_TO_EDIT
            + "Edit requires a field prefix. %1$s \uD83D\uDC3E";
    public static final String MESSAGE_EDIT_MISSING_NAME = FAILED_TO_EDIT
            + "Edit requires a name field. %1$s \uD83D\uDC3E";
    public static final String MESSAGE_EDIT_NAME = FAILED_TO_EDIT
            + "Edit detect duplicate name fields. %1$s \uD83D\uDC3E";
    public static final String MESSAGE_EDIT_INVALID_NAME = FAILED_TO_EDIT + "%1$s \uD83D\uDC3E";
    public static final String MESSAGE_EDITING_NAME = FAILED_TO_EDIT
            + "Editing Pooch Contact names is not allowed . %1$s \uD83D\uDC3E";
    public static final String MESSAGE_EDIT_INVALID_FIELD = FAILED_TO_EDIT
            + "%1$s \uD83D\uDC3E";
    public static final String MESSAGE_EDIT_NO_DIFFERENCE = FAILED_TO_EDIT
            + "No difference detected!";
    public static final String MESSAGE_INVALID_EDIT_PERSON = "Name does not exist in our address book \uD83D\uDC3E"
            + "Make sure that you are attempting to edit OTHER.";
    public static final String MESSAGE_INVALID_EDIT_STAFF = "Name does not exist in our address book \uD83D\uDC3E"
            + "Make sure that you are attempting to edit STAFF.";
    public static final String MESSAGE_INVALID_EDIT_MAINTAINER = "Name does not exist in our address book \uD83D\uDC3E"
            + " Make sure that you are attempting to edit MAINTAINER.";
    public static final String MESSAGE_INVALID_EDIT_SUPPLIER = "Name does not exist in our address book \uD83D\uDC3E"
            + " Make sure that you are attempting to edit SUPPLIER.";
}
