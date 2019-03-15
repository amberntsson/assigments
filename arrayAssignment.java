package com.example.arrayassignment;
/**
 * @author Anna Amberntsson
 * @version 1.0
 * @since 2019-02-08
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.lang.String;

public class arrayAssignment {
    public static int choice;
    public static int randNum;

    //Main-method containing two char-arrays, their length and a String. Calls allFunctions
    public static void main(String[] args)throws IOException  {
        Scanner startScan = new Scanner(System.in);

        System.out.println("Tryck 1 för att köra programmet manuellt eller tryck 2 om du vill du skriva in en siffra: ");
        choice = startScan.nextInt();
        if(choice==2){
            System.out.println("Vilken siffra vill du randomisera med? ");
            randNum= startScan.nextInt();

        }

        char[] charArray1 = {'s', 't', 'r', 'i', 'n', 'g'};
        char[] charArray2 = {'s', 'i', 'n', 'g'};
        int numChars1 = charArray1.length - 1;
        int numChars2 = charArray2.length - 1;
        String string="";

        allFunctions(charArray1, numChars1, charArray2, numChars2, string);
    }

    //Method containing the outprints for all methods in the program. Receives variables from main
    public static void allFunctions(char []allChars1, int numChars1, char[] allChars2, int numChars2, String string) throws IOException  {
        System.out.println("Originalarray 1" + Arrays.toString(allChars1) + "\nOriginalarray 2" + Arrays.toString(allChars2));
        System.out.println(detectTInArray(allChars1, numChars1, string));
        System.out.println(detectTInArray(allChars2, numChars2, string));
        System.out.println("Uppgift d)");
        compareCharArrays(removeDuplicates(allChars1, numChars1, string), removeDuplicates(allChars2, numChars2, string));
        System.out.println("Uppgift a)");
        System.out.println(changeToT(allChars1, numChars1) + "\n" + (changeToT(allChars2, numChars2)));
        System.out.println("Uppgift b)");
        System.out.println(toCapital(allChars1, numChars1) + "\n" + (toCapital(allChars2, numChars2)));
        System.out.println("Uppgift c) \n" + removeDuplicates(allChars1, numChars1, string) + "\n" + removeDuplicates(allChars2, numChars2, string));
        System.out.println("Uppgift e)");
        inputCharArray();
    }

    //Types true or false it the array contains a 't'
    public static boolean detectTInArray(char[] allChars, int numChars, String string) {
        if (numChars > 0) {
            if (allChars[numChars] == 't') {
                return true;
            }
            return detectTInArray(allChars, numChars - 1, string);
        }
        toCapital(allChars, numChars);
        removeDuplicates(allChars, numChars, string);
        return false;
    }

    //Loop the array to find 't' and change it to 'r'
    public static String changeToT(char[] allCharsA, int numChars) {

        if (numChars > 0) {
            if (allCharsA[numChars] == 'r') {
                allCharsA[numChars] = 't';
            }
            changeToT(allCharsA, numChars - 1);
        }
        return Arrays.toString(allCharsA);
    }

    //Loop the array to find 't' and change it to capital letter
    public static String toCapital(char[] allCharsB, int numChars) {
        if (numChars > 0) {
            if (allCharsB[numChars] == 't') {
                allCharsB[numChars] = Character.toUpperCase(allCharsB[numChars]);
            }
            toCapital(allCharsB, numChars - 1);
        }
        return Arrays.toString(allCharsB);
    }

    //Loop the array to search for a char
    private static String removeDuplicates(char[] allCharsC, int numChars, String string) {
        String stringArray=string;
        if (stringArray.indexOf(allCharsC[numChars]) == -1) {   //Checks for char
            stringArray += allCharsC[numChars];
            if (numChars-1 == -1) {                             //Breaks the recursion when the condition is true
                StringBuilder result= new StringBuilder();
                result.append(stringArray);                     //Attatch a string to stringbuilder
                result = result.reverse();
                String stringified = result.toString();         //convert to string

                return Arrays.toString(stringified.toCharArray());
            }
        }return removeDuplicates(allCharsC, numChars - 1, stringArray);     //recursion
    }

    //Compare two char arrays if identical
        public static void compareCharArrays(String firstChar, String secondChar){
        if(firstChar.equals(secondChar)){
                System.out.println("Array 1 och Array 2 är identiska");
            }System.out.println("Inte identiska\n" + firstChar + "\n" + secondChar);
        }


        //Receives input from user and add the three first chars in a char array
        //If input>=3 the program asks for new input and where to put it. If no new input is given, the program will break
        public static void inputCharArray() {

            if (choice == 2) {
                randomInput();
            } else {
                Scanner scan = new Scanner(System.in);
                char[] charArrayInput;
                int numChars = 3;
                int counter = 0;
                String newChar="";
                System.out.println("Skriv in input till den nya arrayn: ");
                String input = scan.next();

                if (input.length() >= numChars) {

                    String[] sendString = {};
                    charArrayInput = input.substring(0, 3).toCharArray();
                    System.out.println("Din input: " + Arrays.toString(charArrayInput));
                    do {
                        newChar = "";
                        System.out.println("Ny bokstav: ");
                        newChar = scan.next();

                        if (newChar.isEmpty() || newChar.length() > 1) {
                            return;
                        }
                        counter++;
                        sendString = inputStringArray(sendString, counter, newChar);

                        System.out.println("\nVilken position i arrayn? (0-2): ");
                        int position = scan.nextInt();
                        if (position == 0 || position == 1 || position == 2) {
                            addContent(charArrayInput, newChar, position);
                            System.out.println("Uppgift f) \n" + Arrays.toString(sendString));
                        }
                    }

                    while (newChar.length() > 0);

                } else if (input.length() < numChars || input.length() == 0) {
                    return;
                } else {
                    return;
                }
            }
        }

        //Add the new input on the right spot in the array and returns it
        public static char[] addContent( char[] charArray, String input, int position){
        char [] newArray = input.toCharArray();
        charArray[position]=newArray[0];
        System.out.println("\nNy e) \n" + Arrays.toString(charArray) + "\n");
        return charArray;
        }

        //All input given after the first input is saved in a string array
        public static String[] inputStringArray(String[] sendStringArray, int counter, String newChar) {
            String[] firstArray = sendStringArray;
            String[] secondArray = Arrays.copyOf(firstArray,counter);       //Gör kopia på den första arrayen med ökande antal platser
            secondArray[counter-1] = newChar;                               //Lägger in värdet på bokstaven i arrayen

            sendStringArray = secondArray;
            System.out.println("Uppgift g)");
            printAsString(sendStringArray, counter);                        //Kallar på metoden printAsString
            return sendStringArray;

        }

        //Type out all the given input in the terminal window
        public static String printAsString(String [] stringArray, int counter){

        if(counter>0){
            System.out.print(stringArray[counter-1]);
            printAsString(stringArray, counter-1);
        }

        return Arrays.toString(stringArray);
        }

        //If the user don´t want to give input, the program will randomize chars
        private static void randomInput(){
            char[] charArrayInput;
            int numChars =3;
            int counter =0;
            String newChar="";
            Random random = new Random();
            int position =0;

            char[] randomArray = new char[randNum];
            for(int i=0; i<randNum; i++){
                char genChar= (char) (random.nextInt(26) + 'a');
                randomArray[i]=genChar;
            }
            String[] sendString = {};
            if (randNum >= numChars) {

                charArrayInput = Arrays.copyOf(randomArray, numChars);
                System.out.println(Arrays.toString(randomArray));
                System.out.println("Din input: " + Arrays.toString(charArrayInput));


                for (int n = 3; n < randNum; n++) {
                    counter++;
                    newChar = Character.toString(randomArray[n]);

                    sendString = inputStringArray(sendString, counter, newChar);
                    addContent(charArrayInput, newChar, position);
                }

                System.out.println("Uppgift f) \n" + Arrays.toString(sendString));
            }

          else{
                return;
            }
    }
}



