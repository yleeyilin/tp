package seedu.address.logic.parser;

import static seedu.address.logic.messages.AddMessages.FAILED_TO_ADD;
import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.messages.AddMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Rating;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution. Parameter args cannot be null.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        assert (args != null) : "`argument` to pass for add command is null";

        logger.log(Level.INFO, "Going to start parsing for add command.");

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_NOTE,
                        PREFIX_RATING);

        // validates user command fields
        ParserUtil.verifyNoUnknownPrefix(args, AddCommand.MESSAGE_USAGE, "add",
                FAILED_TO_ADD,
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_NOTE, PREFIX_RATING);
        ParserUtil.verifyNoMissingField(argMultimap, AddCommand.MESSAGE_USAGE, "add",
                FAILED_TO_ADD,
                PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL);
        boolean isPreambleEmpty = argMultimap.isPreambleEmpty();
        if (!isPreambleEmpty) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        Person person = createPersonContact(argMultimap);

        return new AddCommand(person);
    }

    /**
     * Creates a person contact based on the argument multimap.
     * @param argMultimap Contains the mappings of values to the specific prefixes.
     * @return A person contact.
     * @throws ParseException Thrown when invalid paramters are used.
     */
    private Person createPersonContact(ArgumentMultimap argMultimap) throws ParseException {
        try {
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).orElseThrow());
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).orElseThrow());
            Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).orElseThrow());
            Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).orElseThrow());
            String noteContent = argMultimap.getValue(PREFIX_NOTE).orElse("No note here");
            Note note = noteContent.equals("No note here") ? new Note(noteContent) : ParserUtil.parseNote(noteContent);
            Rating rating = ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).orElse("0"));
            Tag tag = new Tag("other");
            Set<Tag> tags = new HashSet<>();
            tags.add(tag);
            return new Person(name, phone, email, address, note, tags, rating);
        } catch (ParseException pe) {
            throw new ParseException(String.format(AddMessages.MESSAGE_ADD_INVALID_PARAMETERS, pe.getMessage()));
        }
    }
}
