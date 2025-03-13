package modelo;  

import java.sql.*;  
import java.util.*;  

public class ClienteDAO {  
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private String url = "jdbc:mysql://localhost:3306/handymantotal";  
    private String user = "root";  
    private String password = "Hecaro.0920";  

    public ClienteDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Cliente> listar() {  
        List<Cliente> lista = new ArrayList<>();  
        PreparedStatement ps = null;  
        ResultSet rs = null;  
        try {  
            if (con == null) { // Verifica si la conexión es nula  
                System.err.println("La conexión es nula. No se puede ejecutar la consulta.");   
                return lista; // Retorna la lista vacía si no hay conexión  
            }  
            ps = con.prepareStatement("SELECT * FROM cliente");  
            rs = ps.executeQuery();  
            while (rs.next()) {  
                lista.add(new Cliente(  
                    rs.getInt("ID_cliente"),  
                    rs.getString("Nombre"),  
                    rs.getString("Direccion"),  
                    rs.getString("Telefono"),  
                    rs.getString("Correo_electronico"),  
                    rs.getString("Contrasena")  
                ));  
            }  
        } catch (SQLException e) {  
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());  
            e.printStackTrace(); // Considera usar logging  
        } finally {  
            cerrarRecursos(rs, ps);  
        }  
        return lista;  
    }  

    private void cerrarRecursos(ResultSet rs, PreparedStatement ps) {  
        try {  
            if (rs != null) rs.close();  
            if (ps != null) ps.close();  
        } catch (SQLException e) {  
            System.err.println("Error al cerrar recursos: " + e.getMessage());  
            e.printStackTrace(); // Considera usar logging  
        }  
    }  

    public void insertar(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}  