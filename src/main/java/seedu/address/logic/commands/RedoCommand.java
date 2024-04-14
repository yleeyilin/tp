package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.RedoMessages;
import seedu.address.model.Model;

/**
 * Redoes the last undid command.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "/redo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Redo previous undid command. ";
    public static final String LOGGER_MESSAGE = "Going to execute redo command.";

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Creates a RedoCommand Object.
     */
    public RedoCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.log(Level.INFO, LOGGER_MESSAGE);
        requireNonNull(model);

        if (!model.canRedoAddressBook()) {
            throw new CommandException(RedoMessages.MESSAGE_REDO_FAIL);
        }

        model.redoAddressBook();
        return new CommandResult(RedoMessages.MESSAGE_REDO_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        return other instanceof RedoCommand;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
