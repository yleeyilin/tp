package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.HelpMessages;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "/help";
    public static final String MESSAGE_USAGE = "Shows program usage instructions.\n"
            + "Example: /help ; command : exit";
    public static final String MESSAGE_CONSTRAINTS = "Only accepts general, add, delete, edit,"
            + " exit, search, list, note, pin, unpin, note, rate, redo, undo, remind, sort as"
            + " valid command type inputs.";
    private String commandType;


    enum CommandTypes {
        GENERAL,
        ADD,
        DELETE,
        EDIT,
        EXIT,
        SEARCH,
        LIST,
        NOTE,
        PIN,
        UNPIN,
        RATE,
        REDO,
        UNDO,
        REMIND,
        SORT
    }

    /**
     * @param commandType of command to get help for.
     */
    public HelpCommand(String commandType) {
        requireAllNonNull(commandType);
        this.commandType = commandType;
    }

    /**
     * Checks if command provided is a valid command.
     *
     * @param commandType command type that user needs help for.
     *
     * @return boolean showing whether the command type is valid.
     * */
    public static boolean isValidCommandType(String commandType) {
        assert (commandType != null) : "specified command type to give help for is null";

        for (CommandTypes c : CommandTypes.values()) {
            String lowercaseName = c.name().toLowerCase();
            String lowercaseCommandType = commandType.toLowerCase();

            if (lowercaseName.equals(lowercaseCommandType)) {
                return true;
            }
        }
        return false;
    }

    // strange that i do accept model as param never interact. but cant removed cuz
    // it inherits this from command parent
    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert (commandType != null) : "specified command type to give help for is null";

        String capitalisedCommandType = commandType.toUpperCase();
        if (capitalisedCommandType.equals(CommandTypes.GENERAL.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_HELP_MESSAGE, true, false);
        } else if (capitalisedCommandType.equals(CommandTypes.ADD.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_ADD_HELP_MESSAGE, true, false);
        } else if (capitalisedCommandType.equals(CommandTypes.DELETE.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_DELETE_HELP_MESSAGE, true, false);
        } else if (capitalisedCommandType.equals(CommandTypes.EDIT.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_EDIT_HELP_MESSAGE, true, false);
        } else if (capitalisedCommandType.equals(CommandTypes.SEARCH.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_SEARCH_HELP_MESSAGE, true, false);
        } else if (capitalisedCommandType.equals(CommandTypes.EXIT.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_EXIT_HELP_MESSAGE, true, false);
        } else if (capitalisedCommandType.equals(CommandTypes.LIST.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_LIST_HELP_MESSAGE, true, false);
        } else if (capitalisedCommandType.equals(CommandTypes.NOTE.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_NOTE_HELP_MESSAGE, true, false);
        } else if (capitalisedCommandType.equals(CommandTypes.PIN.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_PIN_HELP_MESSAGE, true, false);
        } else if (capitalisedCommandType.equals(CommandTypes.UNPIN.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_UNPIN_HELP_MESSAGE, true, false);
        } else if (capitalisedCommandType.equals(CommandTypes.RATE.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_RATE_HELP_MESSAGE, true, false);
        } else if (capitalisedCommandType.equals(CommandTypes.REDO.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_REDO_HELP_MESSAGE, true, false);
        } else if (capitalisedCommandType.equals(CommandTypes.UNDO.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_UNDO_HELP_MESSAGE, true, false);
        } else if (capitalisedCommandType.equals(CommandTypes.REMIND.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_REMIND_HELP_MESSAGE, true, false);
        } else if (capitalisedCommandType.equals(CommandTypes.SORT.name())) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_SORT_HELP_MESSAGE, true, false);
        } else {
            throw new CommandException(HelpMessages.MESSAGES_INVALID_COMMAND_TYPE);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HelpCommand)) {
            return false;
        }

        HelpCommand otherHelpCommand = (HelpCommand) other;
        return commandType.equals(otherHelpCommand.commandType);
    }
}
