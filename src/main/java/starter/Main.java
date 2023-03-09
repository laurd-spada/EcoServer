package starter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(5000)){
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            while(true){
                String echoString = bufferedReader.readLine();
                if(echoString.equals("exit")){
                    break;
                }
                printWriter.println("Echo from server : " + echoString);
                System.out.println(echoString);
            }
        } catch (IOException e){
            System.out.println("Server exception " + e.getMessage());
        }
    }
}
