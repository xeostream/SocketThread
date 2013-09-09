/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author xeon
 */
public class ClientThread extends Thread {
    
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    
    public ClientThread() throws UnknownHostException, IOException {
        InetAddress address = InetAddress.getLocalHost();
        socket = new Socket(address,ServerDaemon.PORT);
        in = new BufferedReader(new InputStreamReader
                (this.socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter
                (this.socket.getOutputStream())),true);
    }
    
    @Override
    public void run() {
        try {
            String temp = Thread.currentThread().getName();
            out.println(temp);
            while(true) {
                System.out.println(in.readLine());
                Thread.sleep(3000);
                out.println("bye");
                System.out.println(in.readLine());
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
                socket.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws UnknownHostException, IOException {
        for(int i = 0; i < 12; i++) {
            ClientThread client = new ClientThread();
            client.start();
        }
    }
}