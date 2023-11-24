import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {
    public static int PORT;
    public static String docroot;

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Please include a port number and the docroot for index.html");
            System.exit(1);
        }

        PORT = Integer.parseInt(args[0]);
        docroot = "task02/" + args[1];

        try (ServerSocket serverSocket = new ServerSocket(PORT);) {

            System.out.printf("Server is listening on port %d\n", PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String[] response = br.readLine().split(" ");
                String fileName = response[1];
                if (fileName.equals("/")) {
                    fileName = "/index.html";
                }

                File file = new File(docroot + fileName);

                if (file.exists()) {
                    StringBuilder sb = new StringBuilder();
                    try (BufferedReader htmlReader = new BufferedReader(new FileReader(file))) {
                        String str;
                        while ((str = htmlReader.readLine()) != null) {
                            sb.append(str);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String html = sb.toString();

                    try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);) {
                        System.out.println("Navigating to " + file);
                        out.println("HTTP/1.1 200 OK");
                        out.println("Content-Type: text/html");
                        out.println("\r\n");
                        out.println(html);
                    }
                } else {
                    try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);) {
                        out.println("HTTP/1.1 404 Not Found");
                        out.println("Content-Type: text/html");
                        out.println("\r\n");
                        out.printf("Resource %s not found", fileName);
                    }
                }
            }

        }
    }
}
