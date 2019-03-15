package com.example.primenumbers;
import java.util.Collections;
import java.util.Scanner;

public class PrimeMain {
    private static Scanner scan = new Scanner(System.in);
    private static Scanner scanB = new Scanner(System.in);
    public static boolean quit = false;
    static AllPrimeNumbers allPrimeNumbers = new AllPrimeNumbers();

    public static void main(String[] args) {

        //Visar menyn vid uppstart
        showMenu();
        while (!quit){
            System.out.println("Var god gör val från menyn");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice){
                case 1:
                    addNum();
                    break;
                case 2:
                    allPrimeNumbers.sortNum();
                    break;
                case 3:
                    searchNum();
                    break;
                case 4:
                    allPrimeNumbers.showList();
                    break;
                case 5:
                    generatePrime();
                    break;
                case 6:
                    System.out.println("Programmet avslutas");
                    quit=true;
                    break;
            }
        }
    }

    //Visar menyn
    private static void showMenu(){
        System.out.println("Meny för primtal");
        System.out.println("1 - Lägg till en siffra i listan\n" +
                "2 - Sortera siffrorna i listan\n" +
                "3 - Sök efter siffra i listan\n" +
                "4 - Visa listan med primtal\n" +
                "5 - Generera primtal\n" +
                "6- Avsluta");
    }

    //Tar emot input, kontrollerar intput och gör om den till int
    private static void addNum() {
        String isStringInt;
        do {
            System.out.println("Skriv in siffra eller tryck M för att återgå till menyn: ");
            isStringInt = scan.nextLine();
            if(isStringInt.equals("M")){
                showMenu();
                break;
            }
            else if(!isNumeric(isStringInt)) {
            addNum();
            }
            int isPrime = Integer.valueOf(isStringInt);
            checkPrime(isPrime);
            allPrimeNumbers.showList();
        }
        while (isNumeric(isStringInt));
    }

        //Kontrollerar om input är ett primtal
    public static void checkPrime(int isPrime){
        int run=0;

        if(isPrime==1 || isPrime==0 || isPrime==4){
           run=1;
        }
        for(int i=2; i<isPrime/2; i++) {
            if (isPrime % i == 0) {
                run=1;
                break;
            }
        }
        if(run==0){
            if(allPrimeNumbers.addPrime(isPrime));
        }
    }

        //Kollar om input är en int
    public static boolean isNumeric(String checkNum){
        try{
            Integer.parseInt(checkNum);
        } catch (NumberFormatException | NullPointerException nfe){
            return false;
        }
        return true;
    }


    //Sökfunktion
    private static void searchNum(){
        if(!allPrimeNumbers.checkContent()){
            System.out.println("Listan är tom");
            return;
        }
        System.out.println("Vilken siffra söker du: ");
        int queryInt = scan.nextInt();
        int existingInt = allPrimeNumbers.searchNum(queryInt);
        if(existingInt == -1){
            System.out.println("Kunde inte hitta din siffra");
            int closest = allPrimeNumbers.findClosest(queryInt);
            System.out.println("Den närmsta siffran i listan är: " + closest);
            return;
        }
        System.out.println("Siffran " + queryInt + " finns i listan");

    }

    //Tar input på hur många primtal som ska genereras och kallar på primtalsgeneratorn
    public static void generatePrime(){
        System.out.println("Hur många primtal vill du generera: ");
        String times =scanB.nextLine();
        if(!isNumeric(times)){
            System.out.println("Din input är inte en siffra");
            return;
        }
        int generateTimes = Integer.valueOf(times);
        int max = allPrimeNumbers.findMax();
        allPrimeNumbers.primeGenerator(generateTimes, max);
        showMenu();
    }
}
