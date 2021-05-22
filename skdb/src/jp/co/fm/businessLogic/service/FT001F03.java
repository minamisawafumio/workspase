package jp.co.fm.businessLogic.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.util.bean.pdf.PdfDataBean;

import jp.co.fm.businessLogic.common.Const;
import jp.co.fm.businessLogic.common.DateUtil;
import jp.co.fm.businessLogic.common.PdfUtil;
import jp.co.fm.businessLogic.common.Service;
import jp.co.fm.businessLogic.system.SystemInfo;
import jp.co.fm.presentation.form.FT001F03Form;

@Controller
@RequestMapping("FT001F03")
public class FT001F03 {
	String thisGamen = "FT001F03";

	@Autowired(required=false)
    private FT001F03Form thisForm;

    @ModelAttribute("FT001F03Form")
	public void init(@ModelAttribute FT001F03Form form, Model model) {
    	thisForm = form;
	}

    /**
     * 決定
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "form", params = "doExecute", method = RequestMethod.POST)
	public String doExecute(Model model, HttpServletRequest request) throws IOException {

    	String comDateTime = DateUtil.getInstance().getComDateTime(5);

    	String pdfFileName = "pdf_" + comDateTime + ".pdf";

    	PdfDataBean pdfDataBean = PdfUtil.getInstance().makePdfDataBean();

    	pdfDataBean.setFileName(pdfFileName);

    	String pdfPath = (String) SystemInfo.getInstance().getValue(Const.PDF_PATH);

    	PdfUtil.getInstance().pdfPrint(pdfPath, pdfDataBean);

    	String selectedIsbn = thisForm.getSelectedIsbn();

    	System.out.println("selectedIsbn=" + selectedIsbn);

    	model.addAttribute("FT001F03Form", thisForm);

		return thisGamen;
	}


    @RequestMapping(value = "form", params = "pdfPrint", method = RequestMethod.POST)
	public String pdfPrint(Model model, HttpServletRequest request) {


		return thisGamen;
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


    @RequestMapping(value = "form", params = "testData", method = RequestMethod.POST)
    public Object testData(Model model, HttpServletRequest request) {

    	String parameter = request.getParameter("abcd");

    	System.out.println(parameter);

    	return thisGamen;
    }


    @RequestMapping(value = "form", params = "button_01", method = RequestMethod.POST)
    public Object button_01(Model model, HttpServletRequest request) {


    	return thisGamen;
    }
}
