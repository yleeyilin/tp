package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_COLLECTION;

import java.util.stream.Stream;

import seedu.address.logic.commands.RateCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.messages.SortMessages;
import seedu.address.logic.parser.exceptions.ParseException;

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

        ParserUtil.verifyNoUnknownPrefix(args, SortCommand.MESSAGE_USAGE, "sort",
                PREFIX_FIELD);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FIELD);

        ParserUtil.verifyNoMissingField(argMultimap, SortCommand.MESSAGE_USAGE, "sort",
                PREFIX_FIELD);

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
            String value = argMultimap.getValue(PREFIX_FIELD).get();

            for (Prefix prefix : PREFIX_SORT_COLLECTION) {
                String keyword = ParserUtil.parseSortField(prefix.getPrefix());
                if (keyword.equalsIgnoreCase(value)) {
                    return new Prefix(value);
                }
            }

            throw new ParseException(String.format(SortMessages.MESSAGE_SORT_INVALID_FIELD));

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
