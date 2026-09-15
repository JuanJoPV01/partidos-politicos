package com.desarrollo.dao;

import com.desarrollo.config.Conexion;
import com.desarrollo.model.PartidoPolitico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PartidoPoliticoDAO {

    public List<PartidoPolitico> listar() {
        List<PartidoPolitico> lista = new ArrayList<>();
        String sql = "SELECT * FROM partidos_politicos ORDER BY id ASC";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PartidoPolitico p = new PartidoPolitico();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setEslogan(rs.getString("eslogan"));
                p.setPresidente(rs.getString("presidente"));
                p.setSecretario(rs.getString("secretario"));
                p.setTesorero(rs.getString("tesorero"));
                p.setPais(rs.getString("pais"));
                p.setNumPresidentes(rs.getInt("num_presidentes"));
                p.setNumGobernadores(rs.getInt("num_gobernadores"));
                p.setNumAlcaldes(rs.getInt("num_alcaldes"));
                p.setNumConcejales(rs.getInt("num_concejales"));
                p.setNumCongresistas(rs.getInt("num_congresistas"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean agregar(PartidoPolitico p) {
        String sql = "INSERT INTO partidos_politicos (nombre, eslogan, presidente, secretario, tesorero, pais, num_presidentes, num_gobernadores, num_alcaldes, num_concejales, num_congresistas) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getEslogan());
            ps.setString(3, p.getPresidente());
            ps.setString(4, p.getSecretario());
            ps.setString(5, p.getTesorero());
            ps.setString(6, p.getPais());
            ps.setInt(7, p.getNumPresidentes());
            ps.setInt(8, p.getNumGobernadores());
            ps.setInt(9, p.getNumAlcaldes());
            ps.setInt(10, p.getNumConcejales());
            ps.setInt(11, p.getNumCongresistas());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public PartidoPolitico obtenerPorId(int id) {
        PartidoPolitico p = null;
        String sql = "SELECT * FROM partidos_politicos WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = new PartidoPolitico();
                    p.setId(rs.getInt("id"));
                    p.setNombre(rs.getString("nombre"));
                    p.setEslogan(rs.getString("eslogan"));
                    p.setPresidente(rs.getString("presidente"));
                    p.setSecretario(rs.getString("secretario"));
                    p.setTesorero(rs.getString("tesorero"));
                    p.setPais(rs.getString("pais"));
                    p.setNumPresidentes(rs.getInt("num_presidentes"));
                    p.setNumGobernadores(rs.getInt("num_gobernadores"));
                    p.setNumAlcaldes(rs.getInt("num_alcaldes"));
                    p.setNumConcejales(rs.getInt("num_concejales"));
                    p.setNumCongresistas(rs.getInt("num_congresistas"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public boolean actualizar(PartidoPolitico p) {
        String sql = "UPDATE partidos_politicos SET nombre=?, eslogan=?, presidente=?, secretario=?, tesorero=?, pais=?, num_presidentes=?, num_gobernadores=?, num_alcaldes=?, num_concejales=?, num_congresistas=? WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getEslogan());
            ps.setString(3, p.getPresidente());
            ps.setString(4, p.getSecretario());
            ps.setString(5, p.getTesorero());
            ps.setString(6, p.getPais());
            ps.setInt(7, p.getNumPresidentes());
            ps.setInt(8, p.getNumGobernadores());
            ps.setInt(9, p.getNumAlcaldes());
            ps.setInt(10, p.getNumConcejales());
            ps.setInt(11, p.getNumCongresistas());
            ps.setInt(12, p.getId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM partidos_politicos WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
