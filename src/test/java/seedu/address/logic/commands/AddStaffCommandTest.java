package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICESTAFF;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.AddMessages;
import seedu.address.logic.stubs.ModelStub;
import seedu.address.logic.stubs.ModelStubAcceptingPersonAdded;
import seedu.address.logic.stubs.ModelStubWithPerson;
import seedu.address.model.person.Staff;
import seedu.address.testutil.StaffBuilder;

//@@author chiageng
public class AddStaffCommandTest {
    @Test
    public void constructor_nullStaff_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddStaffCommand(null));
    }

    @Test
    public void execute_staffAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Staff validStaff = new StaffBuilder().build();

        CommandResult commandResult = new AddStaffCommand(validStaff).execute(modelStub);

        assertEquals(String.format(AddMessages.MESSAGE_ADD_PERSON_SUCCESS, AddMessages.formatPerson(validStaff)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validStaff), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicateStaff_throwsCommandException() {
        Staff validStaff = new StaffBuilder().build();
        AddStaffCommand addCommand = new AddStaffCommand(validStaff);
        ModelStub modelStub = new ModelStubWithPerson(validStaff);

        assertThrows(CommandException.class,
                AddMessages.MESSAGE_ADD_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Staff alice = new StaffBuilder().withName("Alice").build();
        Staff bob = new StaffBuilder().withName("Bob").build();
        AddStaffCommand addAliceCommand = new AddStaffCommand(alice);
        AddStaffCommand addBobCommand = new AddStaffCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddStaffCommand addAliceCommandCopy = new AddStaffCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddStaffCommand addCommand = new AddStaffCommand(ALICESTAFF);
        String expected = AddStaffCommand.class.getCanonicalName() + "{staffToAdd=" + ALICESTAFF + "}";
        assertEquals(expected, addCommand.toString());
    }
}
