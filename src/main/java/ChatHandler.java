import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;

public class ChatHandler implements Runnable
{
    private PrintWriter out;
    private BufferedReader in;
    public static HashMap<String, ChatHandler> online;
    private String username;
    public ChatHandler(Socket socket) throws IOException {
        System.out.println("Thread created");
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        if(online == null)
        {
            online = new HashMap<>();
        }
    }
    public void sendMessage(String msg)
    {
        out.println(msg);
    }
    public void run()
    {
        String input = "";
        while(true)
        {
            try {
                if (!(input = in.readLine()).equalsIgnoreCase("end"))
                {
                    if(input.startsWith("login"))
                    {
                        username = input.substring(input.indexOf(" ")+1).trim();
                        online.put(username, this);
                        sendToAll(username +" has joined the chat!");
                    }
                    else if(input.startsWith("bc"))
                    {
                        String msg = input.substring(input.indexOf(" ")+1).trim();
                        sendToAll(msg);
                    }
                    else if(input.startsWith("pm"))
                    {
                        int firstSpace = input.indexOf(" ");
                        int secondSpace = input.indexOf(" ", firstSpace + 1);
                        String username = input.substring(firstSpace, secondSpace).trim();
                        String msg = input.substring(secondSpace+1);
                        if(online.containsKey(username)) {
                            online.get(username).sendMessage(this.username + ": " + msg);
                        }
                        else
                        {
                            sendMessage(username +" is not online");
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void sendToAll(String msg)
    {
        for(ChatHandler c: online.values())
        {
            c.sendMessage(msg);
        }
    }
}
