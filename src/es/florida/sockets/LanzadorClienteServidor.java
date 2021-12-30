package es.florida.sockets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LanzadorClienteServidor {

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		// Creacion de dos procesos independientes que seran uno para gestionar la clase Cliente y el otro para gestionar Servidor
		String javaHome = System.getProperty( "java.home" );
		String javaBin = javaHome + File.separator + "bin"  + File.separator + "java";
		String classpath = System.getProperty( "java.class.path" );
		
		// Creacion del proceso que lanzara la clase Servidor
		String className = "es.florida.sockets.Servidor";
		List<String> command = new ArrayList<>();
		command.add(javaBin);
		command.add("-cp");
		command.add(classpath);
		command.add(className);
		ProcessBuilder server = new ProcessBuilder(command);
		server.inheritIO().start();
		
		// Creacion del proceso que lanzara la clase Cliente
		className = "es.florida.sockets.Cliente";
		command = new ArrayList<>();
		command.add(javaBin);
		command.add("-cp");
		command.add(classpath);
		command.add(className);
		ProcessBuilder client = new ProcessBuilder(command);
		client.inheritIO().start();
		
		
		Thread.sleep(5000);		
	}
}
