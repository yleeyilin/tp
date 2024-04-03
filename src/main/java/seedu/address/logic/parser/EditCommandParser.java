package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.messages.EditMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Name name;
        String fieldArgs;

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_FIELD);

        ParserUtil.verifyNoUnknownPrefix(args, EditCommand.MESSAGE_USAGE,
                PREFIX_NAME, PREFIX_FIELD, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        boolean hasDuplicateNamePrefix = argMultimap.hasDuplicateNamePrefix();
        if (hasDuplicateNamePrefix) {
            throw new ParseException(String.format(EditMessages.MESSAGE_EDIT_NAME,
                    EditCommand.MESSAGE_USAGE));
        }

        // check for missing name
        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            throw new ParseException(String.format(EditMessages.MESSAGE_EDIT_MISSING_NAME,
                    EditCommand.MESSAGE_USAGE));
        }

        // check for missing field
        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_FIELD)) {
            throw new ParseException(String.format(EditMessages.MESSAGE_EDIT_MISSING_FIELD,
                    EditCommand.MESSAGE_USAGE));
        }

        name = ParserUtil.mapName(argMultimap, EditMessages.MESSAGE_EDIT_INVALID_NAME);
        fieldArgs = ParserUtil.mapFields(argMultimap, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditCommand.MESSAGE_USAGE));
        ArrayList<String> unknownFieldPrefixes = ArgumentTokenizer.checkUnknownPrefix(fieldArgs,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        ArgumentMultimap fieldArgMultimap =
                ArgumentTokenizer.tokenize(fieldArgs, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        fieldArgMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);
        EditPersonDescriptor editPersonDescriptor;

        try {
            editPersonDescriptor = editPersonDescription(fieldArgMultimap);
        } catch (ParseException pe) {
            throw new ParseException(String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD, pe.getMessage()));
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditMessages.MESSAGE_EDIT_EMPTY_FIELD);
        }

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("other"));
        editPersonDescriptor.setTags(tags);

        return new EditCommand(name, editPersonDescriptor);
    }

    /**
     * Edits the description of a Person.
     *
     * @param fieldArgMultimap The mapping of field arguments into different specific fields.
     * @return EditPersonDescriptor that contains the new values from the user.
     * @throws ParseException Indicates the invalid format that users might have entered.
     */
    private EditPersonDescriptor editPersonDescription(ArgumentMultimap fieldArgMultimap) throws ParseException {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        if (fieldArgMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(fieldArgMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(fieldArgMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(fieldArgMultimap.getValue(PREFIX_ADDRESS).get()));
        }

        return editPersonDescriptor;
    }
}
