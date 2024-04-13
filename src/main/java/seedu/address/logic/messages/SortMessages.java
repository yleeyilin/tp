package seedu.address.logic.messages;

/**
 * Container for user sort command visible messages.
 */
public class SortMessages extends Messages {
    public static final String FAILED_TO_SORT = "Failed to sort Pooch Contact - ";
    public static final String MESSAGE_SORT_PERSON_SUCCESS =
            "Woof! Sorted PoochPlanner by %1$s successfully! \uD83D\uDC36";
    public static final String MESSAGE_SORT_INVALID_FIELD =
            FAILED_TO_SORT +  "Please input a valid target field! \uD83D\uDC3E";
}
