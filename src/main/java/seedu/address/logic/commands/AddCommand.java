package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.AddMessages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

//@@author chiageng
/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "/add-person";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe other "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 ";

    private final Person personToAdd;

    /**
     * Creates an AddCommand object.
     * @param person The {@code Person} to add.
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        personToAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(personToAdd)) {
            throw new CommandException(AddMessages.MESSAGE_ADD_DUPLICATE_PERSON);
        }

        model.addPerson(personToAdd);
        return new CommandResult(String.format(AddMessages.MESSAGE_ADD_PERSON_SUCCESS,
                AddMessages.formatPerson(personToAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return personToAdd.equals(otherAddCommand.personToAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("personToAdd", personToAdd)
                .toString();
    }
}
