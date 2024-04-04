package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.UndoMessages;
import seedu.address.model.Model;

/**
 * Undo Command.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "/undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undo previous execution. ";
    private final Logger logger = LogsCenter.getLogger(getClass());


    /**
     * Creates an UndoCommand.
     */
    public UndoCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.log(Level.INFO, "Going to execute undo command.");
        requireNonNull(model);

        if (!model.canUndoAddressBook()) {
            throw new CommandException(UndoMessages.MESSAGE_UNDO_FAIL);
        }

        model.undoAddressBook();
        return new CommandResult(UndoMessages.MESSAGE_UNDO_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UndoCommand)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
