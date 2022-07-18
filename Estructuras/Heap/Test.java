package Estructuras.Heap;

public class Test{
    public static void main(String[] args) {
        Heap h = new Heap();

        h.insertar(Integer.parseInt("5"));
        h.insertar(Integer.parseInt("2"));
        h.insertar(Integer.parseInt("8"));
        h.insertar(Integer.parseInt("4"));
        h.insertar(Integer.parseInt("6"));
        h.insertar(Integer.parseInt("7"));

        System.out.println(h.toString());

    }
}