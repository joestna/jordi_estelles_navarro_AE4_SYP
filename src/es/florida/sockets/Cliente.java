package es.florida.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Cliente {

	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		Cliente objetoCliente = new Cliente();
		Scanner sc = new Scanner(System.in);
	
		// conectarse al servidor
		String host = "localhost";
		int puerto = objetoCliente.IntroducirPuerto( sc );
		Socket cliente = new Socket( host, puerto );
		System.out.println( "CLIENTE : Cliente intenta conectar ..." );
		
		// recibir del servidor el objeto cuenta
		ObjectInputStream inputObjeto = new ObjectInputStream( cliente.getInputStream() );
		System.out.println( "CLIENTE : Esperando envio del servidor ..." );
		Cuenta cuentaMod = (Cuenta) inputObjeto.readObject();
		System.out.println( "CLIENTE : Recibido objeto cuenta de servidor" );
		
		// rellenar la contrasenya con setcontrasenya();
		cuentaMod.SetPassword( objetoCliente.IntroducirContrasenya( sc ) );
		//cuentaMod.SetPassword( "hola1234" );
		
		// devolver al servidor el objeto contrasenya con el atributo rellenado
		ObjectOutputStream outputObjeto = new ObjectOutputStream( cliente.getOutputStream() );
		outputObjeto.writeObject( cuentaMod );
		System.out.println( "CLIENTE : Enviando cuenta modificada al servidor ..." );	
		
		// recibir el objeto contrasenya con el campo contrasenyaEncryptada relleno
		inputObjeto = new ObjectInputStream( cliente.getInputStream() );
		cuentaMod = (Cuenta) inputObjeto.readObject();
		
		// mostrar por pantalla la contasenya encryptada del objeto contrasenya
		String contrasenyaCifrada = cuentaMod.GetEncryptedPassword();
		System.out.println( "FINAL : CLIENTE ;  La contrasenya cifrada es : " + contrasenyaCifrada );
		
		sc.close();
		outputObjeto.close();
		inputObjeto.close();
		cliente.close();
	}
	
	public String IntroducirContrasenya( Scanner sc ) 
	{
		// Resetear el Scanner
		sc = new Scanner(System.in);
		
		boolean error = true;
		String contrasenya = "";
		
		while( error )
		{			
			try
			{
				System.out.print( "> Introduzca la contrasenya : " );
				contrasenya = sc.next();
				
				error = false;
			}
			catch( Exception e )
			{
				System.out.println( "Error, debe introducir una contrasenya valida" );
				sc.nextLine();
			}
		}
		
		return contrasenya;		
	}
	
	
	public int IntroducirPuerto( Scanner sc )
	{		
		// Resetear el Scanner
		sc = new Scanner(System.in);
		
		boolean error = true;
		int puerto = 0;
		
		while( error )
		{
			try
			{
				System.out.print( "> Introduzca el numero de puerto a conectarse : " );
				puerto = sc.nextInt(); // Si el valor introducido por teclado no es un Scanner.nextInt() en el try hay que limpiar el buffer del scanner
				
				error = false;
			}
			catch( Exception e )
			{
				System.out.println( "Error, debe introducir un puerto valido (NUMERO ENTERO)" );
				sc.nextLine(); // Limpia el buffer de lectura del Scanner
				// Tambien se podria haber hecho con sc = new Scanner(System.in)
			}
		}
	
		return puerto;		
	}
}
