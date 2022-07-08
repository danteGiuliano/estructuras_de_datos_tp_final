package ABM;

import java.util.ArrayList;
import java.util.Scanner;

import Dominio.Cliente;
import ORM.ORM;

public class ClienteABM {
    static Scanner edat= new Scanner(System.in);
    static String format="%15s %20s %15s %15s %20s %15s %15s";

    static ArrayList<Cliente> clientes= ORM.get_clientes();


    public static void main(String[] args) {
        mostrar_clientes();
    }


    public static void cargar_cliente(){

        String nombre,numero_documento,tipo_dni,apellido,domicilio,telefono,fecha_nacimiento;

        System.out.println("ingrese un nombre");
        nombre=edat.next();
        System.out.println("ingrese un apellido");
        apellido=edat.next();
        System.out.println("Ingrese un numero de documento");
        numero_documento=edat.next();
        System.out.println("Ingrese un tipo de documento");
        tipo_dni=edat.next();
        System.out.println("ingrese un domicilio");
        domicilio=edat.next();
        System.out.println("ingrese un telefono");
        telefono=edat.next();
        System.out.println("ingrese una fecha dd/mm/aaaa");
        fecha_nacimiento=edat.next();

        
    }
   
    public static void modificar_cliente(){
        mostrar_clientes();
        System.out.println("Ingrese un TIPO DE DOCUMENTO");

    }

    public static void mostrar_clientes(){
        
        clientes.forEach(e->{
            mostrar_cliente(e.get_tipo_dni(),e.get_numero_dni(),e.get_nombre(),e.get_apellido(),e.get_domicilio()
            ,e.get_numero_telefono(),e.get_fecha_nacimiento());
        });
    }

    



    private static void mostrar_cliente(String tipo_dni,String numero_documento,String nombre,String apellido,String domicilio,String telefono,String fecha_nacimiento){
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.printf(format,"TIPO DOCUMENTO", "NRO DOCUMENTO", "NOMBRE", "APELLIDO","DOMICILIO","TELEFONO","FECHA NACIMIENTO");
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.format(format, tipo_dni,numero_documento,nombre,apellido,domicilio,telefono,fecha_nacimiento);
        System.out.println();
    }
    
}
