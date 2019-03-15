package com.example.slutuppgift;
import java.util.ArrayList;

public class ArrayLists {
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<Integer> fibonacci = new ArrayList<>();
    private int sum = 0;

    protected ArrayList<String> getList() {
        return list;
    }
    protected ArrayList<Integer> getFibonacci() {return fibonacci; }
    protected final int getSum(){ return sum; }

    protected void setList(ArrayList<String>list) { this.list= list; }
    protected void setFibonacci(ArrayList<Integer> fibonacci){
        this.fibonacci=fibonacci;
    }
    protected void setSum(int sum){this.sum = sum;
    }
}

