package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICEMAINTAINER;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.AddMessages;
import seedu.address.logic.stubs.ModelStub;
import seedu.address.logic.stubs.ModelStubAcceptingPersonAdded;
import seedu.address.logic.stubs.ModelStubWithPerson;
import seedu.address.model.person.Maintainer;
import seedu.address.testutil.MaintainerBuilder;

//@@author chiageng
public class AddMaintainerCommandTest {
    @Test
    public void constructor_nullMaintainer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMaintainerCommand(null));
    }

    @Test
    public void execute_maintainerAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Maintainer validMaintainer = new MaintainerBuilder().build();

        CommandResult commandResult = new AddMaintainerCommand(validMaintainer).execute(modelStub);

        assertEquals(String.format(AddMessages.MESSAGE_ADD_PERSON_SUCCESS, AddMessages.formatPerson(validMaintainer)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validMaintainer), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicateMaintainer_throwsCommandException() {
        Maintainer validMaintainer = new MaintainerBuilder().build();
        AddMaintainerCommand addCommand = new AddMaintainerCommand(validMaintainer);
        ModelStub modelStub = new ModelStubWithPerson(validMaintainer);

        assertThrows(CommandException.class,
                AddMessages.MESSAGE_ADD_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Maintainer alice = new MaintainerBuilder().withName("Alice").build();
        Maintainer bob = new MaintainerBuilder().withName("Bob").build();
        AddMaintainerCommand addAliceCommand = new AddMaintainerCommand(alice);
        AddMaintainerCommand addBobCommand = new AddMaintainerCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddMaintainerCommand addAliceCommandCopy = new AddMaintainerCommand(alice);
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
        AddMaintainerCommand addCommand = new AddMaintainerCommand(ALICEMAINTAINER);
        String expected = AddMaintainerCommand.class.getCanonicalName()
                + "{maintainerToAdd=" + ALICEMAINTAINER + "}";
        assertEquals(expected, addCommand.toString());
    }
}
