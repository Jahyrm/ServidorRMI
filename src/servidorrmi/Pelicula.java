package servidorrmi;

import java.io.Serializable;

/**
 *
 * @author Jahyr
 */
public class Pelicula implements Serializable {
    private String codigo;
    private String titulo;
    private String sinopsis;
    private String categoria;
    private String nombreArchivo;
    private String ip;

    
    Pelicula(String codigo, String titulo, String sinopsis, String categoria, 
            String nombreArchivo, String ip){
        this.titulo = titulo;
        this.codigo = codigo;
        this.sinopsis = sinopsis;
        this.categoria = categoria;
        this.nombreArchivo = nombreArchivo;
        this.ip = ip;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
