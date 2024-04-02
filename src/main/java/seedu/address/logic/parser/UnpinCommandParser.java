package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.messages.Messages.MESSAGE_COMMAND_FORMAT;
import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_FIELD_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;

import seedu.address.logic.commands.AddCommand;
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

        ArrayList<String> unknownPrefixes = ArgumentTokenizer.checkUnknownPrefix(args, PREFIX_NAME);

        if (unknownPrefixes.size() > 0) {
            String exception = String.format(MESSAGE_INVALID_FIELD_FORMAT, unknownPrefixes);
            exception += "\n" + String.format(MESSAGE_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);
            throw new ParseException(exception);
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            throw new ParseException(String.format(UnpinMessages.MESSAGE_UNPIN_MISSING_NAME,
                    UnpinCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.mapName(argMultimap, UnpinMessages.MESSAGE_UNPIN_INVALID_NAME);

        return new UnpinCommand(name);
    }
}
