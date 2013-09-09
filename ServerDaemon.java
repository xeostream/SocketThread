/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.ServerSocket;

/**
 *
 * @author xeon
 */
public class ServerDaemon {
    
    public static final int PORT = 8080;
    private static final int MAX_THREADS = 10;
    public static int CURRENT_THREADS = 0;
    private ServerSocket server;
    public ServerDaemon() {
        System.out.println("Server start...");
        try {
            server = new ServerSocket(PORT);
            if(CURRENT_THREADS < MAX_THREADS) {
                ServerThread thread = new ServerThread(server.accept());
                thread.start();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                server.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        ServerDaemon server = new ServerDaemon();
    }
}