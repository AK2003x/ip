package greg.exception;

/**
 * Represents exceptions specific to the Greg chatbot.
 * Used to handle domain-specific errors like empty descriptions or unknown commands.
 */
public class GregException extends Exception {

    /**
     * Constructs a new GregException with the specified detail message.
     *
     * @param message The specific error message to be shown to the user.
     */
    public GregException(String message) {
        super(message);
    }
}