import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Введите хост: ");
            String host = sc.nextLine().trim();
            System.out.print("Введите порт: ");
            String port = sc.nextLine().trim();
            String input;
            Client client = new Client(host, Integer.parseInt(port));
            if (client.connect()) {
                while (client.isConnected()) {
                    System.out.println("Введите команду: ");
                    input = client.getIn().readLine();
                    if (input.split(" ").length > 1 && input.split(" ")[0].contains("execute_script")){
                        BufferedReader br = new BufferedReader(new FileReader(input.split(" ")[1]));
                        String line;
                        while ((line = br.readLine()) != null) {
                            client.get(line);
                            client.checkConnection(line);
                        }
                    }
                    client.get(input);
                    client.checkConnection(input);
                }
                client.close();
            }
        } catch (Throwable e) {
            System.err.println("Фатальная ошибка при работе клиента: " + e.getMessage());
        }
    }
}