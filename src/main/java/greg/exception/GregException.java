package greg.exception;

/**
 * Represents exceptions specific to the greg.Greg chatbot.
 * Used to handle errors like empty descriptions or unknown commands.
 */
public class GregException extends Exception {
    /**
     * Constructor for greg.exception.GregException.
     * * @param message The specific error message to be shown to the user.
     */
    public GregException(String message) {
        super(message);
    }
}