package seedu.address.model.person;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s Note deadline is today or after today.
 */
public class RemindPredicate implements Predicate<Person> {
    private final Logger logger = LogsCenter.getLogger(getClass());

    @Override
    public boolean test(Person person) {
        Note currNote = person.getNote();
        if (!currNote.toString().contains(" by: ")) {
            return false;
        }
        String[] noteAndDate = currNote.toString().split(" by: ");

        try {

            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM d yyyy");

            LocalDate convertedDate = LocalDate.parse(noteAndDate[1], inputFormat);
            logger.info(person.getName().toString());

            String outputDate = convertedDate.toString();

            logger.info(outputDate);

            LocalDate noteDate = LocalDate.parse(outputDate);

            LocalDate currDate = LocalDate.now();
            if (!noteDate.isBefore(currDate)) {
                return true;
            } else {
                logger.info(outputDate);
                return false;
            }
        } catch (DateTimeException e) {
            // don't change the date in this case
            // as it is not a valid date, return false.
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof RemindPredicate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
