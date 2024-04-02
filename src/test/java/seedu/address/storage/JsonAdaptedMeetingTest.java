package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.testutil.MeetingBuilder;

public class JsonAdaptedMeetingTest {

    @Test
    public void toModelType_validMeetingDetails_returnsMeeting() throws Exception {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(new MeetingBuilder().buildMeeting());
        assertEquals(new MeetingBuilder().buildMeeting(), meeting.toModelType());
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime parsedDateTime = LocalDateTime.parse(VALID_DATETIME, formatter);
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(null, parsedDateTime);
        String expectedMessage = String.format(
                JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT, "description");
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_emptyName_throwsIllegalValueException() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime parsedDateTime = LocalDateTime.parse(VALID_DATETIME, formatter);
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting("", parsedDateTime);
        String expectedMessage = String.format(
                JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT, "description");
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_blankName_throwsIllegalValueException() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime parsedDateTime = LocalDateTime.parse(VALID_DATETIME, formatter);
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting("  ", parsedDateTime);
        String expectedMessage = String.format(
                JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT, "description");
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullDateTime_throwsIllegalValueException() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime parsedDateTime = LocalDateTime.parse(VALID_DATETIME, formatter);
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting("test", null);
        String expectedMessage = String.format(
                JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT, "date time");
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

}
