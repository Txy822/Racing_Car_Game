package Server;

import static Server.NetworkHelper.implementsInterface;

import Database.DatabaseControl;
import Server.Request.CloseConnectionRequest;
import Server.Request.DatabaseRequest;
import Server.Request.MultiplayerRequest;
import Server.Request.Request;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {

  private int id;
  private Socket client;
  private boolean intialised;
  private MultiplayerGameManager multiplayerGameManager;
  private ArrayList<Request> requests;

  public ClientHandler(int id, Socket client, MultiplayerGameManager multiplayerGameManager) {
    this.id = id;
    this.client = client;
    this.multiplayerGameManager = multiplayerGameManager;
    this.intialised = false;
  }

  @Override
  public void run() {
    try {
      ObjectInputStream fromClient = new ObjectInputStream(client.getInputStream());
      ObjectOutputStream toClient = new ObjectOutputStream(client.getOutputStream());

      Request request;

      requests = new ArrayList<>();

      while ((request = (Request) fromClient.readObject()) != null) {
        requests.add(request);

        // Prevents EOF Exception when client closes connection
        if (request.getClass() == CloseConnectionRequest.class) {
          break;
        }

        // Checks if request requires DatabaseControl injection
        if (implementsInterface(request, DatabaseRequest.class)) {
          ((DatabaseRequest) request).setDatabase(new DatabaseControl());
        }

        // Checks if the request requires MultiplayerGameManager injection
        if (implementsInterface(request, MultiplayerRequest.class)) {
          ((MultiplayerRequest) request).setMultiplayerGameManager(multiplayerGameManager);
        }

        // Handles requests
        toClient.writeObject(request.handleRequest());
      }

      toClient.close();

    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public ArrayList<Request> getRequests() {
    return requests;
  }
}
