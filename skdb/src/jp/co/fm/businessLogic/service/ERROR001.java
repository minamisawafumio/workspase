package jp.co.fm.businessLogic.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.fm.presentation.form.ERROR001Form;
import jp.co.fm.presentation.form.FT001F01Form;

@Controller
@RequestMapping("ERROR001")
public class ERROR001 {

	String thisGamen = "ERROR001";

	@Autowired(required=false)
    private ERROR001Form thisForm;

	@ModelAttribute("ERROR001Form")
	public void init(@ModelAttribute ERROR001Form form, Model model) {
    	thisForm = form;
	}

	@RequestMapping(value = "form", params = "doExecute", method = RequestMethod.POST)
	public String doExecute(Model model) {

		String id = thisForm.getId();

		String name = thisForm.getName();

		return thisGamen;
	}

    @RequestMapping(value = "form", params = "gotoLogin", method = RequestMethod.POST)
    public String gotoLogin(Model model, HttpServletRequest httpServletRequest) {

    	HttpSession httpSession = httpServletRequest.getSession();

		FT001F01Form fT001F01Form = new FT001F01Form();

        model.addAttribute("FT001F01Form", fT001F01Form);

       	httpSession.setAttribute("fT001F01Form", fT001F01Form);

		return "FT001F01";
    }
}
