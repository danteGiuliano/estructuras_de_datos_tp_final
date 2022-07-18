package Estructuras.Grafo;

import java.util.Map;

import Estructuras.Lista.Lista;
import ORM.ORM;

public class TestGrafo {

    public static void main(String[] args) {
        Grafo grafo = ORM.get_aeropuertos();
        System.out.println(grafo.toString());
        //System.out.println(grafo.toString());
        Lista l = grafo.distancia_mas_corta("APV", "CBA");
        mostrar(l);
        

    }
    public static void mostrar(Lista l){
        if(!l.esVacia()){
            System.out.println(l.recuperar(1).toString());
        }
    }
}
