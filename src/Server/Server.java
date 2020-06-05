package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {

  public static final int PORT = 4000;
  public static boolean stopped = false;
  private static ArrayList<ClientHandler> clients;

  public static void main(String[] args) {
    clients = new ArrayList<ClientHandler>();
    MultiplayerGameManager multiplayerGameManager = new MultiplayerGameManager();

    System.out.println("Server starting...");

    try {
      System.out.println("Socket open");
      ServerSocket server = new ServerSocket(PORT);
      System.out.println("Status: OK");

      int clientIdCounter = 0;

      while (!stopped) {
        try {
          // Instantiates a new ClientHandler thread and assigns it the socket
          ClientHandler client = new ClientHandler(clientIdCounter, server.accept(),
              multiplayerGameManager);
          System.out.println("New client connected. ID: " + clientIdCounter++);
          clients.add(client);
          client.start();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public static ArrayList<ClientHandler> getClients() {
    return clients;
  }
}
