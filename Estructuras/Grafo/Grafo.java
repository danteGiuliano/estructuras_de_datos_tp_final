package Estructuras.Grafo;

import Estructuras.Lista.Lista;
import Estructuras.Pila.Pila;

public class Grafo {

    private NodoVertice inicio;

    public Grafo() {
        this.inicio = null;
    }

    public boolean eliminar_vertice(Object elemento) {
        boolean borrado = false;
        NodoVertice enlace = buscar_enlace(elemento);
        if (enlace != null) {
            NodoAdyacente referencias = enlace.get_siguiente_vertice().get_primer_nodo();// Los nodos donde debo borrar

            enlace.set_siguiente_vertice(enlace.get_siguiente_vertice().get_siguiente_vertice()); // Borro el vertice
            while (referencias != null) {
                eliminar_vertice_adyacente(referencias.get_vertice(), elemento);
                referencias = referencias.get_nodo_adyacente();
            }
            borrado = true;
        }
        return borrado;
    }

    private void eliminar_vertice_adyacente(NodoVertice nodo, Object elemento) {
        // Borro la primer ocurrencia
        NodoAdyacente referencia = nodo.get_primer_nodo();
        NodoAdyacente anterior = null;
        boolean borrado = false;
        while (referencia != null && !borrado) {
            if (referencia.get_vertice().equals(elemento)) {
                if (anterior == null) {
                    nodo.set_primer_nodo(referencia.get_nodo_adyacente());
                } else {
                    anterior.set_nodo_adyacente(referencia.get_nodo_adyacente());
                }
                borrado = true;
            }
            anterior = referencia;
            referencia = referencia.get_nodo_adyacente();
        }
    }

    private NodoVertice buscar_enlace(Object elemento) {
        NodoVertice ultimo = this.inicio;
        boolean es_ultimo = false;
        while (ultimo != null && !es_ultimo) {
            if (ultimo.get_siguiente_vertice().equals(elemento)) {
                es_ultimo = true;
            } else {
                ultimo = ultimo.get_siguiente_vertice();
            }
        }
        return ultimo;
    }

    public boolean insertar_vertice(Object elemento) {
        boolean insercion = false;
        NodoVertice repetido = buscar_nodo(elemento);
        if (repetido == null) {
            this.inicio = new NodoVertice(elemento, this.inicio);
            insercion = true;
        }
        return insercion;
    }

    /**
     * Busca si existe el Nodo Vertice en mi grafo
     * 
     * @param elemento
     * @return null o nodo vertice
     */

    private NodoVertice buscar_nodo(Object elemento) {
        NodoVertice nodo_encontrado = this.inicio;

        while (nodo_encontrado != null && !nodo_encontrado.equals(elemento)) {
            nodo_encontrado = nodo_encontrado.get_siguiente_vertice();
        }
        return nodo_encontrado;
    }

    public boolean insertar_arco(Object vertice_x, Object vertice_y, Comparable etiqueta) {
        boolean insercion = false;

        NodoVertice nodo_x = buscar_nodo(vertice_x);
        NodoVertice nodo_y = buscar_nodo(vertice_y);

        if (nodo_x != null && nodo_y != null) {

            if (nodo_x.get_primer_nodo() != null) {
                nodo_x.set_primer_nodo(new NodoAdyacente(etiqueta, nodo_y, nodo_x.get_primer_nodo()));
            } else {
                nodo_x.set_primer_nodo(new NodoAdyacente(nodo_y, etiqueta));
            }
            if (nodo_y.get_primer_nodo() != null) {
                nodo_y.set_primer_nodo((new NodoAdyacente(etiqueta, nodo_x, nodo_y.get_primer_nodo())));
            } else {
                nodo_y.set_primer_nodo(new NodoAdyacente(nodo_x, etiqueta));
            }
            insercion = true;
        }
        return insercion;

    }

    public boolean eliminar_arco(Object referencia_x, Object referencia_y, Object referencia_arco) {
        boolean borrado = false;
        NodoVertice nodo_x = buscar_nodo(referencia_x);
        NodoVertice nodo_y = buscar_nodo(referencia_y);

        if (nodo_x != null || nodo_y != null) {
            // busco el arco a borrar en los 2 nodos
            borrado = eliminar_arco_adyacente(nodo_x, referencia_arco);
            borrado = eliminar_arco_adyacente(nodo_y, referencia_arco);
        }

        return borrado;
    }

    private boolean eliminar_arco_adyacente(NodoVertice nodo, Object etiqueta) {
        NodoAdyacente referencia = nodo.get_primer_nodo();
        NodoAdyacente anterior = null;
        boolean borrado = false;
        while (referencia != null) {

            if (referencia.get_etiqueta().equals(etiqueta)) {
                if (anterior == null) {
                    nodo.set_primer_nodo(referencia.get_nodo_adyacente());
                } else {
                    anterior.set_nodo_adyacente(referencia.get_nodo_adyacente());
                }
                borrado = true;
            }
            anterior = referencia;
            referencia = referencia.get_nodo_adyacente();
        }
        return borrado;
    }

