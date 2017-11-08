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
 * @author Jahyr
 */

public class Categoria implements Serializable {
    
    public static String getCadena(){
        Scanner ingreso = new Scanner (System.in);
        String cadena=ingreso.nextLine();
        return cadena;
    }
    
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
        System.out.print("\033[32mCódigo de la Película: ");
        codigo = getCadena();
        if (listaPeliculas.containsKey(codigo)){
            System.err.print("Error: Ya existe una película con ese código.\n");
        }
        else{
            System.out.print("\033[32mTítulo de Película: ");
            titulo = getCadena();
            System.out.print("\033[32mSinopsis: ");
            sinopsis = getCadena();
            System.out.print("\033[32mNombre del Archivo: ");
            nombreArchivo = getCadena();
            System.out.print("\033[32mDirección IP que contien la Película:");
            ip = getCadena();
            Pelicula nuevaPelicula = new Pelicula(codigo, titulo, sinopsis, nombreCategoria, nombreArchivo, ip);
            listaPeliculas.put(codigo, nuevaPelicula);
            System.out.println("\033[33mPelícula agregada correctamente.\n");
        }     
    }
    
    public void eliminarPelicula(){
        System.out.print("\033[32mIngrese el código de la película a eliminar:");
        String codigo = getCadena();
        if (listaPeliculas.containsKey(codigo)){
            listaPeliculas.remove(codigo);
            System.out.print("\033[32mPelicula removida con exito.");
        }
        else{
            System.err.print("Error: No hay ninguna película con ese código. ");
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
