package jp.co.fm.businessLogic.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.fm.businessLogic.common.Service;
import jp.co.fm.presentation.form.FT001F082Form;
import jp.co.fm.presentation.form.FT001F083Form;
import jp.co.fm.presentation.form.FT001F08Form;

@Controller
@RequestMapping("FT001F08")
public class FT001F08 {
	String thisGamen = "FT001F08";

	@Autowired(required=false)
    private FT001F08Form thisForm;

    @ModelAttribute("FT001F08Form")
	public void init(@ModelAttribute FT001F08Form form, Model model) {
    	thisForm = form;
	}

	@RequestMapping(value = "form", params = "doExecute", method = RequestMethod.POST)
	public String doExecute(Model model) {

		String id = thisForm.getId();

		String name = thisForm.getName();

		return thisGamen;
	}


	@RequestMapping(value = "form", params = "goto082", method = RequestMethod.POST)
    public String goto082(Model model, HttpServletRequest request) {

		FT001F082Form fT001F082Form = new FT001F082Form();

        return Service.getInstance().gotoNextGamen(request, thisForm, fT001F082Form, model);
	}

	@RequestMapping(value = "form", params = "goto083", method = RequestMethod.POST)
    public String goto083(Model model, HttpServletRequest request) {

		FT001F083Form fT001F083Form = new FT001F083Form();

        return Service.getInstance().gotoNextGamen(request, thisForm, fT001F083Form, model);
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
