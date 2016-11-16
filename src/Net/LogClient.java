package Net;
import java.io.*;
import java.rmi.*;
/**
 * Klasa klienta (LogClient)
 * @author Jacek Polak
 * @since 10.10.16r
 * @version 2.0
 */
public class LogClient implements Serializable{

    public static LogInterface logInt;

    /**
     * Konstruktor klasy klienta, rejestruje on się na danym serwerze RMI
     * @param ip
     * @param port
     */
    public LogClient(String ip, String port){
        super();
        try{
            logInt = (LogInterface) Naming.lookup("rmi://" + ip + ":" + port + "/LogServer");
            System.err.println("Connected to RMI server with IP: " + ip);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
