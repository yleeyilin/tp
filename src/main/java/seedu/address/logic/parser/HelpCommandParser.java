package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_HELP;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

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
     * Parameter args cannot be null.
     * and returns a HelpCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String args) throws ParseException {
        assert (args != null) : "argument to pass for help command is null";
        logger.log(Level.INFO, "Going to start parsing for help command.");

        ParserUtil.verifyNoUnknownPrefix(args, HelpCommand.MESSAGE_USAGE, "help", PREFIX_HELP);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_HELP);

        ParserUtil.verifyNoMissingField(argMultimap, HelpCommand.MESSAGE_USAGE, "help", PREFIX_HELP);

        String commandType;

        if (!argMultimap.isPreambleEmpty()) {
            logger.log(Level.WARNING, "Parsing error while parsing for help command.");
            throw new ParseException(String.format(HelpMessages.MESSAGE_HELP_MISSING_COMMAND,
                    HelpCommand.MESSAGE_USAGE));
        }
        commandType = ParserUtil.parseHelp(argMultimap.getValue(PREFIX_HELP).get());
        return new HelpCommand(commandType);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
