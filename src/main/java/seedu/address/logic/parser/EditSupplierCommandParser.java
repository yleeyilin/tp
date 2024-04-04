package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.messages.Messages.FAILED_TO_EDIT;
import static seedu.address.logic.messages.Messages.FAILED_TO_EDIT_WITH_NAME;
import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.commands.EditSupplierCommand;
import seedu.address.logic.commands.EditSupplierCommand.EditSupplierDescriptor;
import seedu.address.logic.messages.EditMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditSupplierCommand object
 */
public class EditSupplierCommandParser implements Parser<EditSupplierCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditSupplierCommand
     * and returns an EditSupplierCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditSupplierCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Name name;
        String fieldArgs;

        String parsedArgs = ParserUtil.parseArg(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(parsedArgs, PREFIX_NAME, PREFIX_FIELD);

        ParserUtil.verifyNoUnknownPrefix(parsedArgs, EditSupplierCommand.MESSAGE_USAGE, "edit-supplier",
                FAILED_TO_EDIT,
                PREFIX_NAME, PREFIX_FIELD, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_NAME, PREFIX_PRODUCT, PREFIX_PRICE);

        boolean hasDuplicateNamePrefix = argMultimap.hasDuplicateNamePrefix();
        if (hasDuplicateNamePrefix) {
            throw new ParseException(String.format(EditMessages.MESSAGE_EDIT_NAME,
                    EditSupplierCommand.MESSAGE_USAGE));
        }

        // check for missing fields
        ParserUtil.verifyNoMissingField(argMultimap, EditSupplierCommand.MESSAGE_USAGE, "edit-supplier",
                FAILED_TO_EDIT,
                PREFIX_NAME, PREFIX_FIELD);

        name = ParserUtil.mapName(argMultimap, EditMessages.MESSAGE_EDIT_INVALID_NAME);
        fieldArgs = ParserUtil.mapFields(argMultimap, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditSupplierCommand.MESSAGE_USAGE));

        ArgumentMultimap fieldArgMultimap =
                ArgumentTokenizer.tokenize(fieldArgs, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_NAME, PREFIX_PRODUCT, PREFIX_PRICE);

        ParserUtil.verifyNoUnknownPrefix(fieldArgs, EditSupplierCommand.MESSAGE_USAGE, "edit",
                FAILED_TO_EDIT_WITH_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_PRODUCT, PREFIX_PRICE);

        fieldArgMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        EditSupplierDescriptor editSupplierDescriptor;

        try {
            editSupplierDescriptor = editSupplierDescription(fieldArgMultimap);
        } catch (ParseException pe) {
            throw new ParseException(String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD, pe.getMessage()));
        }

        if (!editSupplierDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditMessages.MESSAGE_EDIT_EMPTY_FIELD);
        }

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("supplier"));
        editSupplierDescriptor.setTags(tags);

        return new EditSupplierCommand(name, editSupplierDescriptor);
    }

    /**
     * Edits the description of a Supplier.
     *
     * @param fieldArgMultimap The mapping of field arguments into different specific fields.
     * @return EditSupplierDescriptor that contains the new values from the user.
     * @throws ParseException Indicates the invalid format that users might have entered.
     */
    private EditSupplierDescriptor editSupplierDescription(ArgumentMultimap fieldArgMultimap) throws ParseException {
        EditSupplierDescriptor editSupplierDescription = new EditSupplierDescriptor();

        if (fieldArgMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editSupplierDescription.setPhone(ParserUtil.parsePhone(fieldArgMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editSupplierDescription.setEmail(ParserUtil.parseEmail(fieldArgMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editSupplierDescription.setAddress(ParserUtil.parseAddress(
                    fieldArgMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_PRODUCT).isPresent()) {
            editSupplierDescription.setProduct(ParserUtil.parseProduct(
                    fieldArgMultimap.getValue(PREFIX_PRODUCT).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_PRICE).isPresent()) {
            editSupplierDescription.setPrice(ParserUtil.parsePrice(fieldArgMultimap.getValue(PREFIX_PRICE).get()));
        }

        return editSupplierDescription;
    }
}
