package com.rcm.view_orchestrator.DAO;

import java.util.List;

import com.rcm.view_orchestrator.model.Component;
import com.rcm.view_orchestrator.model.Form;
import com.rcm.view_orchestrator.model.Lookup;

public interface FormDAO {
	public Form getForm(String id);
	public Component getComponent(String id);
	public Lookup getLookup(String id);
	public List<String> listComponent(String f_id);
	public List<String> listLookup(String c_id);
}
