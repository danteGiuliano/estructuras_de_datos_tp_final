package Estructuras.AVL;

public class Test {
    static ArbolAVL avl = new ArbolAVL();
    static String ok="OK!",error="ERROR!";
  
    public static void main(String[] args) {
    
        System.out.println("TEST AVL");
        System.out.println("INSERTANDO  20 :"+respuesta(avl.insertar(parser("20"))));
        System.out.println("INSERTANDO  10 :"+respuesta(avl.insertar(parser("10"))));
        System.out.println("INSERTANDO  30 :"+respuesta(avl.insertar(parser("30"))));
        System.out.println("INSERTANDO  15 :"+respuesta(avl.insertar(parser("15"))));
        System.out.println("INSERTANDO  25 :"+respuesta(avl.insertar(parser("25"))));


        System.out.println(" APLICANDO ROTACION DERECHA INZQUIERDA CON 12 "+respuesta(avl.insertar(parser("12"))));
        System.out.println("ARBOL ESPERADO :");
        System.out.println("RAIZ : 20 HI: 12 HD: 30");
        System.out.println("RAIZ : 12 HI: 10 HD: 15");
        System.out.println("RAIZ : 30 HI: 25 ");
        System.out.println("---------------------------------------------------------");

        System.out.println("");

        System.out.println("INSERTANDO  11 :"+respuesta(avl.insertar(parser("11"))));
        System.out.println("INSERTANDO  5 :"+respuesta(avl.insertar(parser("5"))));
        System.out.println(avl.toString());
        
        System.out.println(" APLICANDO ROTACION DERECHA CON 23 "+respuesta(avl.insertar(parser("23"))));
        System.out.println("ARBOL ESPERADO :");
        System.out.println("RAIZ : 20 HI: 12 HD: 25");
        System.out.println("RAIZ : 12 HI: 10 HD: 15");
        System.out.println("RAIZ : 10 HI: 5 HD: 11");
        System.out.println("RAIZ : 25 HI: 23 HD: 30");
        System.out.println("---------------------------------------------------------");


        System.out.println(avl.toString());

        System.out.println(" APLICANDO ROTACION DERECHA CON 3 "+respuesta(avl.insertar(parser("3"))));
        System.out.println("ARBOL ESPERADO :");
        System.out.println("RAIZ : 20 HI: 10 HD: 25");
        System.out.println("RAIZ : 10 HI: 5 HD: 12");
        System.out.println("RAIZ : 5 HI: 3" );
        System.out.println("RAIZ : 12 HI: 11 HD:15" );
        System.out.println("RAIZ : 25 HI: 23 HD: 30");
        System.out.println("---------------------------------------------------------");

        
        System.out.println(avl.toString());

        System.out.println("INSERTANDO  26 :"+respuesta(avl.insertar(parser("26"))));
        
        System.out.println(" APLICANDO ROTACION IZQUIERDA DERECHA CON 27 "+respuesta(avl.insertar(parser("27"))));
        System.out.println("ARBOL ESPERADO :");
        System.out.println("RAIZ : 20 HI: 10 HD: 25");
        System.out.println("RAIZ : 10 HI: 5 HD: 12");
        System.out.println("RAIZ : 5 HI: 3" );
        System.out.println("RAIZ : 12 HI: 11 HD:15" );
        System.out.println("RAIZ : 25 HI: 23 HD: 27");  
        System.out.println("RAIZ : 27 HI: 26 HD: 30");
        System.out.println("---------------------------------------------------------");

        
        System.out.println(avl.toString());

        
        System.out.println(" PROBANDO INSERTAR DUPLICADO 20 debe dar error "+ respuesta(avl.insertar(parser("20"))));
        
        System.out.println("ELIMINANDO  10 :"+respuesta(avl.eliminar(parser("10"))));
        System.out.println(avl.toString());
        System.out.println("ELIMINANDO  11 :"+respuesta(avl.eliminar(parser("11"))));

        
        System.out.println(" APLICANDO ROTACION IZQUIERDA ELIMINANDO 20 caso 3 "+respuesta(avl.eliminar(parser("20"))));
        System.out.println("ARBOL ESPERADO :");
        System.out.println("RAIZ : 23 HI: 12 HD: 27");
        System.out.println("RAIZ : 12 HI: 5 HD: 15");
        System.out.println("RAIZ : 5 HI: 3" );
        System.out.println("RAIZ : 27 HI: 25 HD: 30" );
        System.out.println("RAIZ : 25  HD: 26");
        System.out.println("---------------------------------------------------------");
        
        System.out.println(avl.toString());

      
        System.out.println("ELIMINANDO  25 CASO 2 :"+respuesta(avl.eliminar(parser("25"))));
        System.out.println("ELIMINANDO  26 CASO 1 :"+respuesta(avl.eliminar(parser("26"))));


    
    }



    private static void insertar_v1(){

        avl.insertar("20");
        avl.insertar("10");
        avl.insertar("30");
        avl.insertar("15");
        avl.insertar("25");
        avl.insertar("12");
        avl.insertar("11");
        avl.insertar("5");

    }


    private static String respuesta(boolean resp){
        return resp?ok:error;
    }
    private static Integer parser(String cadena){
        return Integer.parseInt(cadena);
    }
}
