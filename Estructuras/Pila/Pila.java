package Estructuras.Pila;

import java.util.HashMap;
import java.util.Map;

import Estructuras.Lista.Lista;
import Estructuras.Lista.Nodo;

/**
 *
 * @author Dante
 */
public class Pila {
    private Nodo tope;
    int peso;
    Map<Object,Integer> tabla_peso;

    public Pila(){
        
        this.tope=null;
        peso=0;
        tabla_peso=new HashMap<Object,Integer>();
    }
    //La estructura en si misma el tope apunta siempre SIEMPRE A null;  
    public boolean apilar(Object aux,int peso){

        Nodo t = new Nodo(aux,this.tope);
        peso+=peso;
        tabla_peso.put(aux, peso);
        this.tope=t;
        // el tope va de derecha a izquierda. tope siempre esta en el final
        return true;
    }
    public boolean desapilar(){
        boolean flag=false;
        if(this.tope!=null){
            Object tope = this.tope.get_elemento();
            Nodo aux =this.tope.get_enlace();
            this.tope=aux;
            this.peso-=tabla_peso.get(tope);
            tabla_peso.remove(tope);
            flag=true;
        }
        return flag;
    }

    public int obtener_peso(){
        return this.peso;
    }
    
    public Object obtenerTope(){
        Object aux=null;
       if(this.tope!=null){
       aux= this.tope.get_elemento();
       }
        return aux;
    }

    public void vaciar(){
        this.tope=null;
    }
    public boolean esVacia(){
        boolean flag=true;
        if(this.tope!=null){
            flag=false;
        }
        return flag;
    }
    public String toString(){
        String aux = "Pila vacia";
        Nodo index=this.tope;
        if(this.tope!=null){
            aux="";
            while(index!=null){
                aux+=index.get_elemento().toString();
                index=index.get_enlace();
            }
        }
        return aux;
    }
    public Pila clone() {
        Pila clon = new Pila();
        return cloneAux(clon, this.tope);
    }

    private Pila cloneAux(Pila clon, Nodo aux) {
        if (aux != null) {
            clon = cloneAux(clon, aux.get_enlace());
            Nodo t = new Nodo(aux.get_elemento(), clon.tope);
            clon.tope = t;
        }
        return clon;
    }

    public boolean bloque_repetido(Object aux){
        return tabla_peso.containsKey(aux);
    }

    public Lista pila_a_lista(){
        Lista lista = new Lista();
        Nodo index=this.tope;
        if(this.tope!=null){
            while(index!=null){
                lista.insertar(index.get_elemento(), 1);
                index=index.get_enlace();
            }
        }
        return lista;
    }

   
}
