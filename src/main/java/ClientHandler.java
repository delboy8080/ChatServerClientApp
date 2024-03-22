import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable
{

    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket) throws IOException {
        System.out.println("Thread created");
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run()
    {
        String input="";
        while(true)
        {
            System.out.println("Waiting input");
            try {
                if (!(input = in.readLine()).equalsIgnoreCase("exit"))
                {
                    System.out.println(input);
                    String words[] = input.split(" ");

                    if(words.length==3) {
                        String cmd = words[0];
                        int a = Integer.parseInt(words[1]);
                        int b = Integer.parseInt(words[2]);
                        if(cmd.equalsIgnoreCase("add"))
                        {
                            out.println(a+b);
                        }
                        else if(cmd.equalsIgnoreCase("subtract"))
                        {
                            out.println(a-b);
                        }
                        else if(cmd.equalsIgnoreCase("multiply"))
                        {
                            out.println(a*b);
                        }
                        else if(cmd.equalsIgnoreCase("divide"))
                        {
                            out.println(a/b);
                        }
                    }
                    else {
                        out.println("Goodbye");
                        break;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
