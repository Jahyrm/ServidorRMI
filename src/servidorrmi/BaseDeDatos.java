/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorrmi;

import RMIInterface.RMIInterface;
import java.rmi.server.UnicastRemoteObject;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Jahyr
 */
public class BaseDeDatos extends UnicastRemoteObject implements RMIInterface, Serializable{
    
    public static Scanner in = new Scanner (System.in);
    public static Scanner entero = new Scanner (System.in);
    
    private static final int puerto = 3232; 
    
    public static HashMap<String, Categoria> listaCategorias = new HashMap<>();

    public BaseDeDatos() throws RemoteException{}

    public void mainuno(){
        // TODO code application logic here
        while(true){
            System.out.println("Bienvenido Administrador:");
            System.out.println("1. Agregar Categoría.");
            System.out.println("2. Eliminar Categoría.");
            System.out.println("3. Agregar Película.");
            System.out.println("4. Eliminar Película.");
            int opcion = entero.nextInt();
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
                default:
                    System.err.println("Error: No es una opción valida. Intenta de nuevo.\n");

            }
        }
    }
    
    @Override
    public String imprimirLista() throws RemoteException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        
        //listaCategorias.forEach((k,v) -> System.out.println("Key: " + k + ": Value: " + v));
        
        //System.out.println("Imprimo LISTA");
        //return "LISTA";
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String titulo = "";
        Map<String, Pelicula> listaPeliculas = listaCategorias.get(codigoCategoria).getListaPeliculas();
        Pelicula pelicula = listaPeliculas.get(codigoPelicula);
        titulo = pelicula.getTitulo();
        return titulo;
    }
    
    public static void agregarCategoria(){
        System.out.println("Ingrese el código de la categoría:");
        String codigo = in.nextLine();
        if (listaCategorias.containsKey(codigo)){
            System.err.print("Error: Ya existe una categoría con ese código.\n");
        }
        else{
            System.out.println("Ingrese el nombre de la categoría: ");
            String nombreCategoria = in.nextLine();
            Categoria nuevaCategoria = new Categoria(codigo, nombreCategoria);
            listaCategorias.put(codigo, nuevaCategoria);
            System.out.println("");
        }  
    }
    
    public static void eliminarCategoria(){
        System.out.println("Ingrese el código de la categoria a eliminar:");
        String codigo = in.nextLine();
        if (listaCategorias.containsKey(codigo)){
            listaCategorias.remove(codigo);
            System.out.println("Eliminación correcta.\n");
        }
        else{
            System.err.println("Error: No hay ninguna categoría con ese código.\n");
        } 
    }
    
    public static void agregarPelicula(){
        System.out.println("Ingrese el código de la categoría: ");
        String codigoCategoria = in.nextLine();
        if(listaCategorias.containsKey(codigoCategoria)){
            Categoria categoriaActual = listaCategorias.get(codigoCategoria);
            categoriaActual.agregarPelicula();
        } else {
            System.err.println("Error: No existe una categoría con ese código.\n");
        }
    }
    
    public static void eliminarPelicula(){
        System.out.println("Ingrese el código de la categoría: ");
        String codigoCategoria = in.nextLine();
        if(listaCategorias.containsKey(codigoCategoria)){
            Categoria categoriaActual = listaCategorias.get(codigoCategoria);
            categoriaActual.eliminarPelicula();
        } else {
            System.err.println("Error: No existe una categoría con ese código.\n");
        }
    }
}
