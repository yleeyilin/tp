package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.messages.PinMessages.FAILED_TO_PIN;
import static seedu.address.logic.messages.PinMessages.PIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.PinCommand;
import seedu.address.logic.messages.PinMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new PinCommand object
 */
public class PinCommandParser implements Parser<PinCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PinCommand
     * and returns an PinCommand object for execution. Parameter args cannot be null.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PinCommand parse(String args) throws ParseException {
        requireNonNull(args);
        assert (args != null) : "argument to pass for pin command is null";

        Name name;
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        // validates user command fields
        ParserUtil.verifyNoUnknownPrefix(args, PinCommand.MESSAGE_USAGE, PIN,
                FAILED_TO_PIN,
                PREFIX_NAME);
        ParserUtil.verifyNoMissingField(argMultimap, PinCommand.MESSAGE_USAGE, PIN,
                FAILED_TO_PIN,
                PREFIX_NAME);

        name = ParserUtil.mapName(argMultimap, PinMessages.MESSAGE_PIN_INVALID_NAME);

        return new PinCommand(name);
    }
}
