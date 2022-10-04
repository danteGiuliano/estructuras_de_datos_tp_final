package Estructuras.Grafo;

import java.util.Map;

import Estructuras.Lista.Lista;
import ORM.ORM;

public class TestGrafo {

    public static void main(String[] args) {
        Grafo grafo = ORM.get_aeropuertos();
        System.out.println(grafo.toString());
        //System.out.println(grafo.toString());
        Lista l1 = grafo.camino_mas_corto("NQN", "ETC");
        Lista l2 = grafo.camino_mas_rapido("NDM","ETC");
        mostrar(l1);
        mostrar(l2);

    }
    public static void mostrar(Lista l){
        if(!l.esVacia()){
            System.out.println(l.toString());
        }
    }
}
