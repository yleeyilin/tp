package seedu.address.logic.parser;

import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.messages.NoteMessages.FAILED_TO_ADD_NOTE;
import static seedu.address.logic.messages.NoteMessages.NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.NoteCommand;
import seedu.address.logic.messages.NoteMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;

/**
 * Parses input arguments and creates a new NoteCommand object.
 */
public class NoteCommandParser implements Parser<NoteCommand> {
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the NoteCommand
     * and returns a NoteCommand object for execution. Parameter {@code args} cannot be null.
     *
     * @param args Argument to parse.
     * @return NoteCommand object with the parsed values.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public NoteCommand parse(String args) throws ParseException {
        assert (args != null) : "argument to pass for note command is null";
        logger.log(Level.INFO, "Going to start parsing for note command.");

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_NOTE, PREFIX_DEADLINE);

        // validates user command fields
        ParserUtil.verifyNoUnknownPrefix(args, NoteCommand.MESSAGE_USAGE, NOTE,
                FAILED_TO_ADD_NOTE, PREFIX_NAME, PREFIX_NOTE, PREFIX_DEADLINE);
        ParserUtil.verifyNoMissingField(argMultimap, NoteCommand.MESSAGE_USAGE, NOTE,
                FAILED_TO_ADD_NOTE, PREFIX_NAME, PREFIX_NOTE);
        boolean isPreambleEmpty = argMultimap.isPreambleEmpty();
        if (!isPreambleEmpty) {
            logger.log(Level.WARNING, "Parsing error while parsing for note command.");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE));
        }

        try {
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            boolean isContainingDeadlinePrefix = argMultimap.containsPrefix(PREFIX_DEADLINE);
            Note note;
            if (!isContainingDeadlinePrefix) {
                note = ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE).get());
            } else {
                note = ParserUtil.parseDeadlineNote(argMultimap.getValue(PREFIX_NOTE).get(),
                        argMultimap.getValue(PREFIX_DEADLINE).get());
            }
            return new NoteCommand(name, note);
        } catch (ParseException pe) {
            throw new ParseException(String.format(NoteMessages.MESSAGE_NOTE_INVALID_PARAMETERS, pe.getMessage()));
        }
    }
}
