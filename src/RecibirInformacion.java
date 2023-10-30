import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class RecibirInformacion extends Thread {
  private Socket cliente;
  private DataOutputStream enviar;
  private DataInputStream leer;

  public RecibirInformacion(Socket cliente) {
    this.cliente = cliente;
    try {
      this.enviar = new DataOutputStream(cliente.getOutputStream());
      this.leer = new DataInputStream(cliente.getInputStream());
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  private void manejarChat(String mensaje) {
//    String mensaje;
    for (Socket c : Servidor.clientes) {
      if (c != this.cliente) {
        try {
          DataOutputStream enviar_ = new DataOutputStream(c.getOutputStream());
//          DataInputStream leer_ = new DataInputStream(c.getInputStream());
//          mensaje = leer_.readUTF();
//          System.out.println(mensaje);
          enviar_.writeUTF(mensaje);
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      }
    }
  }

  @Override
  public void run() {
    try {
      String mensaje;
      System.out.println("Se unio al server");
      while (true) {
        BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
        mensaje = leer.readLine();
        manejarChat(mensaje);
      }
//      Servidor.clientes.remove(this.cliente);
//      cliente.close();
//      System.out.println("Se desconecto");
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

}
