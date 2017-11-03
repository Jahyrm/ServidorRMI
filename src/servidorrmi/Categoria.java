/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorrmi;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Jahyr
 */
public class Categoria implements Serializable {
    public static Scanner in = new Scanner (System.in);

    private String codigo;
    private String nombreCategoria;
    private Map<String, Pelicula> listaPeliculas;

    public Categoria(String codigo, String nombreCategoria) {
        this.codigo = codigo;
        this.nombreCategoria = nombreCategoria;
        listaPeliculas = new HashMap<>();
    }

    public void agregarPelicula() {
        String codigo, titulo, sinopsis, nombreArchivo, ip;
        System.out.println("Código de Película: ");
        codigo = in.nextLine();
        if (listaPeliculas.containsKey(codigo)){
            System.err.print("Error: Ya existe una película con ese código.\n");
        }
        else{
            System.out.println("Título de Película: ");
            titulo = in.nextLine();
            System.out.println("Sinopsis: ");
            sinopsis = in.nextLine();
            System.out.println("Nombre del Archivo: ");
            nombreArchivo = in.nextLine();
            System.out.println("Dirección IP que contien la Película:");
            ip = in.nextLine();
            Pelicula nuevaPelicula = new Pelicula(codigo, titulo, sinopsis, nombreCategoria, nombreArchivo, ip);
            listaPeliculas.put(codigo, nuevaPelicula);
            System.out.println("Película agregada correctamente.\n");
        }  
        
        
    }
    
    public void eliminarPelicula(){
        System.out.println("Ingrese el código de la película a eliminar:");
        String codigo = in.nextLine();
        if (listaPeliculas.containsKey(codigo)){
            listaPeliculas.remove(codigo);
        }
        else{
            System.err.print("Error: ");
            System.out.println(" No hay ninguna película con ese código.");  
        }  
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public Map<String, Pelicula> getListaPeliculas() {
        return listaPeliculas;
    }

    public void setListaPeliculas(Map<String, Pelicula> listaPeliculas) {
        this.listaPeliculas = listaPeliculas;
    }
    
    
    
}
