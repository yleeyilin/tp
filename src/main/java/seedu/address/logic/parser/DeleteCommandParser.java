package seedu.address.logic.parser;

import static seedu.address.logic.messages.Messages.FAILED_TO_DELETE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.messages.DeleteMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        assert (args != null) : "`argument` to pass for delete command is null";
        logger.log(Level.INFO, "Going to start parsing for delete command.");

        ParserUtil.verifyNoUnknownPrefix(args, DeleteCommand.MESSAGE_USAGE, "delete",
                FAILED_TO_DELETE, PREFIX_NAME);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        ParserUtil.verifyNoMissingField(argMultimap, DeleteCommand.MESSAGE_USAGE, "delete",
                FAILED_TO_DELETE, PREFIX_NAME);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(DeleteMessages.MESSAGE_DELETE_MISSING_NAME,
                    DeleteCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        try {
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).orElseThrow());
            return new DeleteCommand(name);
        } catch (ParseException pe) {
            throw new ParseException(String.format(DeleteMessages.MESSAGE_DELETE_INVALID_PARAMETERS, pe.getMessage()));
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
