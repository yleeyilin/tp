package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineNoteTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        String validNote = "careful poochies";
        assertThrows(NullPointerException.class, () -> new DeadlineNote(validNote, null));
    }

    @Test
    public void constructor_invalidDeadlineNote_throwsIllegalArgumentException() {
        String invalidNote = "";
        String validDeadline = "2020-10-10";
        assertThrows(IllegalArgumentException.class, () -> new DeadlineNote(invalidNote, validDeadline));
    }


    @Test
    public void isValidDate() {
        // null deadlineNotes
        assertThrows(NullPointerException.class, () -> DeadlineNote.isValidDate(null));

        // invalid deadlineNotes
        assertFalse(DeadlineNote.isValidDate("")); // empty string
        assertFalse(DeadlineNote.isValidDate(" ")); // spaces only

        assertFalse(DeadlineNote.isValidDate("2020-13-08")); // invalid month only
        assertFalse(DeadlineNote.isValidDate("2020-12-32")); // invalid day only

        // valid deadlineNotes
        assertTrue(DeadlineNote.isValidDate("2019-10-10"));
    }

    @Test
    public void convertSuccessfulDate() {
        DeadlineNote deadlineNote = new DeadlineNote("valid note", "2019-10-10");
        assertEquals("Oct 10 2019", deadlineNote.convertDate("2019-10-10"));
    }

    @Test
    public void equals() {
        Note deadlineNote1 = new DeadlineNote("kind cats", "2025-10-10");
        Note deadlineNote2 = new DeadlineNote("kind cats", "2025-10-10");
        Note deadlineNote3 = new DeadlineNote("kind cats", "2345-10-10");


        // same values -> returns true
        assertTrue(deadlineNote1.equals(deadlineNote2));

        // same object -> returns true
        assertTrue(deadlineNote1.equals(deadlineNote1));

        // null -> returns false
        assertFalse(deadlineNote1.equals(null));

        // different types -> returns false
        assertFalse(deadlineNote1.equals(5));

        // different values -> returns false
        assertFalse(deadlineNote1.equals(deadlineNote3));
    }
}
