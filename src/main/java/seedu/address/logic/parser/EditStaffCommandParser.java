package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.messages.Messages.FAILED_TO_EDIT;
import static seedu.address.logic.messages.Messages.FAILED_TO_EDIT_WITH_NAME;
import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.commands.EditMaintainerCommand;
import seedu.address.logic.commands.EditStaffCommand;
import seedu.address.logic.commands.EditStaffCommand.EditStaffDescriptor;
import seedu.address.logic.messages.EditMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditStaffCommand object
 */
public class EditStaffCommandParser implements Parser<EditStaffCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditStaffCommand
     * and returns an EditStaffCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditStaffCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Name name;
        String fieldArgs;

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_FIELD);

        ParserUtil.verifyNoUnknownPrefix(args, EditStaffCommand.MESSAGE_USAGE, "edit-staff",
                FAILED_TO_EDIT, PREFIX_NAME,
                PREFIX_FIELD, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_EMPLOYMENT, PREFIX_SALARY);

        boolean hasDuplicateNamePrefix = argMultimap.hasDuplicateNamePrefix();
        if (hasDuplicateNamePrefix) {
            throw new ParseException(String.format(EditMessages.MESSAGE_EDIT_NAME,
                    EditStaffCommand.MESSAGE_USAGE));
        }

        // check for missing fields
        ParserUtil.verifyNoMissingField(argMultimap, EditStaffCommand.MESSAGE_USAGE, "edit-staff",
                FAILED_TO_EDIT,
                PREFIX_NAME, PREFIX_FIELD);

        name = ParserUtil.mapName(argMultimap, EditMessages.MESSAGE_EDIT_INVALID_NAME);
        fieldArgs = ParserUtil.mapFields(argMultimap, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditStaffCommand.MESSAGE_USAGE));

        ArgumentMultimap fieldArgMultimap =
                ArgumentTokenizer.tokenize(fieldArgs, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_EMPLOYMENT, PREFIX_SALARY);

        ParserUtil.verifyNoUnknownPrefix(fieldArgs, EditStaffCommand.MESSAGE_USAGE, "edit",
                FAILED_TO_EDIT_WITH_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_EMPLOYMENT, PREFIX_SALARY);

        fieldArgMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_EMPLOYMENT, PREFIX_SALARY);

        EditStaffDescriptor editStaffDescriptor;

        try {
            editStaffDescriptor = editStaffDescription(fieldArgMultimap);
        } catch (ParseException pe) {
            throw new ParseException(String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD, pe.getMessage()));
        }

        if (!editStaffDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditMessages.MESSAGE_EDIT_EMPTY_FIELD);
        }

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("staff"));
        editStaffDescriptor.setTags(tags);

        return new EditStaffCommand(name, editStaffDescriptor);
    }

    /**
     * Edits the description of a Staff.
     *
     * @param fieldArgMultimap The mapping of field arguments into different specific fields.
     * @return EditStaffDescriptor that contains the new values from the user.
     * @throws ParseException Indicates the invalid format that users might have entered.
     */
    private EditStaffDescriptor editStaffDescription(ArgumentMultimap fieldArgMultimap) throws ParseException {
        EditStaffDescriptor editStaffDescription = new EditStaffDescriptor();

        if (fieldArgMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editStaffDescription.setPhone(ParserUtil.parsePhone(fieldArgMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editStaffDescription.setEmail(ParserUtil.parseEmail(fieldArgMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editStaffDescription.setAddress(ParserUtil.parseAddress(fieldArgMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_SALARY).isPresent()) {
            editStaffDescription.setSalary(ParserUtil.parseSalary(fieldArgMultimap.getValue(PREFIX_SALARY).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_EMPLOYMENT).isPresent()) {
            editStaffDescription.setEmployment(ParserUtil.parseEmployment(
                    fieldArgMultimap.getValue(PREFIX_EMPLOYMENT).get()));
        }

        return editStaffDescription;
    }
}
