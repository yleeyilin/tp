package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.SortMessages;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "/sort";

    public static final String MESSAGE_USAGE =
            "Sorts the address book based on specified parameters.\n"
            + "Example: /sort ; field : name";

    public static final String MESSAGE_CONSTRAINTS =
            "Only accepts name, phone, email, address, "
            + "salary, employment, price, product, "
            + "skill, commission, tag, note, pin "
            + "and rating as valid command type inputs.";

    private final Prefix prefix;

    /**
     * @param prefix parameter that the user wants to sort
     */
    public SortCommand(Prefix prefix) {
        requireNonNull(prefix);
        this.prefix = prefix;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateSortedPersonList(prefix);

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(SortMessages.MESSAGE_SORT_PERSON_SUCCESS);
    }
}
