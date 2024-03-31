package seedu.address.logic.messages;

/**
 * Container for user help command visible messages.
 */
public class HelpMessages extends Messages {
    public static final String MESSAGES_SHOWING_HELP_MESSAGE = "Opened help window.";
    public static final String MESSAGES_SHOWING_DELETE_HELP_MESSAGE = "Opened help window for delete command.";
    public static final String MESSAGES_SHOWING_EDIT_HELP_MESSAGE = "Opened help window for edit command.";
    public static final String MESSAGES_SHOWING_ADD_HELP_MESSAGE =
            "Opened help window for add command.";
    public static final String MESSAGES_SHOWING_SEARCH_HELP_MESSAGE =
            "Opened help window for search command.";
    public static final String MESSAGES_INVALID_COMMAND_TYPE = "Invalid command type given.";

    public static final String MESSAGE_HELP_MISSING_COMMAND = "Failed to give help - "
            + "Delete requires a command field. \uD83D\uDC3E";

    public static final String USERGUIDE_URL = "https://ay2324s2-cs2103t-w10-2.github.io/tp/UserGuide.html";

    public static final String DISPLAYED_DELETE_MESSAGE = "Deletes the specified person from the Pooch Planner"
            + "\n" + "" + "\n"
            + "Format: /delete ; name : [name] "
            + "\n" + "" + "\n"
            + "Go to our UG for more information : " + USERGUIDE_URL;

    public static final String DISPLAYED_ADD_MESSAGE = "Adds a other/maintainer/supplier/staff person to pooch planner"
            + "\n" + "" + "\n"
            + "Format:" + "\n"
            + "/pooch-add ; name : [name] ; phone : [phone] ; address : [address] ; email : [email]" + "\n"
            + "/pooch-maintainer ; name : [name] ; phone : [phone] ; address : [address] ;"
            + " email : [email] ; skill : [skill] ; commission : [commission/hr]" + "\n"
            + "/pooch-supplier ; name : [name] ; phone : [phone] ; address : [address] ;"
            + " email : [email] ; product : [product] ; price : [price/(quantity)]" + "\n"
            + "/pooch-staff ; name : [name] ; phone : [phone] ; address : [address] ;"
            + " email : [email] ; salary : [salary/hr]  ; employment : [part/full]" + "\n"
            + "\n" + "" + "\n"
            + "Go to our UG for more information : " + USERGUIDE_URL;

    public static final String DISPLAYED_EDIT_MESSAGE = "Edit the fields of the specified person in the Pooch Planner"
            + "\n" + "" + "\n"
            + "Format:" + "\n"
            + "/edit-person ; name : [name] ; field { [field] : [value] }" + "\n"
            + "/edit-staff ; name : [name] ; field { [field] : [value] }" + "\n"
            + "/edit-supplier ; name : [name] ; field { [field] : [value] }" + "\n"
            + "/edit-maintainer ; name : [name] ; field { [field] : [value] }" + "\n"
            + "\n" + "" + "\n"
            + "Go to our UG for more information : " + USERGUIDE_URL;


    public static final String DISPLAYED_SEARCH_MESSAGE = "Searches through the address book using"
            + " specified fields and keyword"
            + "\n" + "" + "\n"
            + "Formats:" + "\n"
            + "/search ; name : [full/partial name]" + "\n"
            + "/search ; phone : [full/partial phone]" + "\n"
            + "/search ; address : [full/partial address]" + "\n"
            + "/search ; email : [full/partial email]" + "\n"
            + "/search ; product : [full/partial product name]" + "\n"
            + "/search ; employment : [employment]" + "\n"
            + "\n" + "" + "\n"
            + "Go to our UG for more information : " + USERGUIDE_URL;

}
