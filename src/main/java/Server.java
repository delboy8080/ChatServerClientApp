import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server
{

    public static void main(String[] args) {
        try {

            ServerSocket socket = new ServerSocket(8888);

            while(true)
            {
                Socket client = socket.accept();
                ChatHandler handler = new ChatHandler(client);
                Thread t = new Thread(handler);
                t.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
