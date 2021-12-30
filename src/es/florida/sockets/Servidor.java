package es.florida.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	// Tabla ASCI caracteres basicos
	String[] tablaASCII = 
	{
			" ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", "asdf", "-", ".", "/",
			"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			":", ";", "<", "=", ">", "?", "@",
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
			"[", "\\", "]", "^", "_",
			"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
			"{", "|", "}", "~"
	};
	
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		Servidor objetoServidor = new Servidor();
		int numeroPuerto = 1234;
		
		// 2 esperar que el cliente se conecte
		ServerSocket servidor = new ServerSocket( numeroPuerto );
		System.err.println( "SERVIDOR : Esuchando ..." );
		Socket cliente = servidor.accept();
		//System.err.println( "SERVIDOR : Cliente aceptado " );
		
		// 2.1 enviar al cliente un objeto de la clase contrasenya
		ObjectOutputStream outputObjeto = new ObjectOutputStream( cliente.getOutputStream() );
		Cuenta cuenta = new Cuenta();
		outputObjeto.writeObject( cuenta );
		//System.err.println( "SERVIDOR : Enviando a cliente el objeto cuenta con usuario > " + cuenta.GetUser());
				
		// 7 recibir del cliente el objeto contrasenya modificado con la contrasenya
		ObjectInputStream inputObjeto = new ObjectInputStream( cliente.getInputStream() );
		Cuenta cuentaMod = (Cuenta) inputObjeto.readObject();
		//System.err.println( "SERVIDOR : Recibido de cliente el objeto cuenta con la contrasenya completada" );
		System.out.println( "SERVIDOR : " + cuentaMod.GetPassword() );
		
		
		// 7.5 cifrar la contrasenya
		
		cuentaMod.SetEncryptedPassword( objetoServidor.CifrarContrasenya( cuentaMod.GetPassword() ));
		System.out.println(cuentaMod.GetEncryptedPassword());		
		
		// 7.9 enviar al cliente el objeto contasenya con la contrasenya cifrada
		//outputObjeto = new ObjectOutputStream( cliente.getOutputStream() );
		//outputObjeto.writeObject( cuentaMod );
		//System.err.println( "SERVIDOR : Enviando a cliente el objeto cuenta con la contrasenya cifrada" );
		//System.out.println( cuentaMod.GetPassword());
		
		
		outputObjeto.close();
		inputObjeto.close();
		cliente.close();
		servidor.close();
	}
	
	
	// Utiliza la contrasenya que le pasa el cliente, la cifrara y completara el objeto contrasenya
	public String CifrarContrasenya( String contrasenya )
	{
		String[] contrasenyaDividida = contrasenya.split("");		
		String contrasenyaCifrada = "";
		
		for( int i = 0; i < contrasenyaDividida.length; i++ )
		{
			for( int j = 0; j < tablaASCII.length; j++ )
			{
				if( contrasenyaDividida[i].equals(tablaASCII[j]) && j == tablaASCII.length ) 
				{
					contrasenyaCifrada += tablaASCII[0];
				}
				else if(contrasenyaDividida[i].equals(tablaASCII[j]))
				{
					contrasenyaCifrada += tablaASCII[j + 1];
				}
			}
		}
		
		return contrasenyaCifrada;
	}
	
	
	// Metodo para descifrar la contrasenya
	public String DescifrarContrasenya( String contrasenyaDescifrar)
	{
		String[] contrasenyaDescifrarDividida = contrasenyaDescifrar.split("");
		String contrasenyaDescifrada = "";
		
		for( int i = 0; i < contrasenyaDescifrarDividida.length; i++ )
		{
			for( int j = 0; j < tablaASCII.length; j++ )
			{
				if( contrasenyaDescifrarDividida[i].equals(tablaASCII[j]) && j == 0 ) 
				{
					contrasenyaDescifrada += tablaASCII[tablaASCII.length];
				}
				else if(contrasenyaDescifrarDividida[i].equals(tablaASCII[j]))
				{
					contrasenyaDescifrada += tablaASCII[j - 1];
				}
			}
		}

		return contrasenyaDescifrada;
	}

}