    public boolean existe_vertice(Object elemento) {
        boolean existe = false;
        NodoVertice recorrido = this.inicio;
        while (recorrido != null && !existe) {
            if (recorrido.get_elememento().equals(elemento)) {
                existe = true;
            } else {
                recorrido = recorrido.get_siguiente_vertice();
            }
        }
        return existe;
    }

    public boolean existe_arco(Object elemento_x, Object elemento_y) {
        NodoVertice recorrido = this.inicio;
        NodoAdyacente candidado = null;
        Object busqueda = null;
        boolean existe = false, existe_arco = false;

        while (recorrido != null & !existe_arco) {
            if (recorrido.get_elememento().equals(elemento_x)) {
                busqueda = elemento_y;
                existe_arco = true;
            }
            if (recorrido.get_elememento().equals(elemento_y)) {
                busqueda = elemento_x;
                existe_arco = true;
            }
            if (existe_arco) {
                candidado = recorrido.get_primer_nodo();
            }
            recorrido = recorrido.get_siguiente_vertice();

        }
        if (existe_arco) {
            while (candidado != null && !existe) {

                if (candidado.get_vertice().equals(busqueda)) {
                    existe = true;
                } else {
                    candidado = candidado.get_nodo_adyacente();
                }

            }
        }
        return existe;
    }

    public Lista listar_arcos(Object elemento_x, Object elemento_y) {
        Lista lista = new Lista();
        NodoVertice recorrido = this.inicio;
        NodoAdyacente candidado = null;
        Object busqueda = null;
        boolean existe = false, existe_arco = false;

        while (recorrido != null & !existe_arco) {
            if (recorrido.get_elememento().equals(elemento_x)) {
                busqueda = elemento_y;
                existe_arco = true;
            }
            if (recorrido.get_elememento().equals(elemento_y)) {
                busqueda = elemento_x;
                existe_arco = true;
            }
            if (existe_arco) {
                candidado = recorrido.get_primer_nodo();
            }
            recorrido = recorrido.get_siguiente_vertice();

        }
        if (existe_arco) {
            while (candidado != null && !existe) {

                if (candidado.get_vertice().equals(busqueda)) {
                    lista.insertar(candidado.get_etiqueta(), 1);
                }
                candidado = candidado.get_nodo_adyacente();
            }
        }
        return lista;
    }

    public Object extraer_arco(Object elemento_x, Object elemento_y, Object etiqueta) {
        Object arco = null;
        NodoVertice recorrido = this.inicio;
        NodoAdyacente candidado = null;
        Object busqueda = null;
        boolean existe = false, existe_arco = false;

        while (recorrido != null & !existe_arco) {
            if (recorrido.get_elememento().equals(elemento_x)) {
                busqueda = elemento_y;
                existe_arco = true;
            }
            if (recorrido.get_elememento().equals(elemento_y)) {
                busqueda = elemento_x;
                existe_arco = true;
            }
            if (existe_arco) {
                candidado = recorrido.get_primer_nodo();
            }
            recorrido = recorrido.get_siguiente_vertice();

        }
        if (existe_arco) {
            while (candidado != null && !existe) {

                if (candidado.get_vertice().equals(busqueda)) {
                    if (candidado.get_etiqueta().equals(etiqueta)) {
                        arco = candidado.get_etiqueta();
                        existe = true;
                    }
                } else {
                    candidado = candidado.get_nodo_adyacente();
                }
            }
        }
        return arco;
    }

    public Object extraer_vertice(Object tipo) {
        return buscar_nodo(tipo).get_elememento();
    }

    public Lista camino_mas_corto(Object origen, Object destino) {
        Lista mas_largo = new Lista();
        NodoVertice verticeOr = buscar_nodo(origen);
        if (verticeOr != null) {
            Lista masLargoActual = new Lista();
            mas_largo = camino_mas_corto_aux(verticeOr, destino, mas_largo, masLargoActual);
        }
        return mas_largo;
    }

    private Lista camino_mas_corto_aux(NodoVertice n, Object destino, Lista mas_largo, Lista mas_largo_actual) {

        mas_largo_actual.insertar(n.get_elememento(), mas_largo_actual.longitud() + 1);
        if (n.get_elememento().equals(destino)) {
            if (mas_largo.esVacia()) {
                mas_largo = mas_largo_actual.clone();
            } else {
                if (mas_largo.longitud() > mas_largo_actual.longitud()) {
                    mas_largo.vaciar();
                    mas_largo = mas_largo_actual.clone();
                }
            }
        } else {
            NodoAdyacente ruta = n.get_primer_nodo();
            while (ruta != null && (mas_largo.longitud() < mas_largo.longitud() || mas_largo.esVacia())) {
                if (mas_largo_actual.localizar(ruta.get_vertice().get_elememento()) < 0) {
                    mas_largo = camino_mas_corto_aux(ruta.get_vertice(), destino, mas_largo, mas_largo_actual);
                }
                ruta = ruta.get_nodo_adyacente();
            }
        }
        mas_largo_actual.eliminar(mas_largo_actual.longitud());

        return mas_largo;
    }

