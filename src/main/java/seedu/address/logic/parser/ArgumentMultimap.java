package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.messages.Messages;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Stores mapping of prefixes to their respective arguments.
 * Each key may be associated with multiple argument values.
 * Values for a given key are stored in a list, and the insertion ordering is maintained.
 * Keys are unique, but the list of argument values may contain duplicate argument values, i.e. the same argument value
 * can be inserted multiple times for the same prefix.
 */
public class ArgumentMultimap {

    /** Prefixes mapped to their respective arguments**/
    private final Map<Prefix, List<String>> argMultimap = new HashMap<>();

    /**
     * Associates the specified argument value with {@code prefix} key in this map.
     * If the map previously contained a mapping for the key, the new value is appended to the list of existing values.
     *
     * @param prefix   Prefix key with which the specified argument value is to be associated
     * @param argValue Argument value to be associated with the specified prefix key
     */
    public void put(Prefix prefix, String argValue) {
        List<String> argValues = getAllValues(prefix);
        argValues.add(argValue);
        argMultimap.put(prefix, argValues);
    }

    /**
     * Returns the last value of {@code prefix}.
     */
    public Optional<String> getValue(Prefix prefix) {
        List<String> values = getAllValues(prefix);
        return values.isEmpty() ? Optional.empty() : Optional.of(values.get(values.size() - 1));
    }

    /**
     * Returns all values of {@code prefix}.
     * If the prefix does not exist or has no values, this will return an empty list.
     * Modifying the returned list will not affect the underlying data structure of the ArgumentMultimap.
     */
    public List<String> getAllValues(Prefix prefix) {
        if (!argMultimap.containsKey(prefix)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(argMultimap.get(prefix));
    }

    /**
     * Returns the preamble (text before the first valid prefix). Trims any leading/trailing spaces.
     */
    public String getPreamble() {
        return getValue(new Prefix("")).orElse("");
    }

    //@@author chiageng
    /**
     * Throws a {@code ParseException} if any of the prefixes given in {@code prefixes} appeared more than
     * once among the arguments.
     */
    public void verifyNoDuplicatePrefixesFor(Prefix... prefixes) throws ParseException {
        Prefix[] duplicatedPrefixes = Stream.of(prefixes).distinct()
                .filter(prefix -> argMultimap.containsKey(prefix) && argMultimap.get(prefix).size() > 1)
                .toArray(Prefix[]::new);

        if (duplicatedPrefixes.length > 0) {
            throw new ParseException(Messages.getErrorMessageForDuplicatePrefixes(duplicatedPrefixes));
        }
    }
    //@@author

    //@@author yleeyilin
    /**
     * Checks that name prefix is not used more than once.
     * @return True if there is duplicate name prefix.
     */
    public boolean hasDuplicateNamePrefix() {
        Prefix[] duplicatedNamePrefixes = Stream.of(PREFIX_NAME).distinct()
                .filter(prefix -> argMultimap.containsKey(prefix) && argMultimap.get(prefix).size() > 1)
                .toArray(Prefix[]::new);

        boolean isNamePrefixDuplicated = duplicatedNamePrefixes.length > 0;
        if (isNamePrefixDuplicated) {
            return true;
        }
        return false;
    }
    //@@author

    public boolean containsPrefix(Prefix prefix) {
        return argMultimap.containsKey(prefix);
    }

    //@@author Joshy837
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ArgumentMultimap)) {
            return false;
        }

        ArgumentMultimap otherArgumentMultimap = (ArgumentMultimap) other;
        return argMultimap.equals(otherArgumentMultimap.argMultimap);
    }
    //@@author

    public boolean isPreambleEmpty() {
        return this.getPreamble().isEmpty();
    }

    //@@author Joshy837
    /**
     * Gets all the prefixes.
     * @return An array of prefixes in the hashmap.
     */
    public Prefix[] getAllPrefixes() {
        return argMultimap.keySet().toArray(new Prefix[0]);
    }
    //@@author

    //@@author Joshy837
    /**
     * Throws a {@code ParseException} if any of the prefixes given in {@code prefixes} appeared more than
     * once among the arguments.
     */
    public void verifyNoEmptyEntries() throws ParseException {
        List<Prefix> emptyPrefixes = new ArrayList<>();
        for (Prefix prefix : getAllPrefixes()) {
            if (!prefix.toString().isEmpty() && Objects.equals(argMultimap.get(prefix).get(0), "")) {
                emptyPrefixes.add(prefix);
            }
        }

        if (!emptyPrefixes.isEmpty()) {
            throw new ParseException(Messages.getErrorMessageForEmptyPrefixes(
                    emptyPrefixes.toArray(new Prefix[0])));
        }
    }
    //@@author

    /**
     * Returns a string implementation of Argument Multi Map.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ArgumentMultimap{");

        for (Map.Entry<Prefix, List<String>> entry : argMultimap.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
        }

        if (!argMultimap.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }
}
