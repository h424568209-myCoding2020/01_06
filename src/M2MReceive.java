import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class M2MReceive  extends Thread {
    Socket socket;
    public M2MReceive(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            //构建输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //不断的接收信息
            while (true) {
                String info = null;
                //接收信息
                if ((info = br.readLine()) != null) {
                    System.out.println(info);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
