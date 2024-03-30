package seedu.address.logic.parser;

import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;

import java.util.stream.Stream;

import seedu.address.logic.commands.NoteCommand;
import seedu.address.logic.messages.NoteMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;

/**
 * Parses input arguments and creates a new NoteCommand object
 */
public class NoteCommandParser implements Parser<NoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the NoteCommand
     * and returns a NoteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public NoteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NAME, PREFIX_NOTE, PREFIX_DEADLINE);
        Name name;
        Note note;
        boolean isContainingNotePrefix = arePrefixesPresent(argMultimap, PREFIX_NOTE);
        boolean isContainingDeadlinePrefix = argMultimap.containsPrefix(PREFIX_DEADLINE);
        boolean isContainingNameNotePrefix = arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_NOTE);
        boolean isPreambleEmpty = argMultimap.getPreamble().isEmpty();

        if (!isContainingNotePrefix) {
            throw new ParseException(NoteMessages.MESSAGE_NOTE_NOT_SPECIFIED);
        }

        if (!isContainingNameNotePrefix || !isPreambleEmpty) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE));
        }

        if (!isContainingDeadlinePrefix) {
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            note = ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE).get());
        } else {
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            note = ParserUtil.parseDeadlineNote(argMultimap.getValue(PREFIX_NOTE).get(),
                    argMultimap.getValue(PREFIX_DEADLINE).get());
        }
        return new NoteCommand(name, note);
    }

    //method repeated, need to abstract somewhere
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
