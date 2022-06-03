import java.util.Scanner;

public class RleProgram {
    public static String toHexString(byte[] data) {
        String hexdec = "";
        for (byte one : data) {
            // if it's a digit, change to string
            if(one < 10)
                hexdec = hexdec + one;
            // convert it to a char and then change to string
            else
                hexdec = hexdec + (char)('a' + one - 10);
        }
        return hexdec;
    }
    public static int countRuns(byte[] flatData) {
        // initialize count and repeat values to be 1
        int counter = 1;
        int repValue = 1;
        for(int i = 0; i < flatData.length - 1; i++) {
            if(flatData[i] != flatData[i + 1])
                counter++;
            else {
                repValue++;
                if(repValue > 15) {
                    counter++;
                    repValue = 1;
                }
            }
        }
        return counter;
    }
    public static byte[] encodeRle(byte[] flatData) {
        byte counter = 1;
        byte groupValue = 1;
        for(int i = 0; i < flatData.length - 1; i++) {
            // counts if the numbers are within the same group
            if (flatData[i] == flatData[i + 1])
                counter++;
            // if the numbers stop being consecutive, the group changes
            else {
                groupValue++;
                counter = 1;
            }
            if (counter >= 15) {
                groupValue++;
                counter = 1;
            }
        }
        // creating array based on how many groups are in the previous array
        byte[] arr = new byte[groupValue * 2];
        counter = 1;
        byte numValue = flatData[0];
        int value = 0;
        for(int i = 0; i < flatData.length - 1; i++) {
            // counts if the numbers are within the same group
            if(flatData[i] == flatData[i + 1]) {
                counter++;
                numValue = flatData[i];
                // if there are more than 15 in one group, add 2 more values to the new array and reset the count
                if(counter >= 15) {
                    arr[value] = counter;
                    value++;
                    arr[value] = numValue;
                    value++;
                    counter = 0;
                }
            }
            // add 2 more values to the new array and reset the count
            else {
                numValue = flatData[i];
                arr[value] = counter;
                value++;
                arr[value] = numValue;
                value++;
                counter = 1;
            }
        }
        // adding the final 2
        numValue = flatData[flatData.length - 1];
        arr[arr.length - 2] = counter;
        arr[arr.length - 1] = numValue;
        return arr;
    }
    public static int getDecodedLength(byte[]rleData) {
        // initialize the length variable
        int len = 0;
        for(int i = 0; i < rleData.length; i += 2) {
            len += rleData[i];
        }
        return len;
    }
    public static byte[] decodeRle(byte[] rleData) {
        // initialize the length variable to store and initialize the array
        int length = getDecodedLength(rleData);
        byte[] array = new byte[length];
        int index = 0;
        for(int i = 0; i < rleData.length; i += 2) {
            for(int j = 0; j < rleData[i]; j++)
                array[index++] = rleData[i + 1];
        }
        return array;
    }
    public static byte[] stringToData(String dataString) {
        // initialize byte array to store
        byte[] arr = new byte[dataString.length()];
        for(int i = 0; i < dataString.length(); i++) {
            // if the character is a digit, minus '0' to find
            if(Character.isDigit(dataString.charAt(i)))
                arr[i] = (byte) (dataString.charAt(i) - '0');
            // else, convert the character to a byte
            else
                arr[i] = (byte) (Character.toLowerCase(dataString.charAt(i)) -'a' + 10);
        }
        return arr;
    }
    public static String toRleString(byte[] rleData) {
        String res = "";
        for(int i = 0; i < rleData.length; i += 2) {
            res += rleData[i];
            if(rleData[i + 1] >= 0 && rleData[i + 1] <= 9)
                res += (char) ('0' + rleData[i + 1]);
            else
                res += (char) ('a' + (rleData[i + 1] - 10));
            if(i != rleData.length - 2)
                res += ":";
        }
        return res;
    }
    public static byte[] stringToRle(String rleString) {
        String[] val = rleString.split(":");
        byte[] res = new byte[2 * val.length];
        char character;
        for(int i = 0; i < val.length; i++) {
            res[2 * i] = Byte.parseByte(val[i].substring(0, val[i].length() - 1));
            character = val[i].charAt(val[i].length() - 1);
            if(character <= '9')
                res[2 * i + 1] = (byte) (character - '0');
            else
                res[2 * i + 1] = (byte) (10 + (character - 'a'));
        }
        return res;
    }

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
        }
    }
}
