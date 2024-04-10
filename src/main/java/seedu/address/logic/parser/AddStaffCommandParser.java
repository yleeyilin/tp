package seedu.address.logic.parser;

import static seedu.address.logic.messages.Messages.FAILED_TO_ADD;
import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddStaffCommand;
import seedu.address.logic.messages.AddMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Employment;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Salary;
import seedu.address.model.person.Staff;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddStaffCommand object
 */
public class AddStaffCommandParser implements Parser<AddStaffCommand> {
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the AddStaffCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStaffCommand parse(String args) throws ParseException {
        logger.log(Level.INFO, "Going to start parsing for add staff command.");
        ParserUtil.verifyNoUnknownPrefix(args, AddStaffCommand.MESSAGE_USAGE, "add-staff",
                FAILED_TO_ADD,
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_SALARY, PREFIX_EMPLOYMENT, PREFIX_RATING);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_SALARY, PREFIX_EMPLOYMENT, PREFIX_RATING);

        ParserUtil.verifyNoMissingField(argMultimap, AddStaffCommand.MESSAGE_USAGE, "add-staff",
                FAILED_TO_ADD,
                PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_EMPLOYMENT, PREFIX_SALARY);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStaffCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_SALARY, PREFIX_EMPLOYMENT);

        try {
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).orElseThrow());
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).orElseThrow());
            Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).orElseThrow());
            Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).orElseThrow());
            String noteContent = argMultimap.getValue(PREFIX_NOTE).orElse("No note here");
            Note note = noteContent.equals("No note here") ? new Note(noteContent) : ParserUtil.parseNote(noteContent);
            Rating rating = ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).orElse("0"));
            Tag tag = new Tag("staff");
            Set<Tag> tags = new HashSet<>();
            tags.add(tag);
            Employment employment = ParserUtil.parseEmployment(argMultimap.getValue(PREFIX_EMPLOYMENT).orElseThrow());
            Salary salary = ParserUtil.parseSalary(argMultimap.getValue(PREFIX_SALARY).orElseThrow());

            Staff person = new Staff(name, phone, email, address, note, tags, salary, employment, rating);

            return new AddStaffCommand(person);
        } catch (ParseException pe) {
            throw new ParseException(String.format(AddMessages.MESSAGE_ADD_INVALID_PARAMETERS, pe.getMessage()));
        }
    }
}
