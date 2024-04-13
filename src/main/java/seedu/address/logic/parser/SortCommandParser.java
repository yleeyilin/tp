package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.messages.SortMessages.FAILED_TO_SORT;
import static seedu.address.logic.messages.SortMessages.SORT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_COLLECTION;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.messages.SortMessages;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution. Parameter {@code args} cannot be null.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        assert (args != null) : "argument to pass for sort command is null";

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FIELD);

        // validates user command fields
        ParserUtil.verifyNoUnknownPrefix(args, SortCommand.MESSAGE_USAGE, SORT,
                FAILED_TO_SORT, PREFIX_FIELD);
        ParserUtil.verifyNoMissingField(argMultimap, SortCommand.MESSAGE_USAGE, SORT,
                FAILED_TO_SORT, PREFIX_FIELD);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_FIELD);

        // duplicate field entries
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_SORT_COLLECTION);

        Prefix prefix = ParserUtil.mapSortFields(argMultimap, SortMessages.MESSAGE_SORT_INVALID_FIELD);

        return new SortCommand(prefix);
    }
}
