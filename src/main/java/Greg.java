import java.util.Scanner;

/**
 * Runs the Greg chatbot greeting program.
 * Prints an ASCII logo of GREG and a short greeting
 * Prints an intro message, then repeatedly reads user input and echoes it back.
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

        // Greetings & Introduction
        System.out.println(line);
        System.out.println(logo);
        System.out.println(line);
        System.out.println(" GREETINGS! I'm " + chatbotName + " :)))");
        System.out.println(" How can I be of service to you today my good sir!");
        System.out.println(line);

        // Echo Commands
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();

            // Checks for "bye" to exit the while loop and output farewell message
            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
        }
        sc.close();
    }
}
