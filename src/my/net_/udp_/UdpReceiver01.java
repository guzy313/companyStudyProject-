package my.net_.udp_;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpReceiver01 {

    @Test
    public void udpReceiver() throws IOException{
        DatagramSocket socket = new DatagramSocket(9999);

        byte[] bytes = new byte[1024];

        DatagramPacket packet = new DatagramPacket(bytes,bytes.length);

        System.out.println("接收端等待接收数据..");

        socket.receive(packet);

        int len = packet.getLength();
        byte[] data = packet.getData();

        String str;
        str = new String(data,0,len);

        System.out.println("接收到的数据:");
        System.out.println(str);
        socket.close();

    }



    @Test
    public void udpReceiver01() throws IOException {
        DatagramSocket socket = new DatagramSocket(9999);//监听9999端口，接收数据包

        //创建空包用来接收
        byte[] bytes = new byte[1024];
        DatagramPacket packet = new DatagramPacket(bytes,bytes.length);
        System.out.println("receiver 准备接收..");
        socket.receive(packet);//接收动作

        String str;
        str = new String(packet.getData(),0,packet.getLength());
        System.out.println("接收到的数据:");
        System.out.println(str);


        packet.setAddress(InetAddress.getByName("172.16.2.199"));
        packet.setPort(6666);
        packet.setData("hello sender".getBytes());
        socket.send(packet);
        socket.close();



    }

}
