package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ALICEMAINTAINER;
import static seedu.address.testutil.TypicalPersons.ALICESTAFF;
import static seedu.address.testutil.TypicalPersons.ALICESUPPLIER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.messages.AddMessages;
import seedu.address.logic.messages.DeleteMessages;
import seedu.address.logic.messages.EditMessages;
import seedu.address.logic.messages.Messages;
import seedu.address.logic.messages.PinMessages;
import seedu.address.logic.messages.UnpinMessages;

/**
 * Contains unit tests for {@code Messages}.
 */
public class MessagesTest {
    @Test
    public void format() {
        // Normal Person
        String testNormalString = Messages.format(ALICE);
        String expectedNormalString = "Alice Pauline; "
                + "Phone: 94351253; "
                + "Email: alice@example.com; "
                + "Address: 123, Jurong West Ave 6, #08-111; "
                + "Tags: [other]";
        assertEquals(testNormalString, expectedNormalString);

        // Staff
        String testStaffString = Messages.format(ALICESTAFF);
        String expectedStaffString = "Alice Pauline; "
                + "Phone: 94351253; "
                + "Email: alice@example.com; "
                + "Address: 123, Jurong West Ave 6, #08-111; "
                + "Tags: [staff]";
        assertEquals(testStaffString, expectedStaffString);

        // Maintainer
        String testMaintainerString = Messages.format(ALICEMAINTAINER);
        String expectedMaintainerString = "Alice Pauline; "
                + "Phone: 94351253; "
                + "Email: alice@example.com; "
                + "Address: 123, Jurong West Ave 6, #08-111; "
                + "Tags: [maintainer]";
        assertEquals(testMaintainerString, expectedMaintainerString);

        // Supplier
        String testSupplierString = Messages.format(ALICESUPPLIER);
        String expectedSupplierString = "Alice Pauline; "
                + "Phone: 94351253; "
                + "Email: alice@example.com; "
                + "Address: 123, Jurong West Ave 6, #08-111; "
                + "Tags: [supplier]";
        assertEquals(testSupplierString, expectedSupplierString);
    }
    @Test
    public void addFormat() {
        // Normal Person
        String testNormalString = AddMessages.formatPerson(ALICE);
        String expectedNormalString = "General Contact Alice Pauline";
        assertEquals(testNormalString, expectedNormalString);

        // Staff
        String testStaffString = AddMessages.formatPerson(ALICESTAFF);
        String expectedStaffString = "Staff Alice Pauline";
        assertEquals(testStaffString, expectedStaffString);

        // Maintainer
        String testMaintainerString = AddMessages.formatPerson(ALICEMAINTAINER);
        String expectedMaintainerString = "Maintainer Alice Pauline";
        assertEquals(testMaintainerString, expectedMaintainerString);

        // Supplier
        String testSupplierString = AddMessages.formatPerson(ALICESUPPLIER);
        String expectedSupplierString = "Supplier Alice Pauline";
        assertEquals(testSupplierString, expectedSupplierString);
    }

    @Test
    public void deleteFormat() {
        // Normal Person
        String testNormalString = DeleteMessages.formatPerson(ALICE);
        String expectedNormalString = "General Contact Alice Pauline";
        assertEquals(testNormalString, expectedNormalString);

        // Staff
        String testStaffString = DeleteMessages.formatPerson(ALICESTAFF);
        String expectedStaffString = "Staff Alice Pauline";
        assertEquals(testStaffString, expectedStaffString);

        // Maintainer
        String testMaintainerString = DeleteMessages.formatPerson(ALICEMAINTAINER);
        String expectedMaintainerString = "Maintainer Alice Pauline";
        assertEquals(testMaintainerString, expectedMaintainerString);

        // Supplier
        String testSupplierString = DeleteMessages.formatPerson(ALICESUPPLIER);
        String expectedSupplierString = "Supplier Alice Pauline";
        assertEquals(testSupplierString, expectedSupplierString);
    }

    @Test
    public void editFormat() {
        // Normal Person
        String testNormalString = EditMessages.formatPerson(ALICE);
        String expectedNormalString = "General Contact Alice Pauline";
        assertEquals(testNormalString, expectedNormalString);

        // Staff
        String testStaffString = EditMessages.formatPerson(ALICESTAFF);
        String expectedStaffString = "Staff Alice Pauline";
        assertEquals(testStaffString, expectedStaffString);

        // Maintainer
        String testMaintainerString = EditMessages.formatPerson(ALICEMAINTAINER);
        String expectedMaintainerString = "Maintainer Alice Pauline";
        assertEquals(testMaintainerString, expectedMaintainerString);

        // Supplier
        String testSupplierString = EditMessages.formatPerson(ALICESUPPLIER);
        String expectedSupplierString = "Supplier Alice Pauline";
        assertEquals(testSupplierString, expectedSupplierString);
    }

    @Test
    public void pinFormat() {
        // Normal Person
        String testNormalString = Messages.formatPerson(ALICE);
        String expectedNormalString = "General Contact Alice Pauline";
        assertEquals(testNormalString, expectedNormalString);

        // Staff
        String testStaffString = Messages.formatPerson(ALICESTAFF);
        String expectedStaffString = "Staff Alice Pauline";
        assertEquals(testStaffString, expectedStaffString);

        // Maintainer
        String testMaintainerString = Messages.formatPerson(ALICEMAINTAINER);
        String expectedMaintainerString = "Maintainer Alice Pauline";
        assertEquals(testMaintainerString, expectedMaintainerString);

        // Supplier
        String testSupplierString = Messages.formatPerson(ALICESUPPLIER);
        String expectedSupplierString = "Supplier Alice Pauline";
        assertEquals(testSupplierString, expectedSupplierString);
    }

    @Test
    public void unpinFormat() {
        // Normal Person
        String testNormalString = Messages.formatPerson(ALICE);
        String expectedNormalString = "General Contact Alice Pauline";
        assertEquals(testNormalString, expectedNormalString);

        // Staff
        String testStaffString = Messages.formatPerson(ALICESTAFF);
        String expectedStaffString = "Staff Alice Pauline";
        assertEquals(testStaffString, expectedStaffString);

        // Maintainer
        String testMaintainerString = Messages.formatPerson(ALICEMAINTAINER);
        String expectedMaintainerString = "Maintainer Alice Pauline";
        assertEquals(testMaintainerString, expectedMaintainerString);

        // Supplier
        String testSupplierString = Messages.formatPerson(ALICESUPPLIER);
        String expectedSupplierString = "Supplier Alice Pauline";
        assertEquals(testSupplierString, expectedSupplierString);
    }
}
