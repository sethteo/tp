package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewClientCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    public static final String VIEW_CLIENT_ARGUMENT = "c";

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        try {
            String[] index = getValidArgs(args);

            String viewType = index[0].trim();
            Index viewIndex = ParserUtil.parseIndex(index[1].trim());

            switch (viewType) {
            case VIEW_CLIENT_ARGUMENT:
                return new ViewClientCommand(viewIndex);
            default:
                return null;
            }

        } catch (ParseException pe) {
            throw pe;
        }
    }

    public String[] getValidArgs(String args) throws ParseException {
        String[] arguments = args.trim().split("\\s+");

        if (arguments.length < 2) {
            if (arguments[0].equals(VIEW_CLIENT_ARGUMENT)) {
                //Checks if view is followed by 'c'
                throw new ParseException("Error: No index detected!");
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
            }

        }
        if (arguments.length == 2) {
            if (!arguments[0].equals(VIEW_CLIENT_ARGUMENT)) {
                //Checks if view is followed by 'c'
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ViewClientCommand.MESSAGE_USAGE));
            }

            try {
                Index clientIndex = ParserUtil.parseIndex(arguments[1]);
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, arguments[1]));
            }
        }
        return arguments;
    }
}

