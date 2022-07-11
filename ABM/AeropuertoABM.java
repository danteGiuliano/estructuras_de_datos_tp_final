package ABM;

import Estructuras.Grafo.Grafo;
import ORM.ORM;

public class AeropuertoABM{

    static Grafo mapa_aeroportuario=new Grafo();

    
    public static void main(String[] args) {
        cargar_mapa();
        System.out.println("Mostrar aeropuertos");
        System.out.println(mapa_aeroportuario.toString());
        mapa_aeroportuario.eliminar_vertice("ETC");
        System.out.println(mapa_aeroportuario.toString());
        System.out.println("Existe arco entre NQN y APV "+mapa_aeroportuario.existe_arco("NQN", "APV"));
        System.out.println("Existe arco entre NQN y NDM "+mapa_aeroportuario.existe_arco("NQN", "NDM"));
        System.out.println("Existe Vertice NQN"+mapa_aeroportuario.existe_vertice("NQN"));
        mapa_aeroportuario.eliminar_arco("NQN", "APV", "2000");
        System.out.println(mapa_aeroportuario.toString());
    }   
    private static void cargar_mapa(){
        mapa_aeroportuario=ORM.get_aeropuertos();
    }
}