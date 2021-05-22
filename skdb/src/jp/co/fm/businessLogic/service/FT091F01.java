package jp.co.fm.businessLogic.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.fm.businessLogic.common.BeanUtil;
import jp.co.fm.businessLogic.common.Const;
import jp.co.fm.businessLogic.common.DateUtil;
import jp.co.fm.businessLogic.common.DbUtil;
import jp.co.fm.businessLogic.common.Service;
import jp.co.fm.businessLogic.common.StringUtil;
import jp.co.fm.businessLogic.system.SystemConst;
import jp.co.fm.businessLogic.table.T_0160;
import jp.co.fm.presentation.form.FT091F01Form;

@Controller
@RequestMapping("FT091F01")
public class FT091F01 {

    String thisGamen = "FT091F01";

	@Autowired(required=false)
    private FT091F01Form thisForm;


    @ModelAttribute("FT091F01Form")
	public void init(@ModelAttribute FT091F01Form form) {
    	thisForm = form;
	}

	/**
	 * 「決定」ボタン押下
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form", params = "h001", method = RequestMethod.POST)
	public String h001(Model model, HttpServletRequest request) {

       	HttpSession httpSession = request.getSession();



       	String id = thisForm.getId();
       	String password = thisForm.getPassword();

		String msg = validate(id, password);

		if("".equals(msg)){ //エラーなし
			//状態
			Integer jyoutai = (Integer) httpSession.getAttribute(Const.JYOUTAI);

			//1:確認中
			if(jyoutai == null || jyoutai == 1){

				T_0160 t_0160 = getT_0160(thisForm);

				Integer rtnInt = 1;

				SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

				try {
					rtnInt = DbUtil.getInstance().insert(sqlSession, t_0160);
					sqlSession.commit();
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					sqlSession.close();
				}

				String moji;

				if(rtnInt == 1) {
					moji = "登録しました";
					httpSession.setAttribute(Const.JYOUTAI, 2);
				} else {
					moji = "登録できませんでした";
				}

				thisForm.setMsg(moji);
				return thisGamen;
			}else{
				httpSession.setAttribute(Const.JYOUTAI, 1);
				//2:確認終了
				return Service.getInstance().backGamen(request, model, thisForm);
			}
		}else{
			//1:確認中
//			put(Const.JYOUTAI, 1, httpSession);
			httpSession.setAttribute(Const.JYOUTAI, 1);

			thisForm.setMsg(msg);
			return thisGamen;
		}
	}

	/**
	 * 入力内容チェック処理
	 * @param thisForm
	 * @return
	 */
	private String validate(String id, String password){

		Integer idLength = id.trim().length();
		Integer passwordLength = password.trim().length();

		T_0160 t_0160 = select(id);

		if(t_0160 != null) {
			return "ユーザＩＤが既に存在します";
		}
		if(idLength < 1){
			return "ユーザＩＤを入力して下さい";
		}
		if(passwordLength < 1){
			return "パスワードを入力して下さい";
		}

//		if(passwordLength < 4){
//			return "パスワードは４文字以上にして下さい";
//		}
//
//		//指定文字列に半角英字（大文字）が何文字含まれるか
//		Integer count3 = StringUtil.countExistHankakuEijiB(password);
//
//		if(count3 > 0){
//			return "パスワードに全角を含めないで下さい";
//		}
//
//		//指定文字列に記号が何文字含まれるか
//		Integer count1 = StringUtil.countExistKigou(password);
//		//指定文字列に半角英字（小文字）が何文字含まれるか
//		Integer count2 = StringUtil.countExistHankakuEijiL(password);
//		//指定文字列に数字が何文字含まれるか
//		Integer count4 = StringUtil.countExistSuuji(password);
//
//		if(count1 < 1 || count2 < 1 || count4 < 1){
//			return "パスワードは半角英数記号を含む文字にして下さい";
//		}

		return "";
	}

	private T_0160 select(String userId) {

		Map<String, Object> map = new HashMap<>();

		map.put("corpCd"	, Const.CORP_CD);
		map.put("userId"	, userId);
		map.put("delFlg"	, "0");

		T_0160 t_0160 = (T_0160) BeanUtil.getInstance().makeBean(new T_0160(), map);

    	String sql = DbUtil.getInstance().getSelectSql(t_0160, SystemConst.PM_KEY_T_0160);

		t_0160 = (T_0160) DbUtil.getInstance().selectFirstOneRec(t_0160, sql);


//		t_0160 = (T_0160) DbUtil.getInstance().selectFirstOneRec(t_0160, SystemConst.PM_KEY_T_0160);
		return t_0160;
	}

	private T_0160 getT_0160(FT091F01Form thisForm) {

		String comDateTime = DateUtil.getInstance().getComDateTime(5);

		String pswd = thisForm.getPassword().trim();

		String id = thisForm.getId().trim();

		//パスワードハッシュ化
		pswd = StringUtil.getInstance().encryption(pswd, Const.CODE_SHA_512);

		Map<String, Object> map = new HashMap<>();

		map.put("corpCd"	, Const.CORP_CD);
		map.put("userId"	, id);
		map.put("setPswd"	, pswd);
		map.put("makeYmdhms", comDateTime);
		map.put("updtYmdhms", comDateTime);
		map.put("delFlg"	, "0");

		T_0160 t_0160 = (T_0160) BeanUtil.getInstance().makeBean(new T_0160(), map);

    	return t_0160;
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
