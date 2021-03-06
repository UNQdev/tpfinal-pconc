package main;

public class Sorter extends Thread {
	
	private IntegerList miListaConcurrente;
	public Buffer buffer;
	private Counter contador;
	
    public Sorter (IntegerList listaInicial, Buffer buffer, Counter c){
    	this.buffer = buffer;
    	this.contador = c;
    	this.miListaConcurrente = listaInicial;
    }
	
    public IntegerList listaInicial(){
    	return miListaConcurrente;
    } 
   
   @Override
   public void run() {
	   qsort_worker();
	}
   
   public void qsort_worker() { 
	   while (true) {    		
		   Range unRango = this.buffer.pop(); // consume trabajo (bloqueante)
           if (!unRango.isValid()) 
        	   return;          	            
           if (unRango.size() > 0) {
        	   int count = this.miListaConcurrente.partition(unRango.start,unRango.end );
        	   Range right= new Range(unRango.start, unRango.start+count-1);
        	   Range left= new Range(unRango.start+count+ 1 , unRango.end);
           if(right.isValid())
        	   this.buffer.push(right);// agrega trabajo
           if(left.isValid())
        	   this.buffer.push(left); // agrega trabajo
           } 
           if (!unRango.isEmpty()) 
               this.contador.dec();
	   }
   } 
   
}
