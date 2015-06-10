package main;

import java.util.ArrayList;

public class Printer {

    /*
    *
    * Propósito: imprimir cosas.
    *
    * */

    public static void printList(ArrayList<Integer> list){
        System.out.println("[");
        for (Integer in : list) {
            System.out.println(in + " ");
        }
        System.out.println("]");
    }
}
