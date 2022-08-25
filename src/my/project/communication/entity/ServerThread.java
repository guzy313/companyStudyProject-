package my.project.communication.entity;

import java.net.Socket;
import java.util.Vector;

public class ServerThread{
    private Vector<Socket> sockets = new Vector<>();


    public Vector<Socket> getSockets() {
        return sockets;
    }

    public void setSockets(Vector<Socket> sockets) {
        this.sockets = sockets;
    }
}
