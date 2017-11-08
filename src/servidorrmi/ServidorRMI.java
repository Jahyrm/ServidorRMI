package servidorrmi;

import java.io.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;

/**
 * @author Patricks
 */
public class ServidorRMI implements Serializable{

    private static BaseDeDatos gestor;
    private static File objetoSerializado;

    public static void main(String[] args) {
        //int puerto =3232;
        boolean validos = false;
        try {
            gestor = null;

            //String dirIP = "192.168.1.101";
            int confirmacion = 0;
            gestor = new BaseDeDatos();
            String dirIP = JOptionPane.showInputDialog(null, "Ingrese la dirección ip del servidor", "Configuración inicial", JOptionPane.QUESTION_MESSAGE);
            int puerto = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el puerto del servidor", "Configuración inicial", JOptionPane.QUESTION_MESSAGE));
            File archivoSerializacion = new File("archivosSerializados");
            objetoSerializado = new File("archivosSerializados/objetoSerializado.dat");
            if (objetoSerializado.exists()) {
                confirmacion = JOptionPane.showConfirmDialog(null, "Desea utilizar los archivos guardados.");
                if (confirmacion == 0) {
                    gestor = getSerializacion(objetoSerializado);
                }
            } else {
                archivoSerializacion.mkdir();
                objetoSerializado.createNewFile();
            }
            System.out.println("\033[32mEscuchando de " + dirIP + " con el puerto :" + puerto);
            System.setProperty("java.rmi.server.hostname", dirIP);
            Registry registry = LocateRegistry.createRegistry(puerto);
            registry.bind("operacionservidor", gestor);
            gestor.menu();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BaseDeDatos getSerializacion(File archivo) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream extraerObjeto = new ObjectInputStream(new FileInputStream(archivo));
        BaseDeDatos objeto = (BaseDeDatos) extraerObjeto.readObject();
        extraerObjeto.close();
        return objeto;
    }

    public static boolean setSerializacion() throws FileNotFoundException, IOException {
        boolean condicion = false;
        ObjectOutputStream archivoSerializar = new ObjectOutputStream(new FileOutputStream(objetoSerializado));
        archivoSerializar.writeObject(gestor);
        archivoSerializar.close();
        return condicion = true;
    }

    public static void salir() throws IOException {
        setSerializacion();
        System.exit(0);
    }

}
