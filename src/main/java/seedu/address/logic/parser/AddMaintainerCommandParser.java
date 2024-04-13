package seedu.address.logic.parser;

import static seedu.address.logic.messages.AddMessages.ADD_MAINTAINER;
import static seedu.address.logic.messages.AddMessages.FAILED_TO_ADD;
import static seedu.address.logic.messages.AddMessages.MAINTAINER_TYPE;
import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.messages.NoteMessages.DEFAULT_NOTE;
import static seedu.address.logic.messages.RateMessages.DEFAULT_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddMaintainerCommand;
import seedu.address.logic.messages.AddMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Commission;
import seedu.address.model.person.Email;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Skill;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddStaffCommand object
 */
public class AddMaintainerCommandParser implements Parser<AddMaintainerCommand> {

    private final Logger logger = LogsCenter.getLogger(getClass());
    /**
     * Parses the given {@code String} of arguments in the context of the AddStaffCommand
     * and returns an AddCommand object for execution. Parameter {@code args} cannot be null.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public AddMaintainerCommand parse(String args) throws ParseException {
        assert (args != null) : "argument to pass for add maintainer command is null";

        logger.log(Level.INFO, "Going to start parsing for add maintainer command.");

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_SKILL, PREFIX_COMMISSION, PREFIX_RATING);

        // validates user command fields
        ParserUtil.verifyNoUnknownPrefix(args, AddMaintainerCommand.MESSAGE_USAGE, ADD_MAINTAINER,
                FAILED_TO_ADD,
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_SKILL, PREFIX_COMMISSION, PREFIX_RATING);
        ParserUtil.verifyNoMissingField(argMultimap, AddMaintainerCommand.MESSAGE_USAGE, ADD_MAINTAINER,
                FAILED_TO_ADD,
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_SKILL, PREFIX_COMMISSION);
        boolean isPreambleEmpty = argMultimap.isPreambleEmpty();
        if (!isPreambleEmpty) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMaintainerCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_SKILL, PREFIX_COMMISSION);

        Maintainer person = createMaintainerContact(argMultimap);

        return new AddMaintainerCommand(person);
    }

    /**
     * Creates a maintainer contact based on the argument multimap.
     * @param argMultimap Contains the mappings of values to the specific prefixes.
     * @return A maintainer contact.
     * @throws ParseException If the user enters invalid paramters.
     */
    private Maintainer createMaintainerContact(ArgumentMultimap argMultimap) throws ParseException {
        try {
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).orElseThrow());
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).orElseThrow());
            Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).orElseThrow());
            Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).orElseThrow());
            String noteContent = argMultimap.getValue(PREFIX_NOTE).orElse(DEFAULT_NOTE);
            Note note = noteContent.equals(DEFAULT_NOTE) ? new Note(noteContent) : ParserUtil.parseNote(noteContent);
            Rating rating = ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).orElse(DEFAULT_RATING));
            Tag tag = new Tag(MAINTAINER_TYPE);
            Set<Tag> tags = new HashSet<>();
            tags.add(tag);
            Skill skill = ParserUtil.parseSkill(argMultimap.getValue(PREFIX_SKILL).orElseThrow());
            Commission commission = ParserUtil.parseCommission(argMultimap.getValue(PREFIX_COMMISSION).orElseThrow());
            return new Maintainer(name, phone, email, address, note, tags, skill, commission, rating);
        } catch (ParseException pe) {
            throw new ParseException(String.format(AddMessages.MESSAGE_ADD_INVALID_PARAMETERS, pe.getMessage()));
        }
    }
}
