package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.Meeting;

/**
 * Jackson-friendly version of {@link Meeting}.
 */
class JsonAdaptedMeeting {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting's %s field is missing!";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private final LocalDateTime dateTime;
    private final String description;

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given meeting details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("description") String description, @JsonProperty("dateTime")
            LocalDateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        this.description = source.getDescription();
        this.dateTime = source.getDateTime();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Meeting toModelType() throws IllegalValueException {
        // checks whether the given description is empty or blank
        if (this.description == null || this.description.isEmpty() || this.description.isBlank()) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, "description"));
        }

        // Jackson's Object Mapper allows LocalDateTime to be formatted to ISO 8601 Format
        if (this.dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "date time"));
        }


        // conversion to standard format from ISO 8601
        String formattedDateTimeString = this.dateTime.format(formatter);

        // formatting the formatted DateTime string into a DateTime instance form
        LocalDateTime reformattedDateTime = LocalDateTime.parse(formattedDateTimeString, formatter);


        return new Meeting(this.description, reformattedDateTime);
    }

}
