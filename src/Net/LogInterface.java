package Net;
import java.rmi.*;
/**
 * Interfejs serwera, w którym zawarte są metody udostępniane połączonemu z nim klientowi.
 * @author Jacek Polak
 * @since 10.10.16r
 * @version 2.0
 */
public interface LogInterface extends Remote{
    public void insertIntoDB(String table, String[] data) throws RemoteException;
    public void createNewTypeDB(String table, String... data) throws RemoteException;

}
