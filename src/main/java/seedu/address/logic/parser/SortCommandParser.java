package seedu.address.logic.parser;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.messages.SortMessages;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;

/**
 * Parses input arguments and creates a new PinCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PinCommand
     * and returns an PinCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Prefix prefix;

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FIELD);

        // check for missing name
        if (!arePrefixesPresent(argMultimap, PREFIX_FIELD)) {
            throw new ParseException(SortMessages.MESSAGE_SORT_INVALID_FIELD);
        }

        prefix = mapName(argMultimap);

        return new SortCommand(prefix);
    }

    /**
     * Returns prefix value
     * @param argMultimap Object that contains mapping of field to prefix.
     * @return Returns object representing prefix
     * @throws ParseException Thrown when command is in invalid format.
     */
    public Prefix mapName(ArgumentMultimap argMultimap) throws ParseException {
        try {
            String value = ParserUtil.parseField(argMultimap.getValue(PREFIX_FIELD).get());

            List<String> validKeywords = List.of(
                    "name",
                    "phone",
                    "email",
                    "address",
                    "salary",
                    "employment",
                    "product",
                    "price",
                    "skill",
                    "commission",
                    "tag",
                    "note",
                    "pin",
                    "unpin",
                    "rating");

            if (!validKeywords.contains(value.toLowerCase())) {
                throw new ParseException(SortMessages.MESSAGE_SORT_INVALID_FIELD);
            }

            return new Prefix(value);

        } catch (ParseException pe) {
            throw new ParseException(String.format(SortMessages.MESSAGE_SORT_INVALID_FIELD, pe.getMessage()));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
