package es.florida.sockets;

import java.io.Serializable;

public class Cuenta implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	//
	public String user = "Prueba";
	public String password = "";
	//
	public String EncPassword = "";
	
	
	public String GetPassword()
	{
		return this.password;
	}
	
	
	public void SetPassword( String contrasenya )
	{
		this.password = contrasenya;
	}
	
	
	public void SetEncryptedPassword( String contrasenyaEncryptada )
	{
		this.EncPassword = contrasenyaEncryptada;
	}
	
	
	public String GetEncryptedPassword()
	{
		return EncPassword;
	}
	
	
	public void SetUser( String usuario )
	{
		this.user = usuario;
	}
	
	
	public String GetUser()
	{
		return user;
	}
	
	
	public boolean VerificarSesion( String usuario, String contrasenyaProbar )
	{
		if( this.password == contrasenyaProbar && this.user == usuario )
		{
			return true;
		}
		
		return false;
	}	
}

