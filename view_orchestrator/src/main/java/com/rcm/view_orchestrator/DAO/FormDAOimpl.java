package com.rcm.view_orchestrator.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.rcm.view_orchestrator.model.Component;
import com.rcm.view_orchestrator.model.Form;
import com.rcm.view_orchestrator.model.Lookup;

public class FormDAOimpl implements FormDAO{

	private JdbcTemplate jdbcTemplate;
	
	public FormDAOimpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Form getForm(String id) {
		try {
			String sql = "SELECT * FROM `form` WHERE id='"+id+"'";
			return jdbcTemplate.query(sql, resultSet -> {
				resultSet.next();
				final Form form = new Form();
				form.setId(resultSet.getString("id"));
				form.setName(resultSet.getString("name"));
				form.setTitle(resultSet.getString("title"));
				return form;
			});
		}catch(Exception e) {
			return null;
		}		
	}

	@Override
	public Component getComponent(String id) {
		String sql = "SELECT * FROM `component` WHERE id='"+id+"'";
		return jdbcTemplate.query(sql, resultSet -> {
			resultSet.next();
			final Component component = new Component();
			component.setId(resultSet.getString("id"));
			component.setHidden(resultSet.getBoolean("hidden"));
			component.setName(resultSet.getString("name"));
			component.setNote(resultSet.getString("note"));
			component.setPlaceholder(resultSet.getString("placeholder"));
			component.setRequired(resultSet.getBoolean("required"));
			component.setTitle(resultSet.getString("title"));
			component.setType(resultSet.getInt("type"));
			component.setValue(resultSet.getString("value"));
			return component;
		});
	}

	@Override
	public Lookup getLookup(String id) {
		String sql = "SELECT * FROM `lookup` WHERE id='"+id+"'";
		return jdbcTemplate.query(sql, resultSet -> {
			resultSet.next();
			final Lookup lookup = new Lookup();
			lookup.setId(resultSet.getString("id"));
			lookup.setTitle(resultSet.getString("title"));
			lookup.setValue(resultSet.getString("value"));
			return lookup;
		});
	}

	@Override
	public List<String> listComponent(String f_id) {
		String sql = "SELECT * FROM `relation_f2c` WHERE f_id = '"+f_id+"'";
		List<String> data = jdbcTemplate.query(sql, new RowMapper<String>(){
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("c_id");
			}
		}); 
		return data;
	}

	@Override
	public List<String> listLookup(String c_id) {
		String sql = "SELECT * FROM `relation_c2l` WHERE c_id = '"+c_id+"'";
		List<String> data = jdbcTemplate.query(sql, new RowMapper<String>(){
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("l_id");
			}
		}); 
		return data;
	}

}
