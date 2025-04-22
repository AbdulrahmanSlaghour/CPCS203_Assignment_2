/*
   Name : Abdulrahman Slaghour
   University ID : 2435931
   Section : CS1
   Assignment number : #2
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Test_FoodDeliverySystem {
    public static void main(String[] args) throws FileNotFoundException {
        //Create FoodDeliverySystem object to test the system's implementation
        FoodDeliverySystem foodDeliverySystem = new FoodDeliverySystem();

        //both input.txt and output.txt have been provided by the faculty to test the complete system's
        //capabilites, so initialize both files into File objects with relative paths and check whether
        //they exist in a simple if statement. Try-catch exception handling shouldn't be necessary since we
        //haven't oppened any files or ran any important processes that are sensitive to abrupt crashes.

        //input.txt's relative path
        File inputFile = new File("input.txt");

        //output.txt's relative path
        File outputFile = new File("output.txt");
        
        //if either inputFile or outputFile do not exist execute if-statement's code
        if (!inputFile.exists() || !outputFile.exists()) {
            System.out.print("Error encountered: ");

            //ternary operator used to minimize code duplication. If a file doesn't exist it will be added to
            //error message instead of making multiple if statements and error messages for multiple possiblities.
            System.out.println((!inputFile.exists() ? "\"input.txt\" " : "") + (!outputFile.exists() ? "\"output.txt\" " : "") + "doesn't exist.");

            //Since there aren't any important processes open when the error is encountered, exiting the program
            //should be safe.
            System.exit(0);
        }
        
        // create a Scanner that utilizes inputFile to later read from it
        Scanner inputFileScanner = new Scanner(inputFile);

        // create a PrintWriter that utilizes outputFile to later write to
        PrintWriter outputPrintWriter = new PrintWriter(outputFile);

        //the following variables are initialized here to avoid creating different variables for each command
        //and using simpler names that are shared across the switch case's syntax.
        int delivererID;
        Deliverable intendedDeliverer;


        //while loop to parse through input.txt content. The while loop will keep going as long as there
        //are unread lines.
        while (inputFileScanner.hasNext()) {

            //since FoodDeliverySystem works via a command system, we will parse each line and direct the
            //FoodDeliverySystem according to the given command.

            //inputCommand is split for each "," since documentation has specified this as command format
            String[] inputCommand = inputFileScanner.nextLine().split(",");

            //input inputCommand[0] to find appropriate action
            switch (inputCommand[0]) {
                //command order is identical to documentation order
                case "addRegularCustomer":
                    //input parameters according to command order as per documentation, parsing into the
                    //correct type when necessary.
                    String addRegularCustomerMessage = foodDeliverySystem.addRegularCustomer(Integer.parseInt(inputCommand[1]), inputCommand[2], inputCommand[3]);
                    
                    //save returned value in output.txt through outputPrintWriter
                    outputPrintWriter.println(addRegularCustomerMessage);
                    break;

                case "addGoldenCustomer":
                    //input parameters according to command order as per documentation, parsing into the
                    //correct type when necessary.
                    String addGoldenCustomerMessage = foodDeliverySystem.addGoldenCustomer(Integer.parseInt(inputCommand[1]), inputCommand[2], inputCommand[3], Double.parseDouble(inputCommand[4]), Double.parseDouble(inputCommand[5]));
                    
                    //save returned value in output.txt through outputPrintWriter
                    outputPrintWriter.println(addGoldenCustomerMessage);
                    break;

                case "addDriver":
                    //input parameters according to command order as per documentation, parsing into the
                    //correct type when necessary.
                    String addDriverMessage = foodDeliverySystem.addDriver(Integer.parseInt(inputCommand[1]), inputCommand[2], inputCommand[3], Integer.parseInt(inputCommand[4]), inputCommand[5]);
                    
                    //save returned value in output.txt through outputPrintWriter
                    outputPrintWriter.println(addDriverMessage);
                    break;

                case "addDrone":
                    //input parameters according to command order as per documentation, parsing into the
                    //correct type when necessary.
                    String addDroneMessage = foodDeliverySystem.addDrone(Integer.parseInt(inputCommand[1]), Double.parseDouble(inputCommand[2]), Integer.parseInt(inputCommand[3]));
                    
                    //save returned value in output.txt through outputPrintWriter
                    outputPrintWriter.println(addDroneMessage);
                    break;

                case "addRestaurant":
                    //input parameters according to command order as per documentation, parsing into the
                    //correct type when necessary.
                    String addRestaurantMessage = foodDeliverySystem.addRestaurant(Integer.parseInt(inputCommand[1]), inputCommand[2], inputCommand[3]);
                    
                    //save returned value in output.txt through outputPrintWriter
                    outputPrintWriter.println(addRestaurantMessage);
                    break;

                case "addMenuItem":
                    //Since this command can has multiple menu items but FoodDeliverySystem.addMenuItem() can only
                    //handle a single item, a for loop is needed to call the method multiple times for each item passed.
                    
                    //inputCommand[2] contains all menu items in a single String seperated by "#", but a String[] is
                    //needed to parse through it using a for loop.
                    String[] menuItems = inputCommand[2].split("#");

                    //save inputCommand[2] in restaurantID so that Integer.parseInt() is only used once.
                    int restaurantID = Integer.parseInt(inputCommand[1]);

                    for (String menuItem : menuItems) {
                        String addMenuItemMessage = foodDeliverySystem.addMenuItem(restaurantID, menuItem);

                        //save returned value in output.txt through outputPrintWriter
                        outputPrintWriter.println(addMenuItemMessage);
                    }
                    break;

                case "createOrder":
                    //FoodDeliverySystem.createOrder() needs the Customer object, not the customerId given in the inputCommand[1],
                    //so retrieve the Customer using .getCustomerById() while converting the customerId into int using parseInt().
                    Customer intendedCustomer = foodDeliverySystem.getCustomerById(Integer.parseInt(inputCommand[1]));

                    //input parameters according to command order as per documentation, parsing into the
                    //correct type when necessary.
                    String createOrderMessage = foodDeliverySystem.createOrder(intendedCustomer, Integer.parseInt(inputCommand[2]), inputCommand[3].split("#"), Double.parseDouble(inputCommand[4]));

                    //save returned value in output.txt through outputPrintWriter
                    outputPrintWriter.println(createOrderMessage);
                    break;

                case "assignDeliverer":
                    //FoodDeliverySystem.assignDeliverer() needs a Deliverable deliverer to be passed through the parameters, not the
                    //provided delivererId in the command format. So retrieval of the deliverer through getDelivererById() is necessary.
                    delivererID = Integer.parseInt(inputCommand[2]);
                    intendedDeliverer = foodDeliverySystem.getDelivererById(delivererID);

                    //save commandInput values into appropriately named variables for increased readabilty
                    int orderID = Integer.parseInt(inputCommand[1]);

                    //input parameters according to command order as per documentation
                    String assignDelivererMessage = foodDeliverySystem.assignDeliverer(orderID, intendedDeliverer);

                    //save returned value in output.txt through outputPrintWriter
                    outputPrintWriter.println(assignDelivererMessage);
                    break;

                case "printOrdersByDeliverer":
                    //save commandInput values into appropriately named variables for increased readabilty
                    delivererID = Integer.parseInt(inputCommand[1]);

                    //FoodDeliverySystem.printOrdersByDeliverer() needs a Deliverable deliverer to be passed through the parameters, not the
                    //provided delivererId in the command format. So retrieval of the deliverer through getDelivererById() is necessary.
                    intendedDeliverer = foodDeliverySystem.getDelivererById(delivererID);

                    //input parameters according to command order as per documentation
                    String printOrdersByDelivererMessage = foodDeliverySystem.printOrdersByDeliverer(intendedDeliverer);

                    //save returned value in output.txt through outputPrintWriter
                    outputPrintWriter.println(printOrdersByDelivererMessage);
                    break;

                case "getTotalCostByDelivererId":
                    //save commandInput values into appropriately named variables for increased readabilty
                    delivererID = Integer.parseInt(inputCommand[1]);

                    //even though the documentation's name for the method is without "Id" at the end,
                    //input parameters according to command order as per documentation
                    String getTotalCostByDelivererIdMessage = foodDeliverySystem.getTotalCostByDeliverer(delivererID);

                    //save returned value in output.txt through outputPrintWriter
                    outputPrintWriter.println(getTotalCostByDelivererIdMessage);
                    break;

                case "printOrdersByCustomer":
                    //save commandInput values into appropriately named variables for increased readabilty
                    int customerID = Integer.parseInt(inputCommand[1]);

                    //input parameters according to command order as per documentation
                    String printOrdersByCustomerMessage = foodDeliverySystem.printOrdersByCustomer(customerID);

                    //save returned value in output.txt through outputPrintWriter
                    outputPrintWriter.println(printOrdersByCustomerMessage);
                    break;
                    //default case to catch any errors in command input
                default:
                    outputPrintWriter.println("Invalid Command: " + inputCommand[0]);
                    break;
            }

            //flush what is in outputPrintWriter after each command in case of sudden program termination
            outputPrintWriter.flush();
        }

        //at this point there are no more commands from the input, so close inputFileScanner to prevent resource leak
        inputFileScanner.close();

        //close outputPrintWriter to make sure all data is flushed and prevent resource leak
        outputPrintWriter.close();
    }
}
