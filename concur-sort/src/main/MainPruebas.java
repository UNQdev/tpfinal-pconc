package main;

import java.util.ArrayList;

public class MainPruebas {
	
	public static void main(String[] args) {

		IntegerList listaConMuchosElementos = new IntegerList(new ArrayList<Integer>(), 16);
		System.out.println("** Test - Testo el ordenamiento de la lista con muchos elementos**");
		
//		Integer quantity = 3;
//		for (int i = 0; i < quantity; i++) {
//			listaConMuchosElementos.add((int) (Math.random() * 11));
//		}
		
		for (Integer i = 10000 ; i > 0; i--){
	        	listaConMuchosElementos.add( i );
	    }
		
        System.out.println("Lista con elementos sin ordenar: " + listaConMuchosElementos);
        listaConMuchosElementos.sort();
     
        System.out.println("Lista con elementos ordenados: " + listaConMuchosElementos);
        System.out.println("Elementos ordenados: " + listaConMuchosElementos.size());
    }
}
