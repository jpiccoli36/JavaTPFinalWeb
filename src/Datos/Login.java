package Datos;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
	public ResultSet login(String Nombre,String contrase�a){
		java.sql.PreparedStatement stmt =null;
		ResultSet rs = null;
		
		
		try {
			stmt =FactoryConexion.getInstancia().getConn().prepareStatement("select NombreUsuario, ApellidoUsuario,DNI,Categoria from usuarios where Usuario= ? and Contrase�a=?");
			stmt.setString(1,Nombre);
			stmt.setString(2, contrase�a);
			
			rs=stmt.executeQuery();
			
			if((rs.next())){
				
				return rs;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
		
		return null;
	}

}
