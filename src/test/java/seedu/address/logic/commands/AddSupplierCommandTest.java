package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICESUPPLIER;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.AddMessages;
import seedu.address.logic.stubs.ModelStub;
import seedu.address.logic.stubs.ModelStubAcceptingPersonAdded;
import seedu.address.logic.stubs.ModelStubWithPerson;
import seedu.address.model.person.Supplier;
import seedu.address.testutil.SupplierBuilder;

//@@author chiageng
public class AddSupplierCommandTest {
    @Test
    public void constructor_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddSupplierCommand(null));
    }

    @Test
    public void execute_supplierAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Supplier validSupplier = new SupplierBuilder().build();

        CommandResult commandResult = new AddSupplierCommand(validSupplier).execute(modelStub);

        assertEquals(String.format(AddMessages.MESSAGE_ADD_PERSON_SUCCESS, AddMessages.formatPerson(validSupplier)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validSupplier), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicateSupplier_throwsCommandException() {
        Supplier validSupplier = new SupplierBuilder().build();
        AddSupplierCommand addCommand = new AddSupplierCommand(validSupplier);
        ModelStub modelStub = new ModelStubWithPerson(validSupplier);

        assertThrows(CommandException.class,
                AddMessages.MESSAGE_ADD_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Supplier alice = new SupplierBuilder().withName("Alice").build();
        Supplier bob = new SupplierBuilder().withName("Bob").build();
        AddSupplierCommand addAliceCommand = new AddSupplierCommand(alice);
        AddSupplierCommand addBobCommand = new AddSupplierCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddSupplierCommand addAliceCommandCopy = new AddSupplierCommand(alice);
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
        AddSupplierCommand addCommand = new AddSupplierCommand(ALICESUPPLIER);
        String expected = AddSupplierCommand.class.getCanonicalName() + "{supplierToAdd=" + ALICESUPPLIER + "}";
        assertEquals(expected, addCommand.toString());
    }
}
