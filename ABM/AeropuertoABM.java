package ABM;

import Estructuras.Grafo.Grafo;
import ORM.ORM;

public class AeropuertoABM{

    static Grafo mapa_aeroportuario=new Grafo();

    
    public static void main(String[] args) {
        cargar_mapa();
        System.out.println("Mostrar aeropuertos");
        System.out.println(mapa_aeroportuario.toString());

    }
    private static void cargar_mapa(){
        mapa_aeroportuario=ORM.get_aeropuertos();
    }
}