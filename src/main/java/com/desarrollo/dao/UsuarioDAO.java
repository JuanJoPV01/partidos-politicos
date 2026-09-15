package com.desarrollo.dao;

import com.desarrollo.config.Conexion;
import com.desarrollo.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario validar(String email, String clave) {
        Usuario user = null;
        String sql = "SELECT * FROM usuarios WHERE email = ? AND clave = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, clave);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new Usuario();
                    user.setId(rs.getInt("id"));
                    user.setNombre(rs.getString("nombre"));
                    user.setEmail(rs.getString("email"));
                    user.setClave(rs.getString("clave"));
                    user.setRol(rs.getString("rol"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios ORDER BY id ASC";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setEmail(rs.getString("email"));
                u.setClave(rs.getString("clave"));
                u.setRol(rs.getString("rol"));
                lista.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean agregar(Usuario u) {
        String sql = "INSERT INTO usuarios (nombre, email, clave, rol) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getClave());
            ps.setString(4, u.getRol());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Usuario obtenerPorId(int id) {
        Usuario u = null;
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setNombre(rs.getString("nombre"));
                    u.setEmail(rs.getString("email"));
                    u.setClave(rs.getString("clave"));
                    u.setRol(rs.getString("rol"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public boolean actualizar(Usuario u) {
        String sql = "UPDATE usuarios SET nombre=?, email=?, clave=?, rol=? WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getClave());
            ps.setString(4, u.getRol());
            ps.setInt(5, u.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    // --- REPORTES PARAMETRIZADOS ---
    
    public List<Usuario> reportePorRol(String rol) {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE rol = ? ORDER BY nombre ASC";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, rol);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setNombre(rs.getString("nombre"));
                    u.setEmail(rs.getString("email"));
                    u.setRol(rs.getString("rol"));
                    lista.add(u);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Usuario> reporteBusqueda(String texto) {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE LOWER(nombre) LIKE LOWER(?) OR LOWER(email) LIKE LOWER(?) ORDER BY id ASC";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setNombre(rs.getString("nombre"));
                    u.setEmail(rs.getString("email"));
                    u.setRol(rs.getString("rol"));
                    lista.add(u);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
