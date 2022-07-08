package ORM;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Dominio.Cliente;

public class ORM {
    /**
     *  
     * Algoritmo extra para leer/cargar/guardar informacion en el JSON
     * 
     */

    static String ruta = System.getProperty("user.dir") + "/data.json";
    static JSONParser parser = new JSONParser();
    static JSONObject data;
    static JSONArray vuelos;
    static JSONArray aeropuertos;
    static JSONArray clientes;
    static JSONArray pasajes;

    public ORM(){};

    static Scanner edat = new Scanner(System.in);

    private static void leer_json() throws IOException, org.json.simple.parser.ParseException {

        data         =        (JSONObject) parser.parse(new FileReader(ruta));
        vuelos       =        (JSONArray) data.get("Vuelos");
        aeropuertos  =        (JSONArray) data.get("Aeropuertos");
        clientes     =        (JSONArray) data.get("Clientes");
        pasajes      =        (JSONArray) data.get("Pasajes");

    }

  
    public static void cargar_info() {
        try {
            leer_json();
        } 
        catch (FileNotFoundException f) {}
        catch (IOException e) {System.err.println("Archivo no existe");} 
        catch (org.json.simple.parser.ParseException e) {e.printStackTrace();}

    }
   
    public static void mostrar_consola(JSONArray data){
        data.forEach(e->{
            JSONObject elemento = (JSONObject)e;
            System.out.println(elemento);
        });
    }

    public static void cargar_vuelo(){

        String empresas []={"Aerolineas Argentinas","Jet Smart" ,"Fly Bondi","Air Canada"};
        String codigoEmpresas[]={"AA","JS","FB","AC"};
        
        int opcion_elegida;
        String codigo_elegido;

        System.out.println("Seleccione una empresa disponible");
        for (int i = 0; i < empresas.length; i++) {
            System.out.println(i+"-"+empresas[i]);
        }
        opcion_elegida=edat.nextInt();
        if(opcion_elegida>=0 && opcion_elegida <empresas.length){
            codigo_elegido=codigoEmpresas[opcion_elegida];
        }   
       
     
        
        

    }

    public static void cargar_cliente(String nombre,String apellido,String tipo_dni,String numero_dni,String 
        domicilio,String fecha_nacimiento,String numero_documento,String telefono){

            JSONObject cliente = new JSONObject();
            cliente.put("nombre", nombre);
            cliente.put("apellido",apellido);
            cliente.put("tipo_dni",tipo_dni);
            cliente.put("numero_dni",numero_documento);
            cliente.put("domicilio",domicilio);
            cliente.put("numero_telefono", telefono);
            cliente.put("fecha_nacimiento",fecha_nacimiento);

            clientes.add(cliente);
            guardar_info();
    }

    public static void cargar_aeropuerto(){
        String nombre,codigo,telefono;

        System.out.println("ingrese el nombre de aeropuerto");
        nombre=edat.nextLine();
        System.out.println("Ingrese su codigo unico de 3 letras");
        codigo=edat.nextLine();
        System.out.println("Ingrese su numero telefonico EJ: 299-473459");
        telefono=edat.nextLine();
        
        System.out.println("Su informacion es: \n Nombre: "+nombre+"\n Codigo: "+codigo+"\n telefono: "+telefono);
        System.out.println("Desea aceptarla? true/false");

        if(edat.nextBoolean()){
            JSONObject aeropuerto = new JSONObject();

            aeropuerto.put("codigo", codigo);
            aeropuerto.put("nombre", nombre);
            aeropuerto.put("telefono", telefono);

            aeropuertos.add(aeropuerto);
            guardar_info();
           
        }else{
            System.out.println("Informacion rechazada");
        }

    }

    private static void guardar_info(){
        try{
            FileWriter fr= new FileWriter(ruta);
            fr.write(data.toJSONString());
            fr.flush();
            fr.close();
        }
        catch (FileNotFoundException f) {}
        catch (IOException e) {System.err.println("Archivo no existe");}
    }

    public static ArrayList<Cliente> get_clientes(){

        verifica_carga();

        ArrayList<Cliente> clientes_cliente = new ArrayList<Cliente>();
        
        clientes.forEach(e->{

            JSONObject elemento=(JSONObject)e;
            String nombre,apellido,tipo_dni,numero_dni,domicilio,fecha_nacimiento,telefono;

            nombre=(String) elemento.get("nombre");
            apellido=(String)elemento.get("apellido");
            tipo_dni=(String)elemento.get("tipo_dni");
            numero_dni=(String)elemento.get("numero_dni");
            domicilio=(String)elemento.get("domicilio");
            telefono=(String)elemento.get("telefono");
            fecha_nacimiento=(String)elemento.get("fecha_nacimiento");

           Cliente cliente = new Cliente(numero_dni, nombre, apellido, tipo_dni,telefono, domicilio, fecha_nacimiento);
           clientes_cliente.add(cliente);
        });

        return clientes_cliente;
    }


    private static void verifica_carga(){
        if(clientes==null||vuelos==null||pasajes==null||aeropuertos==null){
            cargar_info();
        }
    }
}

