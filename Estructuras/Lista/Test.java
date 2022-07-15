package Estructuras.Lista;

import Dominio.Viaje;
import Dominio.Vuelo;
import Estructuras.AVL.ArbolAVL;
import ORM.ORM;

public class Test {
    public static void main(String[] args) {

        ArbolAVL vuelos = ORM.get_vuelos();
        Vuelo v =(Vuelo) vuelos.extraer_elemento("AA2279");
        Lista l = v.get_registro_vuelos();

        System.out.println(l.localizar("20/10/2019"));
        System.out.println(l.localizar("15/10/2003"));
        System.out.println(l.localizar("9/08/2012"));
        

        System.out.println(l.toString());
        int indice =l.localizar("17/11/1997");
        System.out.println(indice);
        Viaje viaje =(Viaje)(l.recuperar(indice));
        System.out.println(viaje.get_fecha());
    }
}
