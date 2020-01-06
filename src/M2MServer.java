import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class M2MServer {
    private static ArrayList<Socket> list = new ArrayList<>();
    public static void main(String[] args) {
        //上线用户
        Socket socket = null;
        //用户
        String ip = null;
        try {
            //构建服务器对象
            ServerSocket ss = new ServerSocket(1234);
            //构建 用户集合
            list = new ArrayList<>();

            System.out.println("等待客户端连接.....");
            //循环监听
            while(true){
                //上线用户
                socket = ss.accept();
                list.add(socket);
                //获取 Socket IP
                ip = socket.getInetAddress().getHostAddress();
                System.err.println( ip + " 用户已连接 , 当前在线用户为: " + list.size() + "人 !" );
                //构建 发送信息线程
                M2MSend send = new M2MSend(socket);
                send.start();
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
