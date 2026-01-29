/**
 * Runs the Greg chatbot greeting program.
 * Prints an ASCII logo and a short greeting and farewell to standard output.
 */
public class Greg {
    /**
     * Starts the program and prints the greeting sequence.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        // Temporary intro logo
        String chatbotName = "Greg";
        String line = "--------------------------------------------";
        String logo =
                "  ____   ____   _____   ____  \n"
                        + " / ___| |  _ \\ | ____| / ___| \n"
                        + "| |  _  | |_) ||  _|  | |  _  \n"
                        + "| |_| | |  _ < | |___ | |_| | \n"
                        + " \\____| |_| \\_\\|_____| \\____| \n";
        // Print outputs
        System.out.println(line);
        System.out.println(logo);
        System.out.println(line);
        System.out.println(" Hello! I'm " + chatbotName);
        System.out.println(" What can I do for you?");
        System.out.println(line);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
