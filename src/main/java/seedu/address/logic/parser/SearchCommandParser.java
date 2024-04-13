package seedu.address.logic.parser;

import static seedu.address.logic.messages.SearchMessages.FAILED_TO_SEARCH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEARCH_COLLECTION;

import java.util.stream.Stream;

import seedu.address.logic.commands.SearchCommand;
import seedu.address.logic.messages.SearchMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.KeywordPredicate;

/**
 * Parses input arguments and creates a new SearchCommand object
 */
public class SearchCommandParser implements Parser<SearchCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SearchCommand
     * and returns a SearchCommand object for execution. Parameter args cannot be null.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SearchCommand parse(String args) throws ParseException {
        assert (args != null) : "argument to pass for search command is null";

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SEARCH_COLLECTION);

        // Validate correct user command
        ParserUtil.verifyNoUnknownPrefix(
                args,
                SearchCommand.MESSAGE_USAGE,
                "search",
                FAILED_TO_SEARCH,
                PREFIX_SEARCH_COLLECTION);
        boolean isPreambleEmpty = argMultimap.isPreambleEmpty();
        if (!isPreambleEmpty) {
            throw new ParseException(SearchMessages.MESSAGE_SEARCH_INVALID_FIELD);
        }
        boolean isAnyPrefixPresent = atLeastOnePrefixPresent(argMultimap, PREFIX_SEARCH_COLLECTION);
        if (!isAnyPrefixPresent) {
            throw new ParseException(SearchMessages.MESSAGE_SEARCH_MISSING_FIELD);
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_SEARCH_COLLECTION);
        argMultimap.verifyNoEmptyEntries();

        return new SearchCommand(new KeywordPredicate(argMultimap));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean atLeastOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
