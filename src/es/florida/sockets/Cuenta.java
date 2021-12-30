package es.florida.sockets;

public class Cuenta 
{
	//
	public String user = "";
	public String password = "";
	//
	public String EncPassword = "";
	
	
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

