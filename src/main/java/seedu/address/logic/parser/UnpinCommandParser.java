package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.messages.Messages.FAILED_TO_UNPIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.UnpinCommand;
import seedu.address.logic.messages.UnpinMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new UnpinCommand object
 */
public class UnpinCommandParser implements Parser<UnpinCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnpinCommand
     * and returns an UnpinCommand object for execution. Parameter args cannot be null.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnpinCommand parse(String args) throws ParseException {
        requireNonNull(args);
        assert (args != null) : "argument to pass for unpin command is null";

        Name name;
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        // Validates user command fields
        ParserUtil.verifyNoUnknownPrefix(args, UnpinCommand.MESSAGE_USAGE, "unpin",
                FAILED_TO_UNPIN, PREFIX_NAME);

        ParserUtil.verifyNoMissingField(argMultimap, UnpinCommand.MESSAGE_USAGE, "unpin",
                FAILED_TO_UNPIN, PREFIX_NAME);

        name = ParserUtil.mapName(argMultimap, UnpinMessages.MESSAGE_UNPIN_INVALID_NAME);

        return new UnpinCommand(name);
    }
}
