package Estructuras.Heap;

public class Test{
    static String sOk = "\u001B[32m OK! \u001B[0m", sErr = " \u001B[31m ERROR \u001B[0m";
    public static final String NEGRO = "\u001B[30m";
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BLANCO = "\u001B[37m";   

   
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args){

        System.out.println( ANSI_CYAN_BACKGROUND  + ROJO +"**************************************************************");
        System.out.println( ANSI_CYAN_BACKGROUND + ROJO + "*                     Test Arbol Heap MAXIMO                 *");
        System.out.println( ANSI_CYAN_BACKGROUND + ROJO + "**************************************************************"+"\n\n"+ RESET);

        Heap a = new Heap();
        Heap b = new Heap();

        System.out.println(ANSI_YELLOW_BACKGROUND+"--------------------------------------------------------------------------------------------------------"
        + "------------------------------------------------------------------------------------------"+RESET);
        System.out.println("\n\n");


        System.out.println("Checkeo si es vacio " + ((  a.esVacio()) ? sOk : sErr));  
        System.out.println("Recupero cima de arbol vacio " + ((  a.recuperarCima()==null) ? sOk : sErr)); 
        System.out.println("Elimino cima de arbol vacio " + ((  a.eliminarCima()) ? sErr : sOk)); 

        System.out.println("Inserto el 15" + ((  a.insertar(15)) ? sOk : sErr));
        System.out.println("Recupero cima de arbol, tiene que dar 15 " + ((  (int)a.recuperarCima()==15) ? sOk : sErr)); 
        System.out.println("Elimino cima de arbol" + ((  a.eliminarCima()) ? sOk : sErr)); 

        System.out.println("Inserto el 15" + ((  a.insertar(15)) ? sOk : sErr));
        System.out.println("Inserto el 10" + ((  a.insertar(10)) ? sOk : sErr));
        System.out.println("Inserto el 20" + ((  a.insertar(20)) ? sOk : sErr));

        System.out.println("Checkeo si es vacio cuando tiene elementos " + ((  a.esVacio()) ? sErr : sOk));  
        System.out.println("Recupero cima de arbol, tiene que dar 20 " + ((  (int)a.recuperarCima()==20) ? sOk : sErr));


        System.out.println("\nToString() deberia dar  \n 20: HI--> 10 HD--> 15,  \narbol.toString() =  " + a.toString());


        System.out.println("\nInserto el 16" + ((  a.insertar(16)) ? sOk : sErr));
        System.out.println("Inserto el 12" + ((  a.insertar(12)) ? sOk : sErr));
        System.out.println("Inserto el 15" + ((  a.insertar(15)) ? sOk : sErr));

        System.out.println("\nToString() deberia dar \n20: HI--> 16 HD--> 15 "+
        "\n16: HI--> 10 HD--> 12 "+
        "\n10: HI--> -  HD--> - "+
        "\n12: HI--> -  HD--> - "+
        "\n15: HI--> 15 HD--> - "+
        "\n15: HI--> -  HD--> - "+
        "\n \narbol.toString() =  ");
        System.out.println(a.toString());

        System.out.println("Inserto el 8" + ((  a.insertar(8)) ? sOk : sErr));
        System.out.println("Inserto el 5" + ((  a.insertar(5)) ? sOk : sErr));
        System.out.println("Inserto el 5" + ((  a.insertar(5)) ? sOk : sErr));
      

        System.out.println("Recupero cima de arbol, tiene que dar 20 " + ((  (int)a.recuperarCima()==20) ? sOk : sErr));

        System.out.println("\nToString() deberia dar \n20: HI--> 16 HD--> 15 "+
        "\n16:  HI--> 10  HD--> 12 "+
        "\n10: HI--> 5 HD--> 5 "+
        "\n12: HI--> -  HD--> - "+
        "\n15: HI--> 15  HD--> 8 "+
        "\n15: HI--> -  HD--> - "+
        "\n8: HI--> -  HD--> - "+
        "\n \narbol.toString() =  ");
        System.out.println(a.toString());

        System.out.println("\n\n\n");
        System.out.println("Clono el arbol");
        b = a.clone();
        System.out.println("\nToString() deberia dar \n20: HI--> 16 HD--> 15 "+
        "\n16:  HI--> 10  HD--> 12 "+
        "\n10: HI--> 5 HD--> 5 "+
        "\n12: HI--> -  HD--> - "+
        "\n15: HI--> 15  HD--> 8 "+
        "\n15: HI--> -  HD--> - "+
        "\n8: HI--> -  HD--> - "+
        "\n \narbol.toString() =  ");
        System.out.println(a.toString());
        System.out.println(b.toString());
        System.out.println("\n\n\n");

        System.out.println("Elimino cima de arbol (20) " + ((  a.eliminarCima()) ? sOk : sErr)); 
        System.out.println("Recupero cima de arbol, tiene que dar 16 " + ((  (int)a.recuperarCima()==16) ? sOk : sErr));
        System.out.println("Elimino cima de arbol (16) " + ((  a.eliminarCima()) ? sOk : sErr)); 
        System.out.println("Recupero cima de arbol, tiene que dar 15 " + ((  (int)a.recuperarCima()==15) ? sOk : sErr));
        System.out.println("Elimino cima de arbol (15) " + ((  a.eliminarCima()) ? sOk : sErr)); 
        System.out.println("Recupero cima de arbol, tiene que dar 15 " + ((  (int)a.recuperarCima()==15) ? sOk : sErr));
        System.out.println("Elimino cima de arbol (15) " + ((  a.eliminarCima()) ? sOk : sErr)); 
        System.out.println("Recupero cima de arbol, tiene que dar 12 " + ((  (int)a.recuperarCima()==12) ? sOk : sErr));
        System.out.println("Elimino cima de arbol (12) " + ((  a.eliminarCima()) ? sOk : sErr)); 


        System.out.println("Chequeo que no existan cambios en el clon (tienen que estar ambos 5)");

        System.out.println("\nToString() deberia dar \n20: HI--> 16 HD--> 15 "+
        "\n16:  HI--> 10  HD--> 12 "+
        "\n10: HI--> 5 HD--> 5 "+
        "\n12: HI--> -  HD--> - "+
        "\n15: HI--> 15  HD--> 8 "+
        "\n15: HI--> -  HD--> - "+
        "\n8: HI--> -  HD--> - "+
        "\n \narbol.toString() =  ");
        System.out.println(b.toString());

        System.out.println("\n");

        
        System.out.println("\nToString() deberia dar \n10: HI--> 5 HD--> 8 "+
        "\n16: HI--> 5  HD--> - "+
        "\n5: HI--> -  HD--> - "+
        "\n8: HI--> -  HD--> - "+
        "\n \narbol.toString() =  ");
     
        System.out.println(a.toString());


    }

}