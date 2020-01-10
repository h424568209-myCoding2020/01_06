import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class M2MSend extends Thread {
    private ArrayList<Socket> list = M2MServer.getList();
    //当前用户
    private Socket s;
    public M2MSend(Socket s){
        this.s = s;
    }

    public void run(){
        String ip = s.getInetAddress().getHostAddress();
        try {
            //不断的读取写出数据
            while(true){
                BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String line;
                if ((line = reader.readLine()) != null) {

                        //获取对象的输出流
                        PrintWriter pw;
                        pw = new PrintWriter(s.getOutputStream());
                        //写入信息
                        pw.println("服务器说: " + line);
                        pw.flush();

                    System.out.println(ip + ":" + line);

                }
            }
        } catch (IOException e1) {
            //用户下线
            list.remove(s);
            System.err.println(ip + " 已下线 , 当前在线人数为: " + list.size() + " 人 !");
        }
    }
}
