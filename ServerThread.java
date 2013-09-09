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
import java.net.Socket;

/**
 *
 * @author xeon
 */
public class ServerThread extends Thread {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ServerThread(Socket socket) throws IOException {
        ServerDaemon.CURRENT_THREADS++;
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader
                (this.socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter
                (this.socket.getOutputStream())),true);
    }
    @Override
    public void run() {
        try {
            while(true) {
                String temp = in.readLine();
                if(temp != null) {
                    if(temp.equals("bye")) {
                        out.print("goodbye");
                        break;
                    } else {
                        out.print("Hello,I am server");
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                socket.close();
                out.close();
                in.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        ServerDaemon.CURRENT_THREADS--;
    }
}