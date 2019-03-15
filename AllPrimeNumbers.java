package com.example.primenumbers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllPrimeNumbers {

    ArrayList<Integer> primeNumbers = new ArrayList<>();

    //Kollar om arraylistan innehåller några element
    public boolean checkContent(){
        if(primeNumbers.size()<1){
            return false;
        } return true;
    }


    //Lägger till primtal i arraylistan
    public boolean addPrime(int onePrime) {
        if (findIndex(onePrime) >= 0) {
            return false;
        }
        primeNumbers.add(onePrime);
        return true;
    }

    //kollar om primtalet redan finns i listan och förhindrar dubbletter
    private int findIndex(int onePrime) {
        for (int j = 0; j < this.primeNumbers.size(); j++) {
            int existingPrime = this.primeNumbers.get(j);
            if (existingPrime == onePrime) {
                System.out.println("Siffran finns redan");
                return j;
            }
        }
        return -1;
    }

    //Skriver ut listan med primtal efter att nytt tal lagts till
    public void showList() {
        int sum = 0;

        if (primeNumbers.size() > 1) {
            PrimeMain.checkPrime(addSum(sum));
        }
        if (primeNumbers.size() > 0) {
            System.out.println("Lista med primtal: ");
            for (int k = 0; k < primeNumbers.size(); k++) {
                int str = primeNumbers.get(k);
                System.out.println(str);
            }
        }
    }

    //Lägger till summan i slutet av arrayen om summan är ett primtal
    public int addSum(int sum) {
        if (primeNumbers.size() > 0) {
            for (int l = 0; l < primeNumbers.size(); l++)
                sum += primeNumbers.get(l);
        }
        return sum;
    }


    //Skriver ut listan före och efter sortering
    public void sortNum() {
        System.out.println("Listan före sortering: ");
        System.out.println(primeNumbers);
        ArrayList<Integer> sortedList = quickSort(primeNumbers);
        System.out.println("Listan efter sortering: ");
        System.out.println(sortedList);
    }

    //Sorteringsalgoritm Quicksort  uppgift e)
    public static ArrayList<Integer> quickSort(ArrayList<Integer> primeNumbers) {
        if (primeNumbers.size() <= 0) {
            return primeNumbers;
        }
        ArrayList<Integer> smaller = new ArrayList<Integer>();
        ArrayList<Integer> greater = new ArrayList<Integer>();
        Integer pivot = primeNumbers.get(0);
        for (int p = 1; p < primeNumbers.size(); p++) {
            Integer n = primeNumbers.get(p);
            if (n.compareTo(pivot) < 0) {
                smaller.add(n);
            } else {
                greater.add(n);
            }}
            smaller = quickSort(smaller);
            greater = quickSort(greater);
            smaller.add(pivot);
            smaller.addAll(greater);
            return smaller;
        }


        //Söker efter siffror i arrayen, uppgift f)
    public int searchNum(int queryInt) {
            int position = findPrime(queryInt);
            if (position >= 0) {
                return primeNumbers.get(position);
            }
        return -1;
        }

        //Hittar index i arraylistan
    private int findPrime(int onePrime) {
        return this.primeNumbers.indexOf(onePrime);
    }


    //Letar efter närmast tal i arrayen
    public int findClosest(int number){
        int min = (primeNumbers.get(0)-number);
        if(min<0){
            min= number - primeNumbers.get(0);
        }
        int value=0;
        for(int i=0; i<primeNumbers.size(); i++){
            int diff =(number - primeNumbers.get(i));
            if(diff<0){
                diff=(primeNumbers.get(i)-number);
            }
            if(diff<min){
                value=primeNumbers.get(i);
                min=number-value;
                if(min<0){
                    min=value-number;
                }
            }
            else if(diff>min){
                value=value;
            }
            else{
                value=primeNumbers.get(0);
            }
        }
     return value;
    }

    //Hittar högsta värdet i arraylistan
    public int findMax(){
         int highest = Collections.max(primeNumbers);
        return highest+1;
    }

    //Genererar primtal
    public void primeGenerator(int times, int max){
           for(int i=max; i<100000; i++){
               if(!checkPrime(i)){
                   continue;
               }addPrime(i);
               times= times-1;
               if(times==0){ break;}
           }
        return;
    }

    //Kollar om talet från primtalsgeneratorn är ett primtal
    private boolean checkPrime(int isPrime){
        if(isPrime==1 || isPrime==0 || isPrime==4){
            return false;
        }
        for(int i=2; i<isPrime/2; i++) {
            if (isPrime % i == 0) {
                return false;
            }
        }
        return true;
        }
    }