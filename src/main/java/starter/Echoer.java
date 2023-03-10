package starter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Echoer extends Thread{
    private Socket socket;
    public Echoer(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            while (true){
                String echoString = bufferedReader.readLine();
                System.out.println("Received client input: " + echoString);

                if(echoString.equals("exit")){
                    break;
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e){
                    System.out.println("Thread interupted");
                }

                printWriter.println(echoString);
            }
        } catch (IOException e){
            System.out.println("Oops: " + e.getMessage());
        } finally {
            try{
                socket.close();
            } catch (IOException e){
                e.getMessage();
            }
        }
    }
}
