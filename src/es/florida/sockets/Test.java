package es.florida.sockets;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws NoSuchAlgorithmException 
	{
		Scanner sc = new Scanner(System.in);
		
		/*
		// cliente
		Cliente cliente = new Cliente();
		
		// cliente primer test
		
		int puertoPrueba = cliente.IntroducirPuerto( sc );
		System.out.println( puertoPrueba );
		
	
		// cliente segundo test
		
		String contrasenyaPrueba = cliente.IntroducirContrasenya( sc );
		System.out.println( contrasenyaPrueba );
		*/
		
		
		
		// server	
		Servidor server = new Servidor();
		String contrasenyaPrueba = "hola1234";
		String contrasenyaPCifrada = "ipmb2345";
		
		// server primer test	
		
		contrasenyaPCifrada = server.CifrarContrasenyaFacil( contrasenyaPrueba );
		System.out.println( "Contrasenya real : " + contrasenyaPrueba );
		System.out.println( "Contrasenya cifrada : " + contrasenyaPCifrada );
		
		
		// server segundo test		
		
		contrasenyaPCifrada = server.DescifrarContrasenyaFacil( contrasenyaPCifrada );
		System.out.println( "Contrasenya real : " + contrasenyaPrueba );
		System.out.println( "Contrasenya descifrada : " + contrasenyaPCifrada );
		
		 
		contrasenyaPCifrada = server.MD5(contrasenyaPrueba);
		System.out.println( contrasenyaPCifrada );
		
		
	}
}
