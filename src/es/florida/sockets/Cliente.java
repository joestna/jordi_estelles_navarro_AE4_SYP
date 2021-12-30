package es.florida.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		Cliente objetoCliente = new Cliente();
		Scanner sc = new Scanner(System.in);
	
		// 1 conectarse al servidor
		String host = "localhost";
		//int puerto = objetoCliente.IntroducirPuerto( sc );
		int puerto = 1234;
		System.out.println( "CLIENTE : Arranca el cliente" );
		Socket cliente = new Socket( host, puerto );
		
		// 3 recibir del servidor el objeto cuenta
		ObjectInputStream inputObjeto = new ObjectInputStream( cliente.getInputStream() );
		Cuenta cuentaMod = (Cuenta) inputObjeto.readObject();
		//System.out.println( "CLIENTE : Recibido objeto cuenta de servidor > " + cuentaMod.GetUser() );
		
		// 5 rellenar la contrasenya con setcontrasenya();
		//cuentaMod.SetPassword( objetoCliente.IntroducirContrasenya( sc ) );
		cuentaMod.SetPassword( "hola1234" );
		
		//Thread.sleep(1000);
		
		// 6 devolver al servidor el objeto contrasenya con el atributo rellenado
		ObjectOutputStream outputObjeto = new ObjectOutputStream( cliente.getOutputStream() );
		outputObjeto.writeObject( cuentaMod );
		//System.out.println( "CLIENTE : Envio al servidor la cuenta con la contrsenya" );		
		
		
		//Thread.sleep(1000);
		
		// 8 recibir el objeto contrasenya con el campo contrasenyaEncryptada relleno
		//inputObjeto = new ObjectInputStream( cliente.getInputStream() );
		//cuentaMod = (Cuenta) inputObjeto.readObject();
		//System.out.println( "CLIENTE : Recibo del servidor el objeto cuenta y la contrasenya cifrada" );
		
		// 9 mostrar por pantalla la contasenya encryptada del objeto contrasenya
		//String contrasenyaCifrada = cuentaMod.GetEncryptedPassword();
		//System.out.println( contrasenyaCifrada );
		
		
		outputObjeto.close();
		inputObjeto.close();
		cliente.close();
	}
	
	public String IntroducirContrasenya( Scanner sc ) 
	{
		// Anyadir una expresion regular
		
		boolean error = false;
		String contrasenya = "";
		
		while( !error )
		{
			try
			{
				System.out.print( "> Introduzca la contrasenya : " );
				contrasenya = sc.next();
				
				return contrasenya; // Modificar
			}
			catch( Exception e )
			{
				System.out.println( "Error, debe introducir una contrasenya valida" );
			}
		}
		
		// Es imposible que llegue aqui
		return contrasenya;		
	}
	
	
	public int IntroducirPuerto( Scanner sc )
	{		
		boolean error = false;
		int puerto = 0;
		
		while( !error )
		{
			try
			{
				System.out.print( "> Introduzca el numero de puerto a conectarse : " );
				puerto = sc.nextInt(); // Si el valor introducido por teclado no es un Scanner.nextInt() en el try hay que limpiar el buffer del scanner
				
				return puerto;
			}
			catch( Exception e )
			{
				System.out.println( "Error, debe introducir un puerto valido (NUMERO ENTERO)" );
				sc.nextLine(); // Limpia el buffer de lectura del Scanner
			}
		}
		
		// Es imposible que llegue aqui
		return puerto;		
	}
}
