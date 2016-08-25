package jmguisado.iesnervion.es.macroidclient.Connectivity;

import java.net.Socket;

/**
 * Created by JMGG on 01/06/2016.
 *
 * Singleton que guarda el estado de la conexión actual, en caso de que exista conexión
 * se devuelve el socket en caso contrario null.
 */
public class MacroidConnection {

    // Cosas del singleton

    private static MacroidConnection _instance;

    private MacroidConnection() {
        currentConnection = null;
    }

    public synchronized static MacroidConnection Instance() {
        if (_instance == null)
        {
            _instance = new MacroidConnection();
        }
        return _instance;
    }

    // Miembros no estáticos

    private Socket currentConnection;

    public Socket getCurrentConnection() {
        return currentConnection;
    }

    public void setCurrentConnection(Socket currentConnection) {
        this.currentConnection = currentConnection;
    }




}
