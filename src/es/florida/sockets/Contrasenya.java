package es.florida.sockets;

public class Contrasenya 
{
	public String contrasenya = "";
	public String contrasenyaEncryptada = "";
	
	public void SetContrasenya( String contrasenya )
	{
		this.contrasenya = contrasenya;
	}
	
	public void SetContrasenyaEncryptada( String contrasenyaEncryptada )
	{
		this.contrasenyaEncryptada = contrasenyaEncryptada;
	}
	
	public String GetContrasenyaEncryptada()
	{
		return contrasenyaEncryptada;
	}
	
	public boolean VerificarSesion( String contrasenyaProbar )
	{
		if( this.contrasenya == contrasenyaProbar )
		{
			return true;
		}
		
		return false;
	}
}


