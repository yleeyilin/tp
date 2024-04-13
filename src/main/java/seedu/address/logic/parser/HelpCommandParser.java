package seedu.address.logic.parser;

import static seedu.address.logic.messages.HelpMessages.FAILED_TO_HELP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HELP;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.messages.HelpMessages;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HelpCommand object
 */
public class HelpCommandParser implements Parser<HelpCommand> {
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution. Parameter args cannot be null.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String args) throws ParseException {
        assert (args != null) : "argument to pass for help command is null";

        logger.log(Level.INFO, "going to start parsing for help command.");
        String commandType;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_HELP);

        // validates user command fields
        ParserUtil.verifyNoUnknownPrefix(args, HelpCommand.MESSAGE_USAGE, "help",
                FAILED_TO_HELP, PREFIX_HELP);
        ParserUtil.verifyNoMissingField(argMultimap, HelpCommand.MESSAGE_USAGE, "help",
                FAILED_TO_HELP, PREFIX_HELP);
        boolean isPreambleEmpty = argMultimap.isPreambleEmpty();
        if (!isPreambleEmpty) {
            logger.log(Level.WARNING, "Parsing error while parsing for help command.");
            throw new ParseException(String.format(HelpMessages.MESSAGE_HELP_MISSING_COMMAND,
                    HelpCommand.MESSAGE_USAGE));
        }

        try {
            commandType = ParserUtil.parseHelp(argMultimap.getValue(PREFIX_HELP).orElseThrow());
        } catch (ParseException pe) {
            throw new ParseException(String.format(HelpMessages.MESSAGE_HELP_INVALID_PARAMETERS, pe.getMessage()));
        }
        return new HelpCommand(commandType);
    }
}
