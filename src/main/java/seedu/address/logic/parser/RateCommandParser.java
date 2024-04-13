package seedu.address.logic.parser;

import static seedu.address.logic.messages.RateMessages.FAILED_TO_RATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.RateCommand;
import seedu.address.logic.messages.RateMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Rating;

/**
 * Parses input arguments and creates a new RateCommand object
 */
public class RateCommandParser implements Parser<RateCommand> {
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the RateCommand
     * and returns a RateCommand object for execution. Parameter args cannot be null.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public RateCommand parse(String args) throws ParseException {
        assert (args != null) : "argument to pass for rate command is null";

        logger.log(Level.INFO, "Going to start parsing for rate command.");
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_RATING);
        Name name;
        Rating rating;

        // validates user command fields
        ParserUtil.verifyNoUnknownPrefix(args, RateCommand.MESSAGE_USAGE, "rate",
                FAILED_TO_RATE,
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_NOTE, PREFIX_RATING);
        ParserUtil.verifyNoMissingField(argMultimap, RateCommand.MESSAGE_USAGE, "rate",
                FAILED_TO_RATE,
                PREFIX_NAME, PREFIX_RATING);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        try {
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).orElseThrow());
            rating = ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).orElseThrow());
            return new RateCommand(name, rating);
        } catch (ParseException pe) {
            throw new ParseException(String.format(RateMessages.MESSAGE_RATE_INVALID_PARAMETERS, pe.getMessage()));
        }
    }
}
