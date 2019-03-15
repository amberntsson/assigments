package com.example.slutuppgift;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;


public class FibonacciAssignment {

    private static ArrayLists lists = new ArrayLists();

    //Main method, loop the menu
    public static void main(String[] args) {
        while (!menu()) {
        }
    }

    //Method that calls the outprint for the menu and the right method depending on the selected choice
    private static boolean menu() {
        showMenu();
        switch (scannerCheck(scanner().nextLine())) {
            case 1:
                // Add
                System.out.println("Add your text: ");
                add(scanner().nextLine());
                break;
            case 2:
                // Search
                System.out.println("What you looking for?");
                search(scanner().nextLine());
                break;
            case 3:
                // Edit
                System.out.println("What do you want to change?");
                edit(scanner().nextLine());
                break;
            case 4:
                // Remove
                System.out.println("What do you want to remove?");
                remove(scanner().nextLine());
                break;
            case 5:
                //Sort the list
                sort();
                break;
            case 6:
                //Print number of words in the list
                wordFunction(0);
                System.out.println("There are " + lists.getSum() + " words in the list");
                break;
            case 7:
                //reverse the list
                System.out.println(reverseRecursion(lists.getList()));
                break;
            case 8:
                //Encrypt the string array
                System.out.println("What do you want to encrypt?");
                System.out.println(cryptoFunction(scanner().nextLine()));
                break;
            case 0:
                //Exit
                return true;
            default:
                System.out.println("Incorrect input");
                break;
        }
        return false;
    }

    //Print the menu
    private static void showMenu() {
        System.out.println("\nMenu\n" +
                "1 - Add text to list\n" +
                "2 - Search through list\n" +
                "3 - Edit list\n" +
                "4 - Remove text from list\n" +
                "5 - Sort the list\n" +
                "6 - Count number of words in the list\n" +
                "7 - Reverse the list\n" +
                "8 - Encrypt the list\n" +
                "0 - Exit");
    }

    //Scanner, returns a scanner
    private static Scanner scanner() {
        Scanner scan = new Scanner(System.in);
        return scan;
    }

    //Checks if input is valid for menu selection
    private static int scannerCheck(String input) {
        if (input.matches("[0-9]+")) {
            return Integer.parseInt(input);
        } else {
            return 99;
        }
    }

    //Method to add string to the list. Calls the dateMethod and the numberToFibonacci
    private static void add(String input) {
        ArrayList<String> setNewList = new ArrayList<>(lists.getList());
        setNewList.add(input + " " + dateMethod());

        lists.setList(setNewList);
        System.out.println(lists.getList().get(lists.getList().size()-1) + " has been added");
        numberToFibonacci(lists.getFibonacci().size());
    }

    //Creates a date stamp when a new string is added to the array
    private static String dateMethod() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        return simpleDateFormat.format(date);
    }


    //Encounts and sends the next number to the fibonacci list
    private static void numberToFibonacci(int n) {
        int sum;
        ArrayList<Integer> setNewFibonacci = new ArrayList<>(lists.getFibonacci());

        if (lists.getFibonacci().size() == 0 || lists.getFibonacci().size() == 1) {
            sum = 1;
        } else {
            sum = lists.getFibonacci().get(n - 1) + lists.getFibonacci().get(n - 2);
        }
        setNewFibonacci.add(sum);
        lists.setFibonacci(setNewFibonacci);

        System.out.println("Fibonacci sequence " + lists.getFibonacci());
        checkIfEven(sum);
    }

    //Checks if the last number in the fibonacci list is odd or even
    private static void checkIfEven(int numToCheck) {
        if (numToCheck % 2 == 0) {
            System.out.println(numToCheck + " is even");
        } else {
            System.out.println(numToCheck + " is odd");
        }
    }


    //Search the list and prints the result from the SearchFunction
    private static void search(String input) {
        if (indexSearched(input) == -1) {
            System.out.println("Input doesn't exist");
        } else {
            System.out.println(input + " was found at index " + indexSearched(input));
        }
    }

    //Search the list for the right index
    private static int indexSearched(String input) {
        for (int i = 0; i < lists.getList().size(); i++) {
            if (lists.getList().get(i).substring(0, lists.getList().get(i).lastIndexOf(" ")).contains(input)) {
                return i;
            }
        }
        return -1;
    }


    //Search the list for the requested item and change it
    private static void edit(String input) {
        ArrayList<String> setNewList = new ArrayList<>(lists.getList());
        if (indexSearched(input)== -1) {
            System.out.println("Input doesn't exist");
        } else {
            System.out.println("New input: ");
            setNewList.set(indexSearched(input), input = scanner().nextLine() + " " + dateMethod());
            lists.setList(setNewList);

            System.out.println(input + " has been added");
            numberToFibonacci(lists.getFibonacci().size());
        }
    }


    //Output when items will be removed from the arraylist
    private static void remove(String input) {
        ArrayList<String> setNewList = new ArrayList<>(lists.getList());

        if (indexSearched(input) == -1) {
            System.out.println("Input doesn't exist");
        } else {
            setNewList.remove(indexSearched(input));
            lists.setList(setNewList);
            System.out.println(input + " has been removed");
        }
    }


    //Output when sort is selected from the menu and calls the requested sort method
    private static void sort() {
        System.out.println("1. Sort text A-Z\n2. Sort text by date");
        switch (scannerCheck(scanner().nextLine())) {

            case 1:
                collSort();
                break;
            case 2:
                dateSort();
                break;
        }
    }

    //Sort the arraylist by letters
    private static void collSort() {
        //Before sort
        System.out.println("Before Sort: ");
        System.out.println(lists.getList());
        //Sort the list
        Collections.sort(lists.getList());
        //After Sort
        System.out.println("After Sort: ");
        System.out.println(lists.getList());
    }

    //Sort the list by date
    private static void dateSort() {
        //Before sort
        System.out.println("Before Sort: ");
        System.out.println(lists.getList());
        //Sort the list
        Collections.sort(lists.getList(), Comparator.comparing(list -> list.substring(list.lastIndexOf(" "))));
        //After Sort
        System.out.println("After Sort: ");
        System.out.println(lists.getList());
    }

    //Encrypt the string array by MD5
    private static String cryptoFunction(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            return byteArrayToString(digest).toUpperCase();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error, try again");
        }
        return "";
    }

    //builds a string of the byte array
    private static String byteArrayToString(byte[] ba) {
        StringBuilder hex = new StringBuilder(ba.length * 2);
        for (byte b : ba) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }


    //Loop the string array and holds the sum of all the ints returned from countWords
    private static void wordFunction(int sum){
        for(String words : lists.getList()) {
            sum += countWords(words);
        }
        lists.setSum(sum);
    }

    //Count the number of words in the string array list be searching blank spaces
    private static int countWords (String sentence) {
        if (sentence.isEmpty()) {
            return 0;
        }
        int space = sentence.indexOf(" ");
        if (space != -1) {
            return 1 + countWords(sentence.substring(space + 1));
        }
        return 0;
    }

    //Reverse the string array
    private static List<String> reverseRecursion(List<String> list) {
        if (list.size() <= 1) {
            return list;
        } else {
            List<String> reversedList = new ArrayList<>();
            reversedList.add(list.get(list.size() - 1));
            reversedList.addAll(reverseRecursion(list.subList(0, list.size() - 1)));
            return reversedList;
        }
    }
}