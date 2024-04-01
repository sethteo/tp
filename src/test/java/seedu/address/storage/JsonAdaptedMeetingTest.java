package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.JAMAL;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.testutil.MeetingBuilder;

public class JsonAdaptedMeetingTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = "";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = JAMAL.getName().toString();
    private static final String VALID_PHONE = JAMAL.getPhone().toString();
    private static final String VALID_EMAIL = JAMAL.getEmail().toString();
    private static final String VALID_ADDRESS = JAMAL.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = JAMAL.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedMeeting> VALID_MEETINGS = JAMAL.getMeetings().stream()
            .map(JsonAdaptedMeeting::new)
            .collect(Collectors.toList());

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
