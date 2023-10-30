import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class RecibirInformacion extends Thread {
  Socket s;

  public RecibirInformacion(Socket s) {
    this.s = s;
  }

  public void recibirInformacion() {
    try {
      while (true) {
        DataInputStream recibir = new DataInputStream(s.getInputStream());
        DataOutputStream enviar = new DataOutputStream(s.getOutputStream());
        String mensaje;
        mensaje = recibir.readUTF();
        System.out.println(mensaje);
        enviar.writeUTF(mensaje);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void run() {
    recibirInformacion();
  }
}
