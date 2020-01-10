import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class M2MServer {
    private static ArrayList<Socket> list = new ArrayList<>();
    public static void main(String[] args) {
        //上线用户
        Integer cnum=0;
Socket socket = null;
        //用户
        String ip = null;
        try {
            //构建服务器对象
            ServerSocket ss = new ServerSocket(1234);
            //构建用户集合
            list = new ArrayList<>();
            System.out.println("等待客户端连接.....");
            boolean flag = true;
            //循环监听
            while(true) {
                //上线用户
                 socket = new Socket();
                socket = ss.accept();
                list.add(socket);
                //获取 Socket IP
                ip = socket.getInetAddress().getHostAddress();
                System.err.println(ip + " 用户已连接 , 当前在线用户为: " + list.size() + "人 !");
                if (cnum!=list.size()){
                    cnum=list.size();
                    M2MSend send=new M2MSend(socket);
                    send.start();
                }
            }
        } catch (IOException e) {
            //用户下线
            list.remove(socket);
            System.err.println(ip + " 已断开连接 , 当前在线人数为: " + list.size() + " 人 !");
        }
    }
    public static ArrayList<Socket> getList() {
        return list;
    }
}
