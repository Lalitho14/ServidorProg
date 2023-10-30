import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class EnviarInformacion extends Thread {
  Socket s;

  public EnviarInformacion(Socket s) {
    this.s = s;
  }

  public void enviarInformacion() {
    try {
      DataOutputStream enviar = new DataOutputStream(s.getOutputStream());
      enviar.writeUTF("Bienvenido\n");
      enviar.writeUTF("Ingresa un nombre de usuario : ");
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void run() {
    enviarInformacion();
  }
}
