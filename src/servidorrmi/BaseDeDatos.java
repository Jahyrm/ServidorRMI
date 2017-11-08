
package servidorrmi;

import RMIInterface.RMIInterface;
import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Jahyr
 */
public class BaseDeDatos extends UnicastRemoteObject implements RMIInterface, Serializable{
    
    public static Scanner in = new Scanner (System.in);
    public static Scanner entero = new Scanner (System.in);
    
    private final int puerto = 3232; 
    
    public static HashMap<String, Categoria> listaCategorias = new HashMap<>();

    public BaseDeDatos() throws RemoteException{}

    public void menu() throws IOException{

        while(true){
            System.out.println("\033[32m***--Bienvenido Administrador--***");
            System.out.println("\033[32m1. Agregar Categoría.");
            System.out.println("\033[32m2. Eliminar Categoría.");
            System.out.println("\033[32m3. Agregar Película.");
            System.out.println("\033[32m4. Eliminar Película.");
            System.out.println("\033[32m5. Salir.");
            System.out.print("\033[32mIngrese la opcion que desea elegir:");
            int opcion = getEntero();
            switch (opcion) {
                case 1:
                    agregarCategoria();
                    break;
                case 2:
                    eliminarCategoria();
                    break;
                case 3:
                    agregarPelicula();
                    break;
                case 4:
                    eliminarPelicula();
                    break;
                case 5:
                    ServidorRMI.salir();
                    break;
                default:
                    System.err.println("Error: No es una opción valida. Intente de nuevo.\n");
                    break;
            }
            
        }
        
        
    }
    
    @Override
    public String imprimirLista() throws RemoteException {
        StringBuilder sb = new StringBuilder ();
        
        for (Map.Entry<String, Categoria> categoriaActual : listaCategorias.entrySet()) {
            sb.append("Categoría ").append(categoriaActual.getValue().getNombreCategoria()).append(":\n");
            
            for (Map.Entry<String, Pelicula> peliculaActual : 
                    categoriaActual.getValue().getListaPeliculas().entrySet()){
                sb.append(categoriaActual.getValue().getCodigo()).append("-")
                        .append(peliculaActual.getValue().getCodigo())
                        .append(": ").append(peliculaActual.getValue().getTitulo())
                        .append(".\n");
            }
            sb.append("\n");
        }
        return sb.toString();       
    }
    
    @Override
    public String solicitarInfoPelicula(String codigoCategoria, String codigoPelicula) throws RemoteException {
        StringBuilder sb = new StringBuilder ();
        if (listaCategorias.containsKey(codigoCategoria)){
            Map<String, Pelicula> listaPeliculas = listaCategorias.get(codigoCategoria).getListaPeliculas();
            if(listaPeliculas.containsKey(codigoPelicula)){
                Pelicula pelicula = listaPeliculas.get(codigoPelicula);
                sb.append("Título: ").append(pelicula.getTitulo()).append("\n")
                        .append("Sinopsis: ").append(pelicula.getSinopsis())
                        .append("\n");
            } else {
                sb.append("Código incorrecto.");
            }
        } else {
            sb.append("Código incorrecto.");
        }
        return sb.toString();
    }

    @Override
    public String play(String codigoCategoria, String codigoPelicula) throws RemoteException {
        StringBuilder sb = new StringBuilder ();
        if (listaCategorias.containsKey(codigoCategoria)){
            Map<String, Pelicula> listaPeliculas = listaCategorias.get(codigoCategoria).getListaPeliculas();
            if(listaPeliculas.containsKey(codigoPelicula)){
                Pelicula pelicula = listaPeliculas.get(codigoPelicula);
                sb.append("http://").append(pelicula.getIp())
                        .append(":9999/play?input=D:\\videos\\")
                        .append(pelicula.getNombreArchivo());
            } else {
                sb.append("Código incorrecto.");
            }
        } else {
            sb.append("Código incorrecto.");
        }
        return sb.toString();
    }
    
    @Override
    public String getTitulo(String codigoCategoria, String codigoPelicula) throws RemoteException {
        String titulo = "";
        Map<String, Pelicula> listaPeliculas = listaCategorias.get(codigoCategoria).getListaPeliculas();
        Pelicula pelicula = listaPeliculas.get(codigoPelicula);
        titulo = pelicula.getTitulo();
        return titulo;
    }
    
    public static void agregarCategoria(){
        System.out.print("\033[32mIngrese el código de la categoría:");
        String codigo = getCadena();
        if (listaCategorias.containsKey(codigo)){
            System.err.print("Error: Ya existe una categoría con ese código.\n");
        }else{
            System.out.print("\033[32mIngrese el nombre de la categoría: ");
            String nombreCategoria = getCadena();
            Categoria nuevaCategoria = new Categoria(codigo, nombreCategoria);
            listaCategorias.put(codigo, nuevaCategoria);
            System.out.println("");
        }  
    }
    
    public static void eliminarCategoria(){
        System.out.print("\033[32mIngrese el código de la categoria a eliminar:");
        String codigo = getCadena();
        if (listaCategorias.containsKey(codigo)){
            listaCategorias.remove(codigo);
            System.out.println("\033[33mEliminación correcta.\n");
        }else{
            System.err.println("Error: No hay ninguna categoría con ese código.\n");
        } 
    }
    
    public static void agregarPelicula(){
        System.out.print("\033[32mIngrese el código de la categoría: ");
        String codigoCategoria = getCadena();
        if(listaCategorias.containsKey(codigoCategoria)){
            Categoria categoriaActual = listaCategorias.get(codigoCategoria);
            categoriaActual.agregarPelicula();
        } else {
            System.err.println("Error: No existe una categoría con ese código.\n");
        }
    }
    
    public static void eliminarPelicula(){
        System.out.print("\033[32mIngrese el código de la categoría: ");
        String codigoCategoria = getCadena();
        if(listaCategorias.containsKey(codigoCategoria)){
            Categoria categoriaActual = listaCategorias.get(codigoCategoria);
            categoriaActual.eliminarPelicula();
        } else {
            System.err.println("Error: No existe una categoría con ese código.\n");
        }
    }
    public static String getCadena(){
        Scanner ing=new Scanner(System.in);
        String cadena=ing.nextLine();
        return cadena;
    }
    
    public static int getEntero(){
        Scanner ing=new Scanner(System.in);
        return ing.nextInt();
    }
    
}
