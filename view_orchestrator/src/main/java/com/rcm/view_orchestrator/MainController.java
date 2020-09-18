package com.rcm.view_orchestrator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rcm.view_orchestrator.model.Form;
import com.rcm.view_orchestrator.model.Lookup;
import com.rcm.view_orchestrator.DAO.FormDAO;
import com.rcm.view_orchestrator.model.Component;

@Controller
@RequestMapping(path="/rcm")
public class MainController {
	@Autowired
	private FormDAO formDAO;
	
	@GetMapping(path="/curl_form")
	@ResponseBody
	public Form curl_form(@RequestParam(name="id") String id) {
		Form form = formDAO.getForm(id);
		if(form != null) {
			List<Component> componentList = new ArrayList<>();
			List<String> componentIDList = formDAO.listComponent(id);
			for(String componentID : componentIDList) {
				Component component = formDAO.getComponent(componentID);
				List<Lookup> lookupList = new ArrayList<>();
				List<String> lookupIDList = formDAO.listLookup(componentID);
				for(String lookupID : lookupIDList) {
					Lookup lookup = formDAO.getLookup(lookupID);
					lookupList.add(lookup);
				}
				component.setOption(lookupList);
				componentList.add(component);
			}
			form.setComponent(componentList);
			return form;
		}else {
			Form form_null = new Form();
			form_null.setId("id not found!");
			return form_null;
		}
	}
}
