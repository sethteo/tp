package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CLIENT_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLIENT_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.JAMAL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.MeetingBuilder;

public class EditMeetingCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMeetingCommand.MESSAGE_USAGE);
    private EditMeetingCommandParser parser = new EditMeetingCommandParser();

    @Test
    public void parse_missingClientIndex_failure() {
        // no clientIndex specified
        String testInputClientIndex = MEETING_INDEX + VALID_NAME_AMY + DATETIME;
        assertParseFailure(parser, testInputClientIndex, MESSAGE_INVALID_FORMAT);

        // no meetingIndex specified
        String testInputMeetingIndex = CLIENT_INDEX + VALID_NAME_AMY + DATETIME;
        assertParseFailure(parser, testInputMeetingIndex, MESSAGE_INVALID_FORMAT);

        // no datetime specified
        String testInputDateTime = CLIENT_INDEX + MEETING_INDEX + VALID_NAME_AMY;
        assertParseFailure(parser, testInputDateTime, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingMeetingIndex_failure() {
        // no meetingIndex specified
        String testInputMeetingIndex = CLIENT_INDEX + VALID_NAME_AMY + DATETIME;
        assertParseFailure(parser, testInputMeetingIndex, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingDateTime_failure() {
        // no datetime specified
        String testInputDateTime = CLIENT_INDEX + MEETING_INDEX + VALID_NAME_AMY;
        assertParseFailure(parser, testInputDateTime, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingDescription_failure() {
        // no description specified
        String testInputDescription = CLIENT_INDEX + MEETING_INDEX + DATETIME;
        assertParseFailure(parser, testInputDescription, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        String testInput = CLIENT_INDEX + MEETING_INDEX + VALID_NAME_AMY + DATETIME;
        // negative index
        assertParseFailure(parser, "-5" + testInput, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + testInput, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime parsedDateTime = LocalDateTime.parse(VALID_DATETIME, formatter);

        EditMeetingCommand expectedCommand = new EditMeetingCommand(
                Index.fromOneBased(Integer.parseInt(VALID_CLIENT_INDEX)),
                Index.fromOneBased(Integer.parseInt(VALID_MEETING_INDEX)),
                VALID_NAME_AMY, parsedDateTime);

        String userInput = "editMeeting clientIndex/1 meetingIndex/1 n/Amy Bee dt/01-01-2030 17:00";

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
