package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;

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
     * and returns an PinCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PinCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ParserUtil.verifyNoUnknownPrefix(args, PinCommand.MESSAGE_USAGE, PREFIX_NAME);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            throw new ParseException(String.format(PinMessages.MESSAGE_PIN_MISSING_NAME,
                    PinCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.mapName(argMultimap, PinMessages.MESSAGE_PIN_INVALID_NAME);

        return new PinCommand(name);
    }
}
