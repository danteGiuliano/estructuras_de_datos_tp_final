package Estructuras.Grafo;

import java.util.Map;

import Estructuras.Lista.Lista;
import ORM.ORM;

public class TestGrafo {

    public static void main(String[] args) {

        Grafo grafo =ORM.get_aeropuertos();

        Lista  l = grafo .caminoMasCorto("NQN", "AMB");
        if(!l.esVacia()){
            System.out.println(l.toString());
        }

    }
    
}
