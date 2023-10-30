import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor extends Thread {
  ServerSocket servidor;
  Socket s;
  ArrayList<Servidor> sockets;
  ArrayList<String> usuarios;

  Servidor(ServerSocket servidor, Socket s) {
    this.servidor = servidor;
    this.s = s;
    this.usuarios = new ArrayList<String>();
    this.sockets=new ArrayList<Servidor>();
  }

  Servidor() {
    try {
      servidor = new ServerSocket(9999);
      this.usuarios = new ArrayList<String>();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  public void IniciarServidor() {
    System.out.println("Servidor iniciado...");
    try {
      while (true) {
        s = servidor.accept();
        Servidor usr = new Servidor(servidor, s);
        sockets.add(usr);
        usr.start();
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void run() {
//    EnviarInformacion env = new EnviarInformacion(s);
    RecibirInformacion recv = new RecibirInformacion(s);
    try {
      //env.start();
      regUsuario();
      recv.start();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
  public void regUsuario(){
    try{
      DataInputStream recibir = new DataInputStream(s.getInputStream());
      DataOutputStream enviar = new DataOutputStream(s.getOutputStream());
      String mensaje;
      System.out.println("Llego nuevo usuario.");
      mensaje = recibir.readUTF();
      usuarios.add(mensaje);
      //Agregar a Archivo de datos
      enviar.writeUTF(mensaje + " registrado con exito.");
    }catch (Exception e){
      System.err.println(e.getMessage());
    }
  }

}
