package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.GEORGIAMAINTAINER;
import static seedu.address.testutil.TypicalPersons.GEORGIASTAFF;
import static seedu.address.testutil.TypicalPersons.GEORGIASUPPLIER;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.RateMessages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;

public class RateCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Rating validRating1 = new Rating("1");

    @Test
    public void execute_validRatingOther_addSuccess() throws CommandException {
        // set expected results
        Person toAddNotePerson = ALICE;
        Person expectedPerson = new Person(toAddNotePerson.getName(), toAddNotePerson.getPhone(),
                toAddNotePerson.getEmail(), toAddNotePerson.getAddress(),
                toAddNotePerson.getNote(), toAddNotePerson.getTags(), validRating1);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), expectedPerson);

        // execute note command
        RateCommand rateCommand = new RateCommand(toAddNotePerson.getName(), validRating1);
        rateCommand.execute(model);

        assertEquals(model.getFilteredPersonList(), expectedModel.getFilteredPersonList());
        assertEquals(model.findPersonByName(toAddNotePerson.getName(),
                        RateMessages.MESSAGE_RATE_NAME_NOT_FOUND).getNote(),
                expectedModel.findPersonByName(toAddNotePerson.getName(),
                        RateMessages.MESSAGE_RATE_NAME_NOT_FOUND).getNote());
    }

    @Test
    public void execute_validRateStaff_addSuccess() throws CommandException {
        Model modelStaff = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        modelStaff.addPerson(GEORGIASTAFF);
        // set expected results
        Staff toAddNotePerson = GEORGIASTAFF;
        Staff expectedPerson = new Staff(toAddNotePerson.getName(), toAddNotePerson.getPhone(),
                toAddNotePerson.getEmail(), toAddNotePerson.getAddress(),
                toAddNotePerson.getNote(), toAddNotePerson.getTags(), toAddNotePerson.getSalary(),
                toAddNotePerson.getEmployment(),
                validRating1);
        Model expectedModel = new ModelManager(modelStaff.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(modelStaff.getFilteredPersonList().get(7), expectedPerson);

        // execute note command
        RateCommand rateCommand = new RateCommand(toAddNotePerson.getName(), validRating1);
        rateCommand.execute(modelStaff);

        assertEquals(modelStaff.getFilteredPersonList(), expectedModel.getFilteredPersonList());
        assertEquals(modelStaff.getFilteredPersonList().get(7).getNote(),
                expectedModel.getFilteredPersonList().get(7).getNote());
    }

    @Test
    public void execute_validRateSupplier_addSuccess() throws CommandException {
        Model modelSupplier = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        modelSupplier.addPerson(GEORGIASUPPLIER);
        // set expected results
        Supplier toAddNotePerson = GEORGIASUPPLIER;
        Supplier expectedPerson = new Supplier(toAddNotePerson.getName(), toAddNotePerson.getPhone(),
                toAddNotePerson.getEmail(), toAddNotePerson.getAddress(),
                toAddNotePerson.getNote(), toAddNotePerson.getTags(), toAddNotePerson.getProduct(),
                toAddNotePerson.getPrice(),
                validRating1);
        Model expectedModel = new ModelManager(modelSupplier.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(modelSupplier.getFilteredPersonList().get(7), expectedPerson);

        // execute note command
        RateCommand rateCommand = new RateCommand(toAddNotePerson.getName(), validRating1);
        rateCommand.execute(modelSupplier);

        assertEquals(modelSupplier.getFilteredPersonList(), expectedModel.getFilteredPersonList());
        assertEquals(modelSupplier.getFilteredPersonList().get(7).getNote(),
                expectedModel.getFilteredPersonList().get(7).getNote());
    }

    @Test
    public void execute_validRateMaintainer_addSuccess() throws CommandException {
        Model modelMaintainer = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        modelMaintainer.addPerson(GEORGIAMAINTAINER);
        // set expected results
        Maintainer toAddNotePerson = GEORGIAMAINTAINER;
        Maintainer expectedPerson = new Maintainer(toAddNotePerson.getName(), toAddNotePerson.getPhone(),
                toAddNotePerson.getEmail(), toAddNotePerson.getAddress(),
                toAddNotePerson.getNote(), toAddNotePerson.getTags(), toAddNotePerson.getSkill(),
                toAddNotePerson.getCommission(),
                validRating1);
        Model expectedModel = new ModelManager(modelMaintainer.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(modelMaintainer.getFilteredPersonList().get(7), expectedPerson);

        // execute note command
        RateCommand rateCommand = new RateCommand(toAddNotePerson.getName(), validRating1);
        rateCommand.execute(modelMaintainer);

        assertEquals(modelMaintainer.getFilteredPersonList(), expectedModel.getFilteredPersonList());
        assertEquals(modelMaintainer.getFilteredPersonList().get(7).getNote(),
                expectedModel.getFilteredPersonList().get(7).getNote());
    }

    @Test
    public void equals() {
        RateCommand rateFirstCommand = new RateCommand(ALICE.getName(), validRating1);
        RateCommand rateSecondCommand = new RateCommand(BENSON.getName(), validRating1);

        // same object -> returns true
        assertEquals(rateFirstCommand, rateFirstCommand);

        // different names -> returns false
        assertNotEquals(rateFirstCommand, rateSecondCommand);
    }
}
