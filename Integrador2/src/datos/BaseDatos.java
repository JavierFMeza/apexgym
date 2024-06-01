package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

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
				data.add(new LogIn(result.getString(1), result.getString(2), result.getString(3)));
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
				user=new LogIn(result.getString(1), result.getString(2), result.getString(3));
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
			String query = "SELECT v.id, p.nombre, p.preciounit, TO_CHAR(v.fecha, 'DD-MM-YYYY'), ve.nombre, v.cantidad " +
		               "FROM producto p " +
		               "JOIN ventaprod vp ON p.id = vp.prodid " +
		               "JOIN venta v ON vp.ventaid = v.id " +
		               "JOIN vendedor ve ON v.cedven = ve.cedula";
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
	public LinkedList<Servicio> getSer() {
	    LinkedList<Servicio> data1 = new LinkedList<Servicio>();
	    Connection conn = this.getConnection();
	    Statement st;
	    try {
	        st = conn.createStatement();
	        String query = "SELECT s.id, s.horario, TO_CHAR(s.fecha, 'DD-MM-YYYY'), s.horas, e.nombre, t.nombre, t.preciohora " +
	                       "FROM servicio s " +
	                       "JOIN entrenador e ON s.cedent = e.cedula " +
	                       "JOIN tiposervicio t ON s.tipid = t.id";
	        ResultSet result = st.executeQuery(query);
	        while(result.next()) {
	            data1.add(new Servicio(result.getString(1),result.getString(2),result.getString(3),result.getInt(4),result.getString(5),result.getString(6),result.getInt(7)));
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
	public LinkedList<Servicio> getSer2(String id) {
	    LinkedList<Servicio> data2 = new LinkedList<>();
	    Connection conn = null;
	    PreparedStatement st = null;
	    ResultSet result = null;
	    try {
	        conn = this.getConnection();
	        String query = "SELECT s.id, s.horario, TO_CHAR(s.fecha, 'DD-MM-YYYY'), s.horas, e.nombre, t.nombre, t.preciohora " +
                    "FROM servicio s " +
                    "JOIN entrenador e ON s.cedent = e.cedula " +
                    "JOIN tiposervicio t ON s.tipid = t.id" +
	                "WHERE s.id = ?";
	        st = conn.prepareStatement(query);
	        st.setString(1, id);
	        result = st.executeQuery();
	        while (result.next()) {
	        	data2.add(new Servicio(result.getString(1),result.getString(2),result.getString(3),result.getInt(4),result.getString(5),result.getString(6),result.getInt(7)));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (result != null) result.close();
	            if (st != null) st.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return data2;
	}

	/**
	 * Obtiene una venta específica según el ID proporcionado.
	 * 
	 * @param id El ID de la venta a buscar.
	 * @return LinkedList<Venta> Una lista de ventas que coinciden con el ID.
	 */
	public LinkedList<Venta> getVenta2(String id) {
	    LinkedList<Venta> data2 = new LinkedList<>();
	    Connection conn = null;
	    PreparedStatement st = null;
	    ResultSet result = null;
	    try {
	        conn = this.getConnection();
	        String query = "SELECT v.id, p.nombre, p.preciounit, TO_CHAR(v.fecha, 'DD-MM-YYYY'), ve.nombre, v.cantidad " +
	                       "FROM producto p " +
	                       "JOIN ventaprod vp ON p.id = vp.prodid " +
	                       "JOIN venta v ON vp.ventaid = v.id " +
	                       "JOIN vendedor ve ON v.cedven = ve.cedula " +
	                       "WHERE v.id = ?";
	        st = conn.prepareStatement(query);
	        st.setString(1, id);
	        result = st.executeQuery();
	        while (result.next()) {
	            data2.add(new Venta(result.getString(1),result.getString(2),result.getInt(3),result.getString(4),result.getString(5),result.getInt(6)));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (result != null) result.close();
	            if (st != null) st.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
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
		try (Connection conn = getConnection();
		         PreparedStatement stmtVentaProd = conn.prepareStatement("DELETE FROM ventaprod WHERE ventaid = ?");
		         PreparedStatement stmtVenta = conn.prepareStatement("DELETE FROM venta WHERE id = ?")) {
		        
		        conn.setAutoCommit(false);

		        stmtVentaProd.setString(1, id);
		        int filasAfectadasVentaProd = stmtVentaProd.executeUpdate();

		        stmtVenta.setString(1, id);
		        int filasAfectadasVenta = stmtVenta.executeUpdate();

		        if (filasAfectadasVentaProd > 0 && filasAfectadasVenta > 0) {
		            conn.commit();
		            return true;
		        } else {
		            conn.rollback();
		            return false;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
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
            String query = "DELETE FROM servicio WHERE id = ?";
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
	public String buscarIdEntPorNombre(String nombreEnt) {
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
	
	public String buscarIdTiposervicioPorNombre(String nombreServicio) {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    String idTipoServicio = null;
	    try {
	        conn = getConnection();
	        String query = "SELECT id FROM tiposervicio WHERE nombre = ?";
	        stmt = conn.prepareStatement(query);
	        stmt.setString(1, nombreServicio);
	        rs = stmt.executeQuery();
	        if (rs.next()) {
	            idTipoServicio = rs.getString("id");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	  
	    return idTipoServicio;
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
	    String sqlVenta = "UPDATE venta SET cantidad = ?, fecha = TO_DATE(?,'DD-MM-YYYY'), cedven = ? WHERE id = ?";
	    String sqlVentaProd = "UPDATE ventaprod SET prodid = ? WHERE ventaid = ?";
	    
	    try (Connection conn = getConnection();
	         PreparedStatement statementVenta = conn.prepareStatement(sqlVenta);
	         PreparedStatement statementVentaProd = conn.prepareStatement(sqlVentaProd)) {
	        
	        // Actualizar la tabla venta
	        statementVenta.setInt(1, cantidad);
	        statementVenta.setString(2, fecha);
	        statementVenta.setString(3, cedVendedor);
	        statementVenta.setString(4, id);
	        int rowsUpdatedVenta = statementVenta.executeUpdate();
	        
	        // Actualizar la tabla ventaprod
	        statementVentaProd.setString(1, idProducto);
	        statementVentaProd.setString(2, id);
	        int rowsUpdatedVentaProd = statementVentaProd.executeUpdate();
	        
	        if (rowsUpdatedVenta > 0 && rowsUpdatedVentaProd > 0) {
	            System.out.println("¡Los datos han sido actualizados correctamente!");
	        } else {
	            System.out.println("¡No se han realizado cambios!");
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al actualizar los datos: " + e.getMessage());
	    }
	}

	/**
	 * Actualiza los datos de una servicio en la base de datos.
	 * 
	 * @param idServicio El ID del servicio vendido.
	 * @param horario El horario del servicio.
	 * @param hora La cantidad de horas vendidas.
	 * @param fecha La fecha de la servicio en formato "DD-MM-YYYY".
	 * @param idTipoServicio El ID del servicio a actualizar.
	 * @param idEntrenador El ID del entrenador a actualizar.
	 */
	public void actualizarServicio(String id, String horario, int horas, String fecha, String idEnt, String tipId) {
		String sqlServicio = "UPDATE servicio SET id = ?, horario = ?, fecha = TO_DATE(?, 'DD-MM-YYYY'), horas = ?, cedent = ?, tipid = ? WHERE id = ?";

	    try (Connection conn = getConnection();
	            PreparedStatement statementServicio = conn.prepareStatement(sqlServicio)) {
	    		
	           statementServicio.setString(1, id);
	           statementServicio.setString(2, horario);
	           statementServicio.setString(3, fecha); 
	           statementServicio.setInt(4, horas);
	           statementServicio.setString(5, idEnt);
	           statementServicio.setString(6, tipId);
	           statementServicio.setString(7, id);
	           int rowsUpdatedServicio = statementServicio.executeUpdate();

	           if (rowsUpdatedServicio > 0) {
	               System.out.println("¡Los datos del servicio han sido actualizados correctamente!");
	           } else {
	               System.out.println("¡No se han realizado cambios en el servicio!");
	           }
	       } catch (SQLException e) {
	           System.err.println("Error al actualizar los datos del servicio: " + e.getMessage());
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
        PreparedStatement pstVenta = null;
        PreparedStatement pstVentaProducto = null;
        boolean exito = false;
        try {
            conn.setAutoCommit(false);
            
            // Insertar en la tabla venta
            String queryVenta = "INSERT INTO venta (id, fecha, cantidad, cedven) VALUES (?, TO_DATE(?,'DD-MM-YYYY'), ?, ?)";
            pstVenta = conn.prepareStatement(queryVenta);
            pstVenta.setString(1, id);
            pstVenta.setString(2, fecha);
            pstVenta.setInt(3, cantidad);
            pstVenta.setString(4, cedVendedor);
            int filasAfectadasVenta = pstVenta.executeUpdate();
            
            // Insertar en la tabla ventaprod
            String queryVentaProducto = "INSERT INTO ventaprod (ventaid, prodid) VALUES (?, ?)";
            pstVentaProducto = conn.prepareStatement(queryVentaProducto);
            pstVentaProducto.setString(1, id);
            pstVentaProducto.setString(2, idProducto);
            int filasAfectadasVentaProducto = pstVentaProducto.executeUpdate();
            
            if (filasAfectadasVenta > 0 && filasAfectadasVentaProducto > 0) {
                conn.commit();
                exito = true;
            } else {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            try {
                if (pstVenta != null) pstVenta.close();
                if (pstVentaProducto != null) pstVentaProducto.close();
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
    public boolean guardarSer(String idEnt, String horario, int hora, String fecha, String id, String tipId) {
        Connection conn = getConnection();
        PreparedStatement pst = null;
        boolean exito = false;
        try {
            String query = "INSERT INTO servicio (id, horario, fecha, horas, cedent, tipid) VALUES (?, ?, TO_DATE(?,'DD-MM-YYYY'), ?, ?, ?)";
            pst = conn.prepareStatement(query);
            pst.setString(1, id);
            pst.setString(2, horario);
            pst.setString(3, fecha);
            pst.setInt(4, hora);
            pst.setString(5, idEnt);
            pst.setString(6, tipId);
            int filasAfectadas = pst.executeUpdate();
            if (filasAfectadas > 0) {
                exito = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
    
    
    
    
    
    
    public Map<String, Integer> getProductosMasVendidos() {
        Map<String, Integer> productosMasVendidos = new LinkedHashMap<>();
        String query = "SELECT producto.NOMBRE, COUNT(ventaprod.PRODID) AS cantidad_vendida " +
                       "FROM ventaprod " +
                       "JOIN producto ON ventaprod.PRODID = producto.ID " +
                       "GROUP BY producto.NOMBRE " +
                       "ORDER BY cantidad_vendida DESC";

        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                productosMasVendidos.put(resultSet.getString("NOMBRE"), resultSet.getInt("cantidad_vendida"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productosMasVendidos;
    }
}
