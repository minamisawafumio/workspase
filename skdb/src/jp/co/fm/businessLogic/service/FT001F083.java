package jp.co.fm.businessLogic.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.fm.businessLogic.common.Service;
import jp.co.fm.presentation.form.FT001F083Form;

@Controller
@RequestMapping("FT001F083")
public class FT001F083 {
	String thisGamen = "FT001F083";

	@Autowired(required=false)
    private FT001F083Form thisForm;

    @ModelAttribute("FT001F083Form")
	public void init(@ModelAttribute FT001F083Form form, Model model) {
    	thisForm = form;
	}

	@RequestMapping(value = "form", params = "doExecute", method = RequestMethod.POST)
	public String doExecute(Model model) {

		String id = thisForm.getId();

		String name = thisForm.getName();

		return thisGamen;
	}

	/**
	 * 戻り処理
	 * @param model
	 * @return
	 */
    @RequestMapping(value = "form", params = "back", method = RequestMethod.POST)
    public Object back(Model model, HttpServletRequest request) {
       	return Service.getInstance().backGamen(request, model,thisForm);
    }
}
