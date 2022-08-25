package my.net_.udp_;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.*;

public class UdpSender01 {

    @Test
    public void udpSender() throws IOException{
        DatagramSocket socket = new DatagramSocket(6666);

        byte[] bytes = new byte[1024];


        bytes = "hello".getBytes();


        DatagramPacket packet = new DatagramPacket(bytes,bytes.length,InetAddress.getByName("172.16.2.199"),9999);
       /* packet.setData(bytes,0,bytes.length);
        packet.setAddress(InetAddress.getLocalHost());
        packet.setPort(9999);*/
        socket.send(packet);
        System.out.println("发送成功");
        socket.close();

    }


    @Test
    public void udpSender01() throws IOException {
        //创建节点
        DatagramSocket socket = new DatagramSocket(6666);

        //创建数据包
        byte[] bytes = new byte[1024];
        bytes = "hello ".getBytes();
        DatagramPacket packet = new DatagramPacket(bytes,bytes.length,InetAddress.getByName("172.16.2.199"),9999);//对方端口

        socket.send(packet);

        System.out.println("准备接收receiver返回..");

        //接收receiver返回
        socket.receive(packet);
        String str;
        str = new String(packet.getData(),0,packet.getLength());
        System.out.println("receiver 回复:");
        System.out.println(str);




    }


}
