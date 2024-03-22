import java.io.BufferedReader;
import java.io.IOException;

public class CLientListner implements Runnable
{
    BufferedReader reader;

    public void setActive(boolean active) {
        this.active = active;
    }

    boolean active = true;
    public CLientListner(BufferedReader in)
    {
        this.reader = in;
    }
    public void run()
    {
        String line;

            try {
                while (active && !(line = reader.readLine()).equalsIgnoreCase("exit")) {
                    if (line.startsWith("lst:")) {
                        line = line.substring(line.indexOf("lst:") + 4);
                        String users[] = line.split("~");
                        System.out.println("Online Users: ");
                        for (String u : users) {
                            System.out.println(u);
                        }
                    } else {
                        System.out.println(line);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


    }
}
