package my.net_.test;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class socketServerAndClient01 {


    @Test
    public void socketServer01() throws IOException{
        System.out.println(InetAddress.getLocalHost().getHostAddress());
        ServerSocket ss = new ServerSocket(9999);
        Socket socket = ss.accept();
        BufferedReader  br = new BufferedReader(new InputStreamReader(socket.getInputStream()));


    }

    @Test
    public void socketClient01() throws IOException{
        Socket socket = new Socket(InetAddress.getLocalHost(),9999);
    }

}
