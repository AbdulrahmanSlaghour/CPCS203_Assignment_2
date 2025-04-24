/*
   Name : Abdulrahman Slaghour
   Assignment number : #2
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Test_FoodDeliverySystem {
    public static void main(String[] args) throws FileNotFoundException {
        // Create FoodDeliverySystem object to test the system's implementation
        FoodDeliverySystem foodDeliverySystem = new FoodDeliverySystem();

        // both input.txt and output.txt have been provided by the faculty to test the
        // complete system's
        // capabilites, so initialize both files into File objects with relative paths
        // and check whether
        // they exist in a simple if statement. Try-catch exception handling shouldn't
        // be necessary since we
        // haven't oppened any files or ran any important processes that are sensitive
        // to abrupt crashes.

        // input.txt's relative path
        File inputFile = new File("input.txt");

        // output.txt's relative path
        File outputFile = new File("output.txt");

        // if either inputFile or outputFile do not exist execute if-statement's code
        if (!inputFile.exists() || !outputFile.exists()) {
            System.out.print("Error encountered: ");

            // ternary operator used to minimize code duplication. If a file doesn't exist
            // it will be added to
            // error message instead of making multiple if statements and error messages for
            // multiple possiblities.
            System.out.println((!inputFile.exists() ? "\"input.txt\" " : "")
                    + (!outputFile.exists() ? "\"output.txt\" " : "") + "doesn't exist.");

            // Since there aren't any important processes open when the error is
            // encountered, exiting the program
            // should be safe.
            System.exit(0);
        }

        // create a Scanner that utilizes inputFile to later read from it
        Scanner inputFileScanner = new Scanner(inputFile);

        // create a PrintWriter that utilizes outputFile to later write to
        PrintWriter outputPrintWriter = new PrintWriter(outputFile);

        // the following variables are initialized here to avoid creating different
        // variables for each command
        // and using simpler names that are shared across the switch case's syntax.
        int delivererID;
        Deliverable intendedDeliverer;
        int restaurantID;
        int customerID;

        // while loop to parse through input.txt content. The while loop will keep going
        // as long as there
        // are unread lines.
        while (inputFileScanner.hasNext()) {

            // since FoodDeliverySystem works via a command system, we will parse each line
            // and direct the
            // FoodDeliverySystem according to the given command.

            // inputCommand is split for each "," since documentation has specified this as
            // command format
            String[] inputCommand = inputFileScanner.nextLine().split(",");

            // input inputCommand[0] to find appropriate action
            switch (inputCommand[0]) {
                // command order is identical to documentation order appearance
                case "addRegularCustomer":
                    // save commandInput values into appropriately named variables for increased
                    // readabilty.
                    int regularCustomerID = Integer.parseInt(inputCommand[1]);
                    String regularCustomerName = inputCommand[2];
                    String regularCustomerPhoneNumber = inputCommand[3];

                    // input parameters according to command order as per documentation
                    String addRegularCustomerMessage = foodDeliverySystem
                            .addRegularCustomer(regularCustomerID, regularCustomerName, regularCustomerPhoneNumber);

                    // save returned value in output.txt through outputPrintWriter
                    outputPrintWriter.println(addRegularCustomerMessage);
                    break;

                case "addGoldenCustomer":
                    // save commandInput values into appropriately named variables for increased
                    // readabilty.
                    int goldenCustomerID = Integer.parseInt(inputCommand[1]);
                    String goldenCustomerName = inputCommand[2];
                    String goldenCustomerPhoneNumber = inputCommand[3];
                    double monthlyFee = Double.parseDouble(inputCommand[4]);
                    double discountRate = Double.parseDouble(inputCommand[5]);

                    // input parameters according to command order as per documentation
                    String addGoldenCustomerMessage = foodDeliverySystem.addGoldenCustomer(goldenCustomerID
                            , goldenCustomerName, goldenCustomerPhoneNumber,
                            monthlyFee, discountRate);

                    // save returned value in output.txt through outputPrintWriter
                    outputPrintWriter.println(addGoldenCustomerMessage);
                    break;

                case "addDriver":
                    // save commandInput values into appropriately named variables for increased
                    // readabilty.
                    int personID = Integer.parseInt(inputCommand[1]);
                    String driverName = inputCommand[2];
                    String driverPhoneNumber = inputCommand[3];
                    int driverID = Integer.parseInt(inputCommand[4]);
                    String vehicleType = inputCommand[5];

                    // input parameters according to command order as per documentation
                    String addDriverMessage = foodDeliverySystem.addDriver(personID,
                            driverName, driverPhoneNumber, driverID, vehicleType);

                    // save returned value in output.txt through outputPrintWriter
                    outputPrintWriter.println(addDriverMessage);
                    break;

                case "addDrone":
                    // save commandInput values into appropriately named variables for increased
                    // readabilty.
                    int droneID = Integer.parseInt(inputCommand[1]);
                    double maxPayload = Double.parseDouble(inputCommand[2]);
                    int batteryLevel = Integer.parseInt(inputCommand[3]);

                    // input parameters according to command order as per documentation
                    String addDroneMessage = foodDeliverySystem.addDrone(droneID,
                            maxPayload, batteryLevel);

                    // save returned value in output.txt through outputPrintWriter
                    outputPrintWriter.println(addDroneMessage);
                    break;

                case "addRestaurant":
                    // save commandInput values into appropriately named variables for increased
                    // readabilty.
                    restaurantID = Integer.parseInt(inputCommand[1]);
                    String restaurantName = inputCommand[2];
                    String restaurantAddress = inputCommand[3];

                    // input parameters according to command order as per documentation
                    String addRestaurantMessage = foodDeliverySystem.addRestaurant(restaurantID,
                            restaurantName, restaurantAddress);

                    // save returned value in output.txt through outputPrintWriter
                    outputPrintWriter.println(addRestaurantMessage);
                    break;

                case "addMenuItem":
                    // save commandInput values into appropriately named variables for increased
                    // readabilty.
                    restaurantID = Integer.parseInt(inputCommand[1]);

                    // Since addMenuItem command comes with multiple menu items but
                    // FoodDeliverySystem.addMenuItem() can only
                    // handle a single item, a for loop is needed to call the method multiple times
                    // for each item passed.

                    // inputCommand[2] contains all menu items in a single String seperated by "#",
                    // but a String[] is
                    // needed to parse through it using a for loop.
                    String[] menuItems = inputCommand[2].split("#");

                    

                    for (String menuItem : menuItems) {
                        String addMenuItemMessage = foodDeliverySystem.addMenuItem(restaurantID, menuItem);

                        // save returned value in output.txt through outputPrintWriter
                        outputPrintWriter.println(addMenuItemMessage);
                    }
                    break;

                case "createOrder":
                    // save commandInput values into appropriately named variables for increased
                    // readabilty.
                    customerID = Integer.parseInt(inputCommand[1]);
                    restaurantID = Integer.parseInt(inputCommand[2]);
                    String[] items = inputCommand[3].split("#");
                    double deliveryCost = Double.parseDouble(inputCommand[4]);

                    // FoodDeliverySystem.createOrder() needs the Customer object, not the
                    // customerId given in the inputCommand[1],
                    // so retrieve the Customer using .getCustomerById() while converting the
                    // customerId into int using parseInt().
                    Customer intendedCustomer = foodDeliverySystem.getCustomerById(customerID);

                    // input parameters according to command order as per documentation
                    String createOrderMessage = foodDeliverySystem.createOrder(intendedCustomer,
                            restaurantID, items,
                            deliveryCost);

                    // save returned value in output.txt through outputPrintWriter
                    outputPrintWriter.println(createOrderMessage);
                    break;

                case "assignDeliverer":
                    // save commandInput values into appropriately named variables for increased
                    // readabilty
                    int orderID = Integer.parseInt(inputCommand[1]);
                    
                    delivererID = Integer.parseInt(inputCommand[2]);

                    // FoodDeliverySystem.assignDeliverer() needs a Deliverable deliverer to be
                    // passed through the parameters, not the
                    // provided delivererId in the command format. So retrieval of the deliverer
                    // through getDelivererById() is necessary.
                    intendedDeliverer = foodDeliverySystem.getDelivererById(delivererID);

                    // input parameters according to command order as per documentation
                    String assignDelivererMessage = foodDeliverySystem.assignDeliverer(orderID, intendedDeliverer);

                    // save returned value in output.txt through outputPrintWriter
                    outputPrintWriter.println(assignDelivererMessage);
                    break;

                case "printOrdersByDeliverer":
                    // save commandInput values into appropriately named variables for increased
                    // readabilty
                    delivererID = Integer.parseInt(inputCommand[1]);

                    // FoodDeliverySystem.printOrdersByDeliverer() needs a Deliverable deliverer to
                    // be passed through the parameters, not the
                    // provided delivererId in the command format. So retrieval of the deliverer
                    // through getDelivererById() is necessary.
                    intendedDeliverer = foodDeliverySystem.getDelivererById(delivererID);

                    // input parameters according to command order as per documentation
                    String printOrdersByDelivererMessage = foodDeliverySystem.printOrdersByDeliverer(intendedDeliverer);

                    // save returned value in output.txt through outputPrintWriter
                    outputPrintWriter.println(printOrdersByDelivererMessage);
                    break;

                case "getTotalCostByDelivererId":
                    // save commandInput values into appropriately named variables for increased
                    // readabilty
                    delivererID = Integer.parseInt(inputCommand[1]);

                    // even though the documentation's name for the method is without "Id" at the
                    // end,
                    // input parameters according to command order as per documentation
                    String getTotalCostByDelivererIdMessage = foodDeliverySystem.getTotalCostByDeliverer(delivererID);

                    // save returned value in output.txt through outputPrintWriter
                    outputPrintWriter.println(getTotalCostByDelivererIdMessage);
                    break;

                case "printOrdersByCustomer":
                    // save commandInput values into appropriately named variables for increased
                    // readabilty
                    customerID = Integer.parseInt(inputCommand[1]);

                    // input parameters according to command order as per documentation
                    String printOrdersByCustomerMessage = foodDeliverySystem.printOrdersByCustomer(customerID);

                    // save returned value in output.txt through outputPrintWriter
                    outputPrintWriter.println(printOrdersByCustomerMessage);
                    break;
                // default case to catch any errors in command input
                default:
                    outputPrintWriter.println("Invalid Command: " + inputCommand[0]);
                    break;
            }

            // flush what is in outputPrintWriter after each command in case of sudden
            // program termination
            outputPrintWriter.flush();
        }

        // at this point there are no more commands from the input, so close
        // inputFileScanner to prevent resource leak
        inputFileScanner.close();

        // close outputPrintWriter to make sure all data is flushed and prevent resource
        // leak
        outputPrintWriter.close();
    }
}


