import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8888))
        {
            Scanner kb = new Scanner(System.in);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            CLientListner listner = new CLientListner(reader);
            Thread t = new Thread(listner);
            t.start();
            String usersInput = "";
            while(!usersInput.equalsIgnoreCase("exit"))
            {
                System.out.print(">");
                usersInput = kb.nextLine();
                out.println(usersInput);

            }

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
