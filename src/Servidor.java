import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
  private static ServerSocket servidor;
  static ArrayList<Socket> clientes;

  public Servidor(){
    try{
      servidor=new ServerSocket(9999);
      clientes = new ArrayList<>();
    } catch (Exception e){
      System.err.println(e.getMessage());
    }
  }

  public void IniciarServidor(){
    try{
      System.out.println("Servidor Iniciado");
      while (true){
        Socket cliente = servidor.accept();
        clientes.add(cliente);
        RecibirInformacion usr = new RecibirInformacion(cliente);
        usr.start();
      }
    }catch (Exception e){
      System.err.println(e.getMessage());
    }
  }

}
