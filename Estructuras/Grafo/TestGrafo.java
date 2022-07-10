package Estructuras.Grafo;

public class TestGrafo {

    public static void main(String[] args) {

        System.out.println("Llenado de grafo");
        Grafo grafo = new Grafo();

        grafo.insertar_vertice("A");
        grafo.insertar_vertice("B");

        grafo.insertar_arco("A", "B", 25);

        System.out.println(grafo.toString());

    }
    
}
