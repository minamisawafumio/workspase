package jp.co.fm.businessLogic.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.fm.businessLogic.common.Service;
import jp.co.fm.presentation.form.FT001F07_2Form;

@Controller
@RequestMapping("FT001F07_2")
public class FT001F07_2 {

	String thisGamen = "FT001F07_2";

	@Autowired(required=false)
    private FT001F07_2Form thisForm;

    @ModelAttribute("FT001F07_2Form")
	public void init(@ModelAttribute FT001F07_2Form form, Model model) {
    	thisForm = form;
	}

	/**
	 * 戻り処理
	 * @param model
	 * @return
	 */
    @RequestMapping(value = "form", params = "back", method = RequestMethod.POST)
    public Object back2(Model model, HttpServletRequest request) {
       	return Service.getInstance().backGamen(request, model, thisForm);
    }
}
