import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class M2MClient {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("10.188.35.78",1234);
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            M2MReceive receive = new M2MReceive(s);
            receive.start();
            System.out.println("请输入： ");
            while(true){
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String   info ;
                if((info = br.readLine())!=null){
                    pw.println(info);
                    pw.flush();
                }
            }
        }catch ( IOException e){
            e.printStackTrace();
        }
    }
}
