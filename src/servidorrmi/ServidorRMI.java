/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorrmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Patricks
 */
public class ServidorRMI {
    public static void main(String[] args) {
        int puerto =3232;
        try {
            String dirIP = "192.168.1.101";
            //System.out.println(dirIP);
            System.out.println("Escuchando en..."+dirIP+":"+puerto);
            System.setProperty("java.rmi.server.hostname", dirIP);
            BaseDeDatos q= new BaseDeDatos();        
           
            Registry registry = LocateRegistry.createRegistry(puerto); 
            
           registry.bind("operacionservidor", q);
            q.mainuno();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
