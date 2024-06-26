package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.EditMessages;
import seedu.address.model.Model;
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

//@@author yleeyilin
/**
 * Edits the details of an existing staff in PoochPlanner.
 */
public class EditStaffCommand extends Command {
    public static final String COMMAND_WORD = "/edit-staff";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Main Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_FIELD + "FIELD] \n"
            + "Field Parameters: "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_SALARY + "SALARY] "
            + "[" + PREFIX_EMPLOYMENT + "EMPLOYMENT] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe Staff "
            + PREFIX_FIELD + "{ "
            + "phone : " + "99820550 "
            + PREFIX_ADDRESS + "NUS College Avenue"
            + " }";
    public static final String MESSAGE_NULL_NAME = "Specified name to edit staff is null.";

    private final Name name;
    private final EditStaffDescriptor editStaffDescriptor;
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * @param name Name of the staff in the filtered person list to edit.
     * @param editStaffDescriptor Details to edit the staff with.
     */
    public EditStaffCommand(Name name, EditStaffDescriptor editStaffDescriptor) {
        requireAllNonNull(name, editStaffDescriptor);
        this.name = name;
        this.editStaffDescriptor = new EditStaffDescriptor(editStaffDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert (name != null) : MESSAGE_NULL_NAME;
        requireNonNull(model);

        Staff staffToEdit = model.findStaffByName(name,
                EditMessages.MESSAGE_INVALID_EDIT_STAFF);
        Staff editedStaff = createEditedStaff(staffToEdit, editStaffDescriptor);

        model.setPerson(staffToEdit, editedStaff);
        model.updateFilteredPersonListWithCommit(PREDICATE_SHOW_ALL_PERSONS);

        logger.fine(String.format(EditMessages.MESSAGE_EDIT_PERSON_SUCCESS,
                EditMessages.formatPerson(editedStaff)));

        return new CommandResult(String.format(EditMessages.MESSAGE_EDIT_PERSON_SUCCESS,
                EditMessages.formatPerson(editedStaff)));
    }

    /**
     * Creates and returns a {@code Staff} with the details of {@code staffToEdit}
     * edited with {@code editStaffDescriptor}.
     */
    private static Staff createEditedStaff(Staff staffToEdit, EditStaffDescriptor editStaffDescriptor) {
        assert staffToEdit != null;

        Name updatedName = editStaffDescriptor.getName().orElse(staffToEdit.getName());
        Phone updatedPhone = editStaffDescriptor.getPhone().orElse(staffToEdit.getPhone());
        Email updatedEmail = editStaffDescriptor.getEmail().orElse(staffToEdit.getEmail());
        Address updatedAddress = editStaffDescriptor.getAddress().orElse(staffToEdit.getAddress());
        Note presentNote = staffToEdit.getNote(); //edit cannot change note
        Rating presentRating = staffToEdit.getRating(); //edit cannot change rating
        Set<Tag> updatedTags = editStaffDescriptor.getTags().orElse(staffToEdit.getTags());
        Salary updatedSalary = editStaffDescriptor.getSalary().orElse(staffToEdit.getSalary());
        Employment updatedEmployment = editStaffDescriptor.getEmployment().orElse(staffToEdit.getEmployment());

        Staff updatedStaff = new Staff(updatedName, updatedPhone, updatedEmail, updatedAddress,
                presentNote, updatedTags, updatedSalary, updatedEmployment, presentRating);

        if (staffToEdit.isPinned()) {
            updatedStaff.toPin();
        }

        return updatedStaff;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditStaffCommand)) {
            return false;
        }

        EditStaffCommand otherEditStaffCommand = (EditStaffCommand) other;
        boolean areNamesEqual = name.equals(otherEditStaffCommand.name);
        boolean areDescriptorsEqual = editStaffDescriptor.equals(otherEditStaffCommand.editStaffDescriptor);
        return areNamesEqual && areDescriptorsEqual;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("editStaffDescriptor", editStaffDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the staff with. Each non-empty field value will replace the
     * corresponding field value of the staff.
     */
    public static class EditStaffDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private Salary salary;
        private Employment employment;

        public EditStaffDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStaffDescriptor(EditStaffDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setSalary(toCopy.salary);
            setEmployment(toCopy.employment);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags, salary, employment);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setSalary(Salary salary) {
            this.salary = salary;
        }

        public Optional<Salary> getSalary() {
            return Optional.ofNullable(salary);
        }

        public void setEmployment(Employment employment) {
            this.employment = employment;
        }

        public Optional<Employment> getEmployment() {
            return Optional.ofNullable(employment);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditStaffDescriptor)) {
                return false;
            }

            EditStaffDescriptor otherEditStaffDescriptor = (EditStaffDescriptor) other;

            boolean arePhoneEqual = Objects.equals(phone, otherEditStaffDescriptor.phone);
            boolean areEmailEqual = Objects.equals(email, otherEditStaffDescriptor.email);
            boolean areAddressEqual = Objects.equals(address, otherEditStaffDescriptor.address);
            boolean areTagsEqual = Objects.equals(tags, otherEditStaffDescriptor.tags);
            boolean areSalaryEqual = Objects.equals(salary, otherEditStaffDescriptor.salary);
            boolean areEmploymentEqual = Objects.equals(employment, otherEditStaffDescriptor.employment);

            return arePhoneEqual && areEmailEqual && areAddressEqual
                    && areTagsEqual && areSalaryEqual && areEmploymentEqual;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("tags", tags)
                    .add("salary", salary)
                    .add("employment", employment)
                    .toString();
        }
    }
}
