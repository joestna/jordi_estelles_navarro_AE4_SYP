package es.florida.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

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
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchAlgorithmException
	{
		Scanner sc = new Scanner(System.in);
		Servidor objetoServidor = new Servidor();
		int numeroPuerto = 1234;
		
		// esperar que el cliente se conecte
		ServerSocket servidor = new ServerSocket( numeroPuerto );
		System.err.println( "SERVIDOR : Esuchando ..." );
		Socket cliente = servidor.accept();
		System.err.println( "SERVIDOR : Cliente conectado " );
		
		// enviar al cliente un objeto de la clase contrasenya
		ObjectOutputStream outputObjeto = new ObjectOutputStream( cliente.getOutputStream() );
		Cuenta cuenta = new Cuenta();
		outputObjeto.writeObject( cuenta );
		System.err.println( "SERVIDOR : Enviando a cliente el objeto Cuenta" );
				
		// recibir del cliente el objeto contrasenya modificado con la contrasenya
		ObjectInputStream inputObjeto = new ObjectInputStream( cliente.getInputStream() );
		System.err.println( "SERVIDOR : Esperando respuesta cliente" );
		Cuenta cuentaMod = (Cuenta) inputObjeto.readObject();
		System.err.println( "SERVIDOR : Recibido de cliente el objeto cuenta con la contrasenya completada" );
		
		// cifrar la contrasenya		
		cuentaMod.SetEncryptedPassword( objetoServidor.SeleccionarCifrado( sc, cuentaMod.GetPassword() ));
		
		// enviar al cliente el objeto contasenya con la contrasenya cifrada
		outputObjeto = new ObjectOutputStream( cliente.getOutputStream() );
		outputObjeto.writeObject( cuentaMod );
		System.err.println( "SERVIDOR : Enviando a cliente el objeto cuenta con la contrasenya cifrada ... " );
		
		sc.close();
		outputObjeto.close();
		inputObjeto.close();
		cliente.close();
		servidor.close();
	}
	
	
	// Utiliza la contrasenya que le pasa el cliente, la cifrara y completara el objeto contrasenya
	public String CifrarContrasenyaFacil( String contrasenya )
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
	public String DescifrarContrasenyaFacil( String contrasenyaDescifrar)
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
	
	
	public String MD5( String contrasenyaADescifrar ) throws NoSuchAlgorithmException
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest( contrasenyaADescifrar.getBytes());
			  
	        BigInteger no = new BigInteger(1, messageDigest);
	        String hashtext = no.toString(16);
	        
	        while (hashtext.length() < 32) 
	        {
	            hashtext = "0" + hashtext;
	        }
	        
	        return hashtext;
		}
		catch (NoSuchAlgorithmException e)
		{
            throw new RuntimeException(e);
		}
	}
	
	
	public String SeleccionarCifrado( Scanner sc, String contrasenya ) throws NoSuchAlgorithmException
	{
		// Anyadir una expresion regular
		sc = new Scanner(System.in);
		
		boolean error = false;
		int tipoCifrado = 0;
		
		while( !error || tipoCifrado != 1 && tipoCifrado != 2)
		{
			try
			{
				System.err.print( "> Introduzca el tipo de cifrado requerido : 1. Basico / 2. MD5 : " );
				tipoCifrado = sc.nextInt();
				
				error = true;
			}
			catch( Exception e )
			{
				System.err.println( "Error, debe introducir un tipo de cifrado correcto" );
				sc.nextLine();
			}
		}
		
		switch(tipoCifrado)
		{
		case 1 :
			return CifrarContrasenyaFacil(contrasenya);

		case 2 :
			return MD5(contrasenya);
			
		}
		
		return contrasenya;		
	}
}

