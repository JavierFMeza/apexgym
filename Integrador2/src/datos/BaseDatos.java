package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class BaseDatos {
	private String conectionstr = "jdbc:oracle:thin:@192.168.254.215:1521:orcl";
	private String username = "apexgym";
	private String password = "apexgym";
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(this.conectionstr, this.username, this.password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public LinkedList<LogIn> getDatos(){
		LinkedList<LogIn> data = new LinkedList<LogIn>();
		Connection conn = this.getConnection();
		Statement st;
		try {
			st = conn.createStatement();
			String query = "select * from usuarios";
			ResultSet result = st.executeQuery(query);
			while(result.next()) {
				data.add(new LogIn(result.getString(1), result.getString(2), result.getString(3), result.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	public LogIn login(String username, String password) {
		LogIn user=null;
		Connection conn = this.getConnection();
		try {
			String query = "select * from usuarios where usuario=? and contrasena=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, username);
			st.setString(2, password);
			ResultSet result = st.executeQuery();
			while(result.next()) {
				user=new LogIn(result.getString(1), result.getString(2), result.getString(3), result.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * Obtiene una lista de ventas desde la base de datos.
	 * 
	 * @return LinkedList<Venta> Una lista de ventas.
	 */
	public LinkedList<Venta> getVenta(){
		LinkedList<Venta> data1 = new LinkedList<Venta>();
		Connection conn = this.getConnection();
		Statement st;
		try {
			st = conn.createStatement();
			String query = "SELECT v.id, p.nombre, p.precio, TO_CHAR(v.fecha, 'DD-MM-YYYY'), ve.nombre, v.cantidad " +
		               "FROM producto p " +
		               "JOIN venta v ON p.id = v.idprod " +
		               "JOIN vendedor ve ON v.cedven = ve.cedula ";
			ResultSet result = st.executeQuery(query);
			while(result.next()) {
				data1.add(new Venta(result.getString(1), result.getString(2), result.getInt(3), result.getString(4), result.getString(5),result.getInt(6)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data1;
	}
	/**
	 * Obtiene una lista de productos desde la base de datos.
	 * 
	 * @return LinkedList<Producto> Una lista de productos.
	 */
	public LinkedList<Producto> getProd(){
		LinkedList<Producto> data1 = new LinkedList<Producto>();
		Connection conn = this.getConnection();
		Statement st;
		try {
			st = conn.createStatement();
			String query = "SELECT *FROM producto ";
			ResultSet result = st.executeQuery(query);
			while(result.next()) {
				data1.add(new Producto(result.getString(1), result.getString(2), result.getInt(3), result.getString(4), result.getString(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data1;
	}
	/**
	 * Obtiene una lista de entrenadores desde la base de datos.
	 * 
	 * @return LinkedList<Entrenador> Una lista de entrenadores.
	 */
	public LinkedList<Entrenador> getEnt(){
		LinkedList<Entrenador> data1 = new LinkedList<Entrenador>();
		Connection conn = this.getConnection();
		Statement st;
		try {
			st = conn.createStatement();
			String query = "SELECT *FROM entrenador ";
			ResultSet result = st.executeQuery(query);
			while(result.next()) {
				data1.add(new Entrenador(result.getString(1), result.getString(2), result.getString(3), result.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data1;
	}
	/**
	 * Obtiene una lista de servicios desde la base de datos.
	 * 
	 * @return LinkedList<Servicio> Una lista de servicios.
	 */
	public LinkedList<Servicio> getSer(){
		LinkedList<Servicio> data1 = new LinkedList<Servicio>();
		Connection conn = this.getConnection();
		Statement st;
		try {
			st = conn.createStatement();
			String query = "SELECT s.id, s.horario, s.fecha, s.horas, e.nombre " +
						"FROM servicio s " +
		               "JOIN entrenador e ON s.cedent = e.cedula ";
			ResultSet result = st.executeQuery(query);
			while(result.next()) {
				data1.add(new Servicio(result.getString(1), result.getString(2), result.getString(3), result.getInt(4), result.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data1;
	}
	/**
	 * Obtiene un servicio específico según el nombre proporcionado.
	 * 
	 * @param nombre El nombre del servicio a buscar.
	 * @return LinkedList<Servicio> Una lista de servicios que coinciden con el nombre.
	 */
	public LinkedList<Servicio> getSer2(String nombre){
	    LinkedList<Servicio> data2 = new LinkedList<Servicio>();
	    Connection conn = this.getConnection();
	    PreparedStatement st = null;
	    try {
	        String query = "SELECT s.id, s.horario, s.fecha, s.horas, e.nombre " +
						"FROM servicio s " +
		               "JOIN entrenador e ON s.cedent = e.cedula " +
		               "WHERE s.id = ?";
	        st = conn.prepareStatement(query);
	        st.setString(1, nombre);
	        ResultSet result = st.executeQuery();
	        while(result.next()) {
	        	data2.add(new Servicio(result.getString(1), result.getString(2), result.getString(3), result.getInt(4), result.getString(5)));
			}
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return data2;
	}
	/**
	 * Obtiene una venta específica según el ID proporcionado.
	 * 
	 * @param id El ID de la venta a buscar.
	 * @return LinkedList<Venta> Una lista de ventas que coinciden con el ID.
	 */
	public LinkedList<Venta> getVenta2(String id){
	    LinkedList<Venta> data2 = new LinkedList<Venta>();
	    Connection conn = this.getConnection();
	    PreparedStatement st = null;
	    try {
	        String query = "SELECT v.id, p.nombre, p.precio, TO_CHAR(v.fecha, 'DD-MM-YYYY'), ve.nombre, v.cantidad " +
			               "FROM producto p " +
			               "JOIN venta v ON p.id = v.idprod " +
			               "JOIN vendedor ve ON v.cedven = ve.cedula " +
	                       "WHERE v.id = ?";
	        st = conn.prepareStatement(query);
	        st.setString(1, id);
	        ResultSet result = st.executeQuery();
	        while(result.next()) {
	            data2.add(new Venta(result.getString(1), result.getString(2), result.getInt(3), result.getString(4), result.getString(5),result.getInt(6)));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return data2;
	}
	/**
	 * Elimina una venta de la base de datos según el ID proporcionado.
	 * 
	 * @param id El ID de la venta a eliminar.
	 * @return boolean true si la venta se eliminó correctamente, false de lo contrario.
	 */
	public boolean eliminarVenta(String id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean eliminado = false;
        try {
            conn = getConnection();
            String query = "DELETE FROM venta WHERE id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                eliminado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eliminado;
	}
	/**
	 * Elimina un servicio de la base de datos según el ID proporcionado.
	 * 
	 * @param id El ID del servicio a eliminar.
	 * @return boolean true si el servicio se eliminó correctamente, false de lo contrario.
	 */
	public boolean eliminarSer(String id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean eliminado = false;
        try {
            conn = getConnection();
            String query = "DELETE FROM servicios WHERE id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                eliminado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eliminado;
	}
	/**
	 * Busca el ID de un vendedor según el nombre proporcionado.
	 * 
	 * @param nombreVendedor El nombre del vendedor a buscar.
	 * @return String El ID del vendedor si se encuentra, de lo contrario, null.
	 */
	public String buscarIdVendedorPorNombre(String nombreVendedor) {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    String idVendedor = null;
	    try {
	        conn = getConnection();
	        String query = "SELECT cedula FROM vendedor WHERE nombre = ?";
	        stmt = conn.prepareStatement(query);
	        stmt.setString(1, nombreVendedor);
	        rs = stmt.executeQuery();
	        if (rs.next()) {
	            idVendedor = rs.getString("cedula");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    return idVendedor;
	}
	/**
	 * Busca el ID de un producto según el nombre proporcionado.
	 * 
	 * @param nombreProducto El nombre del producto a buscar.
	 * @return String El ID del producto si se encuentra, de lo contrario, null.
	 */
	public String buscarIdProductoPorNombre(String nombreProducto) {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    String idProducto = null;
	    try {
	        conn = getConnection();
	        String query = "SELECT id FROM producto WHERE nombre = ?";
	        stmt = conn.prepareStatement(query);
	        stmt.setString(1, nombreProducto);
	        rs = stmt.executeQuery();
	        if (rs.next()) {
	            idProducto = rs.getString("id");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    return idProducto;
	}
	/**
	 * Busca el ID de un servicio según el nombre del entrenador proporcionado.
	 * 
	 * @param nombreEnt El nombre del entrenador cuyo servicio se busca.
	 * @return String El ID del servicio si se encuentra, de lo contrario, null.
	 */
	public String buscarIdSerPorNombre(String nombreEnt) {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    String idProducto = null;
	    try {
	        conn = getConnection();
	        String query = "SELECT cedula FROM entrenador"
	        		+ " WHERE nombre = ?";
	        stmt = conn.prepareStatement(query);
	        stmt.setString(1, nombreEnt);
	        rs = stmt.executeQuery();
	        if (rs.next()) {
	            idProducto = rs.getString("cedula");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    return idProducto;
	}
	/**
	 * Actualiza los datos de una venta en la base de datos.
	 * 
	 * @param idProducto El ID del producto vendido.
	 * @param cedVendedor El ID del vendedor que realizó la venta.
	 * @param cantidad La cantidad vendida.
	 * @param fecha La fecha de la venta en formato "DD-MM-YYYY".
	 * @param id El ID de la venta a actualizar.
	 */
    public void actualizarVenta(String idProducto, String cedVendedor, int cantidad, String fecha, String id) {
        String sql = "UPDATE venta SET cantidad = ?, fecha = TO_DATE(?,'DD-MM-YYYY'), cedven = ?, idprod = ? WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(4, idProducto);
            statement.setInt(1, cantidad);
            statement.setString(2, fecha);
            statement.setString(3, cedVendedor);
            statement.setString(5, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("¡Los datos han sido actualizados correctamente!");
            } else {
                System.out.println("¡No se han realizado cambios!");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar los datos: " + e.getMessage());
        }
    }
    /**
     * Obtiene una lista de vendedores desde la base de datos.
     * 
     * @return LinkedList<Vendedor> Una lista de vendedores.
     */
    public LinkedList<Vendedor> getVend(){
		LinkedList<Vendedor> data1 = new LinkedList<Vendedor>();
		Connection conn = this.getConnection();
		Statement st;
		try {
			st = conn.createStatement();
			String query = "SELECT *FROM vendedor ";
			ResultSet result = st.executeQuery(query);
			while(result.next()) {
				data1.add(new Vendedor(result.getString(1), result.getString(2), result.getString(3), result.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data1;
	}
    /**
     * Guarda los datos de una venta en la base de datos.
     * 
     * @param idProducto El ID del producto vendido.
     * @param cedVendedor El ID del vendedor que realizó la venta.
     * @param cantidad La cantidad vendida.
     * @param fecha La fecha de la venta en formato "DD-MM-YYYY".
     * @param id El ID de la venta.
     * @return boolean true si la venta se guardó correctamente, false de lo contrario.
     */
    public boolean guardarVenta(String idProducto, String cedVendedor, int cantidad, String fecha, String id) {
        Connection conn = getConnection();
        PreparedStatement pst = null;
        boolean exito = false;
        try {
            String query = "INSERT INTO venta (id, cantidad, fecha, cedven, idprod) VALUES (?, ?, TO_DATE(?,'DD-MM-YYYY'), ?, ?)";
            pst = conn.prepareStatement(query);
            pst.setString(5, idProducto);
            pst.setString(4, cedVendedor);
            pst.setInt(2, cantidad);
            pst.setString(3, fecha);
            pst.setString(1, id);
            int filasAfectadas = pst.executeUpdate();
            if (filasAfectadas > 0) {
                exito = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exito;
    }
    /**
     * Guarda los datos de un producto en la base de datos.
     * 
     * @param id El ID del producto.
     * @param nombre El nombre del producto.
     * @param precio El precio del producto.
     * @param tipo El tipo de producto.
     * @param detalles Los detalles adicionales del producto.
     * @return boolean true si el producto se guardó correctamente, false de lo contrario.
     */
    public boolean guardarProd(String id, String nombre, int precio, String tipo, String detalles) {
        Connection conn = getConnection();
        PreparedStatement pst = null;
        boolean exito = false;
        try {
            String query = "INSERT INTO producto (id, nombre, precio, tipo, detalles) VALUES (?, ?, ?, ?, ?)";
            pst = conn.prepareStatement(query);
            pst.setString(1, id);
            pst.setString(2, nombre);
            pst.setInt(3, precio);
            pst.setString(4, tipo);
            pst.setString(5, detalles);
            int filasAfectadas = pst.executeUpdate();
            if (filasAfectadas > 0) {
                exito = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exito;
    }
    /**
     * Guarda los datos de un servicio en la base de datos.
     * 
     * @param idEnt El ID del entrenador que ofrecerá el servicio.
     * @param horario El horario del servicio.
     * @param hora La duración en horas del servicio.
     * @param fecha La fecha del servicio en formato "DD-MM-YYYY".
     * @param id El ID del servicio.
     * @return boolean true si el servicio se guardó correctamente, false de lo contrario.
     */
    public boolean guardarSer(String idEnt, String horario, int hora, String fecha, String id) {
        Connection conn = getConnection();
        PreparedStatement pst = null;
        boolean exito = false;
        try {
            String query = "INSERT INTO servicio (id, horario, fecha, horas,cedent) VALUES (?, ?, TO_DATE(?,'DD-MM-YYYY'),?,?)";
            pst = conn.prepareStatement(query);
            pst.setString(5, idEnt);
            pst.setString(2, horario);
            pst.setInt(4, hora);
            pst.setString(3, fecha);
            pst.setString(1, id);
            int filasAfectadas = pst.executeUpdate();
            if (filasAfectadas > 0) {
                exito = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exito;
    }
    /**
     * Guarda los datos de un entrenador en la base de datos.
     * 
     * @param cedula La cédula del entrenador.
     * @param nombre El nombre del entrenador.
     * @param telefono El número de teléfono del entrenador.
     * @param fecha La fecha de nacimiento del entrenador en formato "DD-MM-YYYY".
     * @return boolean true si el entrenador se guardó correctamente, false de lo contrario.
     */
    public boolean guardarEnt(String cedula, String nombre, String telefono, String fecha) {
        Connection conn = getConnection();
        PreparedStatement pst = null;
        boolean exito = false;
        try {
            String query = "INSERT INTO entrenador (cedula,nombre,telefono,fec_nac) VALUES (?, ?, ?,TO_DATE(?,'DD-MM-YYYY'))";
            pst = conn.prepareStatement(query);
            pst.setString(1, cedula);
            pst.setString(2, nombre);
            pst.setString(3, telefono);
            pst.setString(4, fecha);
            int filasAfectadas = pst.executeUpdate();
            if (filasAfectadas > 0) {
                exito = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exito;
    }
}