    /**
     * 
     * METODO DE DOMINO , NO PROPIO DEL TDA
     * 
     * 
     * @param referencia_origen
     * @param referencia_destino
     * @param cantidad Cantidad de vuelos maximos
     * @return
     */
    public boolean camino_cantidad_vuelos(Object referencia_origen, Object referencia_destino, int cantidad) {
        NodoVertice origen = buscar_nodo(referencia_origen);
        boolean es_posible = false;
        if (origen != null) {
            Lista actual = new Lista();
            es_posible = camino_cantidad_vuelos_aux(origen, referencia_destino, cantidad, actual, new Lista(),
                    es_posible);
        }
        return es_posible;
    }

    private boolean camino_cantidad_vuelos_aux(NodoVertice origen, Object destino, int cantidad, Lista camino,
            Lista camino_aux,
            boolean es_posible) {

        camino.insertar(origen.get_elememento(), camino.longitud() + 1);
        if (origen.get_elememento().equals(destino)) {
            if (camino_aux.esVacia()) {
                camino_aux = camino.clone();
            } else {
                if (camino_aux.longitud() < camino.longitud()) {
                    camino_aux = camino.clone();
                }
            }
            if (camino_aux.longitud() - 1 <= cantidad) {
                es_posible = true;
            }
        } else {
            NodoAdyacente ruta = origen.get_primer_nodo();
            while (!es_posible && ruta != null && (camino.longitud() - 1 <= cantidad || camino_aux.esVacia())) {
                if (camino.localizar(ruta.get_vertice().get_elememento()) < 0) {
                    es_posible = camino_cantidad_vuelos_aux(ruta.get_vertice(), destino, cantidad, camino,
                            camino_aux, es_posible);
                }
                ruta = ruta.get_nodo_adyacente();
            }
        }
        camino.eliminar(camino.longitud());
        return es_posible;
    }

    /**
     * Metodo de debug
     * 
     * Orden O(n²)
     */
    public String toString() {
        String salida = "";

        if (this.inicio != null) {

            salida += "[ Vertices ] : [Etiqueta,Vertice] \n";

            NodoVertice nodo_vertice = this.inicio;

            while (nodo_vertice != null) {
                salida += "[ " + nodo_vertice.get_elememento() + " ] :";

                NodoAdyacente nodo_adyacente = nodo_vertice.get_primer_nodo();
                while (nodo_adyacente != null) {
                    salida += "[ " + nodo_adyacente.get_etiqueta().toString() + " , "
                            + nodo_adyacente.get_vertice().toString() + " ] ";
                    nodo_adyacente = nodo_adyacente.get_nodo_adyacente();
                }
                salida += "\n";
                nodo_vertice = nodo_vertice.get_siguiente_vertice();
            }
        }
        return salida;
    }

    public Lista camino_mas_rapido(Object origen, Object destino) {
        Lista camino_mas_rapido = new Lista();
        NodoVertice vertice_origen = buscar_nodo(origen);
        if (vertice_origen != null) {
            Pila camino_menos_peso = new Pila();
            camino_menos_peso = camino_mas_rapido_aux(vertice_origen, destino, camino_menos_peso, new Pila());
            camino_mas_rapido = camino_menos_peso.pila_a_lista();
        }
        return camino_mas_rapido;
    }

    private Pila camino_mas_rapido_aux(NodoVertice nodo, Object destino, Pila mas_largo, Pila mas_rapido_aux) {

        mas_rapido_aux.apilar(nodo.get_elememento(), nodo.get_primer_nodo().peso_etiqueta());

        if (nodo.get_elememento().equals(destino)) {
            if (mas_largo.esVacia()) {
                mas_largo = mas_rapido_aux.clone();
            } else {
                if ((mas_largo.obtener_peso() > mas_rapido_aux.obtener_peso())) {
                    mas_largo.vaciar();
                    mas_largo = mas_rapido_aux.clone();
                }
            }
        } else {
            NodoAdyacente camino = nodo.get_primer_nodo();
            while (camino != null
                    && (mas_rapido_aux.obtener_peso() < mas_largo.obtener_peso() || mas_largo.esVacia())) {
                if (!mas_rapido_aux.bloque_repetido(camino.get_vertice().get_elememento())) {
                    mas_largo = camino_mas_rapido_aux(camino.get_vertice(), destino, mas_largo, mas_rapido_aux);
                }
                camino = camino.get_nodo_adyacente();
            }
        }
        return mas_largo;
    }

}
