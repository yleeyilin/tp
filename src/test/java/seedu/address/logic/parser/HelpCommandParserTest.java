package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.messages.Messages.MESSAGE_COMMAND_FORMAT;
import static seedu.address.logic.messages.Messages.MESSAGE_MISSING_FIELD_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HELP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HelpCommand;

public class HelpCommandParserTest {

    private HelpCommandParser parser = new HelpCommandParser();
    private String validCommand = "delete";
    private String invalidCommand = "poodle";
    public final String validHelpDesc = " " + PREFIX_HELP + validCommand;
    public final String invalidCommandHelpDesc = " " + PREFIX_HELP + invalidCommand;

    @Test
    public void parse_validArgs_returnsNoteCommand() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + validHelpDesc,
                new HelpCommand(validCommand));
    }

    @Test
    public void parse_invalidCommand_throwsParseException() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + invalidCommandHelpDesc,
                String.format(HelpCommand.MESSAGE_CONSTRAINTS, HelpCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingCommand_throwsParseException() {
        ArrayList<String> undetectedFields = new ArrayList<>();
        undetectedFields.add("command");
        String exception = String.format(MESSAGE_MISSING_FIELD_FORMAT, undetectedFields);
        String expectedMessage = exception + "\n"
                + String.format(MESSAGE_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE);
        assertParseFailure(parser, PREAMBLE_WHITESPACE + "/help ; command :",
                expectedMessage);
    }
}
