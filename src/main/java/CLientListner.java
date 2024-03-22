import java.io.BufferedReader;
import java.io.IOException;

public class CLientListner implements Runnable
{
    BufferedReader reader;
    public CLientListner(BufferedReader in)
    {
        this.reader = in;
    }
    public void run()
    {
        String line;
        while(true)
        {
            try {
                while (!(line = reader.readLine()).equalsIgnoreCase("exit"))
                    System.out.println(line + "\n>");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
