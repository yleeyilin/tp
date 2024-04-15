package seedu.address.logic.parser;

import static seedu.address.logic.messages.AddMessages.ADD_SUPPLIER;
import static seedu.address.logic.messages.AddMessages.FAILED_TO_ADD;
import static seedu.address.logic.messages.AddMessages.SUPPLIER_TYPE;
import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.messages.NoteMessages.DEFAULT_NOTE;
import static seedu.address.logic.messages.RateMessages.DEFAULT_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddSupplierCommand;
import seedu.address.logic.messages.AddMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Price;
import seedu.address.model.person.Product;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Supplier;
import seedu.address.model.tag.Tag;

//@@author chiageng
/**
 * Parses input arguments and creates a new AddSupplierCommand object.
 */
public class AddSupplierCommandParser implements Parser<AddSupplierCommand> {
    public static final String MESSAGE_NULL_ARGUMENTS = "argument to pass for add supplier command is null";
    public static final String MESSAGE_COMMENCE_PARSING = "Going to start parsing for add supplier command.";
    private final Logger logger = LogsCenter.getLogger(getClass());
    /**
     * Parses the given {@code String} of arguments in the context of the AddStaffCommand
     * and returns an AddCommand object for execution. Parameter {@code args} cannot be null.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public AddSupplierCommand parse(String args) throws ParseException {
        assert (args != null) : MESSAGE_NULL_ARGUMENTS;

        logger.log(Level.INFO, MESSAGE_COMMENCE_PARSING);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_PRODUCT, PREFIX_PRICE, PREFIX_RATING);

        // validates user command fields
        ParserUtil.verifyNoUnknownPrefix(args, AddSupplierCommand.MESSAGE_USAGE, ADD_SUPPLIER,
                FAILED_TO_ADD, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_PRODUCT, PREFIX_PRICE, PREFIX_RATING);
        ParserUtil.verifyNoMissingField(argMultimap, AddSupplierCommand.MESSAGE_USAGE, ADD_SUPPLIER,
                FAILED_TO_ADD, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_PRODUCT, PREFIX_PRICE);
        boolean isPreambleEmpty = argMultimap.isPreambleEmpty();
        if (!isPreambleEmpty) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSupplierCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_PRODUCT, PREFIX_PRICE);

        Supplier person = createSupplierContact(argMultimap);

        return new AddSupplierCommand(person);
    }
    //@@author

    /**
     * Creates a {@code Supplier} contact based on the argument multimap.
     * @param argMultimap Contains the mappings of values to the specific prefixes.
     * @return A supplier contact.
     * @throws ParseException If the user enters invalid parameters.
     */
    private Supplier createSupplierContact(ArgumentMultimap argMultimap) throws ParseException {
        try {
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).orElseThrow());
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).orElseThrow());
            Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).orElseThrow());
            Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).orElseThrow());
            String noteContent = argMultimap.getValue(PREFIX_NOTE).orElse(DEFAULT_NOTE);
            Note note = noteContent.equals(DEFAULT_NOTE) ? new Note(noteContent) : ParserUtil.parseNote(noteContent);
            Rating rating = ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).orElse(DEFAULT_RATING));
            Tag tag = new Tag(SUPPLIER_TYPE);
            Set<Tag> tags = new HashSet<>();
            tags.add(tag);
            Price price = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).orElseThrow());
            Product product = ParserUtil.parseProduct(argMultimap.getValue(PREFIX_PRODUCT).orElseThrow());
            return new Supplier(name, phone, email, address, note, tags, product, price, rating);
        } catch (ParseException pe) {
            throw new ParseException(String.format(AddMessages.MESSAGE_ADD_INVALID_PARAMETERS, pe.getMessage()));
        }
    }
}
