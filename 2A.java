import java.util.Scanner;
public class RLEProgram {
    public static void main(String[] args) {
        //001. instantiate a Scanner object
        Scanner scanner = new Scanner(System.in);

        //0. Define all variables

        int selection = 1;
        String sfilename = "";
        byte[] imageData = null;

        //1. Display Welcome message

        System.out.println("Welcome to the RLE image encoder:");
        System.out.println("");

        //2. Display Color test

        System.out.println("Displaying Spectrum Image:");
        ConsoleGfx.displayImage(ConsoleGfx.testRainbow);

        //3. Display the menu
        while  (selection != 0)  {
            System.out.println("");
            System.out.println("RLE Menu ---------------");
            System.out.println("0. Exit Program \n1. Load File \n2. Load Test Image \n3. Read RLE String \n4. Read RLE Hex String \n5. Read Data Hex String \n6. Display Image \n7. Display RLE String \n8. Display Hex RLE Data \n9. Display Hex Flat Data");
            System.out.println("Select a Menu Option:");
            selection = scanner.nextInt();
            if (selection == 0) {
                System.out.println("Thanks for using RLE image encoder. Goodbye!");
            }
            //menu selection
            switch (selection) {
                // 3-0. Exit Program
                case 0:
                    return;
                // 3-1. Load File
                case 1:
                    System.out.printf("Enter name of file to load : ");
                    sfilename = scanner.next();
                    imageData = ConsoleGfx.loadFile(sfilename);
                    break;

                // 3-2. Load Test Image
                case 2:
                    imageData = ConsoleGfx.testImage;
                    System.out.println("Test image data loaded.");

                    break;

                // 3-3-5. Just print out what needs to be done but no actual action take place
                case 3:
                    System.out.println("Enter an RLE string to be decoded: ");
                    String RLEstring = scanner.next();
                    break;
                case 4:
                    System.out.println("Enter the hex string holding RLE data:");
                    String hexstring = scanner.next();
                    break;
                case 5:
                    System.out.println("Enter the hex string holding flat data: ");
                    String hexstring1 = scanner.next();
                    break;
                // 3-6. Display images stored inside of imageData
                case 6:
                    ConsoleGfx.displayImage(imageData);
                    break;
                // 3-7-9. Just print out what needs to be done but no actual action take place
                case 7:
                    System.out.println("RLE representation:");
                    String RLErepresentation = scanner.next();
                    break;
                case 8:
                    System.out.println("RLE hex values: ");
                    String RLEhexvalues = scanner.next();
                    break;
                case 9:
                    System.out.println("Flat hex values: ");
                    String flathexvalues = scanner.next();
                    break;
                // default option
                default:
                    break;
            }
        } // end While
    }
}
