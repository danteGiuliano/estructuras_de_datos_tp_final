package Estructuras.Pila;

public class TestPila {
    public static void main(String[] args) {
        
        Pila pila = new Pila();

        pila.apilar("4", 4);
        pila.apilar("6", 4);
        pila.apilar("5", 4);
        pila.apilar("3", 4);
        pila.apilar("2", 4);
        pila.apilar("1", 4);
        pila.apilar("6", 4);
        
        System.out.println(pila.toString());
        System.out.println(pila.obtener_peso());
    }
}
