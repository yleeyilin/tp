package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.UnpinCommand;
import seedu.address.logic.messages.UnpinMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new PinCommand object
 */
public class UnpinCommandParser implements Parser<UnpinCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PinCommand
     * and returns an PinCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnpinCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ParserUtil.verifyNoUnknownPrefix(args, UnpinCommand.MESSAGE_USAGE, "unpin", PREFIX_NAME);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        ParserUtil.verifyNoMissingField(argMultimap, UnpinCommand.MESSAGE_USAGE, "unpin",
                PREFIX_NAME);

        Name name = ParserUtil.mapName(argMultimap, UnpinMessages.MESSAGE_UNPIN_INVALID_NAME);

        return new UnpinCommand(name);
    }
}
