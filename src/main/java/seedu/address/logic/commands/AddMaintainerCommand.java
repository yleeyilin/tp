package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.AddMessages;
import seedu.address.model.Model;
import seedu.address.model.person.Maintainer;

/**
 * Adds a maintainer to the address book.
 */
public class AddMaintainerCommand extends Command {
    public static final String COMMAND_WORD = "/add-maintainer";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a maintainer to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_SKILL + "SKILL "
            + PREFIX_COMMISSION + "COMMISSION \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe trainer "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_SKILL + "train dog "
            + PREFIX_COMMISSION + "$50/hr";

    private final Maintainer maintainerToAdd;

    /**
     * Creates an AddMaintainerCommand object.
     * @param maintainer The {@code Maintainer} to add.
     */
    public AddMaintainerCommand(Maintainer maintainer) {
        requireNonNull(maintainer);
        maintainerToAdd = maintainer;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(maintainerToAdd)) {
            throw new CommandException(AddMessages.MESSAGE_ADD_DUPLICATE_PERSON);
        }

        model.addPerson(maintainerToAdd);
        return new CommandResult(String.format(AddMessages.MESSAGE_ADD_PERSON_SUCCESS,
                AddMessages.formatPerson(maintainerToAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddMaintainerCommand)) {
            return false;
        }

        AddMaintainerCommand otherAddCommand = (AddMaintainerCommand) other;
        return maintainerToAdd.equals(otherAddCommand.maintainerToAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("maintainerToAdd", maintainerToAdd)
                .toString();
    }
}
