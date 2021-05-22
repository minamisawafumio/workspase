package jp.co.fm.businessLogic.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.util.bean.pdf.PdfDataBean;

import jp.co.fm.businessLogic.common.CommonUtil;
import jp.co.fm.businessLogic.common.Const;
import jp.co.fm.businessLogic.common.DateUtil;
import jp.co.fm.businessLogic.common.DbUtil;
import jp.co.fm.businessLogic.common.FileUtil;
import jp.co.fm.businessLogic.common.JsonUtil;
import jp.co.fm.businessLogic.common.PdfUtil;
import jp.co.fm.businessLogic.common.Service;
import jp.co.fm.businessLogic.common.StringUtil;
import jp.co.fm.businessLogic.common.ZipUtil;
import jp.co.fm.businessLogic.system.SystemConst;
import jp.co.fm.businessLogic.system.SystemInfo;
import jp.co.fm.businessLogic.system.SystemService;
import jp.co.fm.businessLogic.table.DbToolMapper;
import jp.co.fm.businessLogic.table.T_1010;
import jp.co.fm.presentation.form.FT001F01Form;
import jp.co.fm.presentation.form.FT001F02Form;
import jp.co.fm.presentation.form.FT001F03Form;
import jp.co.fm.presentation.form.FT001F05Form;
import jp.co.fm.presentation.form.FT001F06Form;
import jp.co.fm.presentation.form.FT001F07_2Form;
import jp.co.fm.presentation.form.FT001F08Form;
import jp.co.fm.presentation.form.FT00ShogiForm;
import manga.form.FT001F0733Form;
import manga.form.FT001F0734Form;


@Controller
@RequestMapping("FT001F02")
public class FT001F02 {

	String thisGamen = "FT001F02";

	@Autowired(required=false)
    private FT001F02Form thisForm;

	/**
	 *
	 * @param form
	 * @param model
	 */
    @ModelAttribute("FT001F02Form")
	public void init(@ModelAttribute FT001F02Form form, Model model) {
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

    	return thisGamen;
    }

    /**
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "form", params = "goto03", method = RequestMethod.POST)
    public String gotoD(Model model, HttpServletRequest request) {

    	FT001F03Form form = new FT001F03Form();

    	String[] sampleCheckArray = {};//{"checked", "checked"};

        String[] datas = {};//{"checked", "checked"};

        form.setSampleCheckArray(sampleCheckArray);

        String nextGamen = Service.getInstance().gotoNextGamen(request, thisForm, form, model);

        return nextGamen;
    }

    /**
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "form", params = "goto05", method = RequestMethod.POST)
    public String gotoC(Model model, HttpServletRequest request) {
    	FT001F05Form form = new FT001F05Form();

        return Service.getInstance().gotoNextGamen(request, thisForm, form, model);
    }

    /**
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "form", params = "goto06", method = RequestMethod.POST)
    public String gotoG(Model model, HttpServletRequest request) {

    	FT001F06Form form = new FT001F06Form();

        return Service.getInstance().gotoNextGamen(request, thisForm, form, model);
    }

    @RequestMapping(value = "form", params = "goto033", method = RequestMethod.POST)
    public String goto033(Model model, HttpServletRequest request) {
    	FT001F0733Form form = new FT001F0733Form();

    	HttpSession httpSession = request.getSession();

    	List<String> tittleList = new ArrayList<>();

    	httpSession.setAttribute("tittleList", tittleList);

    	String nextGamen = Service.getInstance().gotoNextGamen(request, thisForm, form, model);

        return "manga/" + nextGamen;
    }

    @RequestMapping(value = "form", params = "goto034", method = RequestMethod.POST)
    public String goto034(Model model, HttpServletRequest request) {
    	FT001F0734Form form = new FT001F0734Form();

    	HttpSession httpSession = request.getSession();

    	List<String> tittleList = SystemService.getInstance().getTittleList();

    	httpSession.setAttribute("tittleList", tittleList);

    	String nextGamen = Service.getInstance().gotoNextGamen(request, thisForm, form, model);

        return "video/" + nextGamen;
    }


    /**
    *
    * @param model
    * @param request
    * @return
    */
	@RequestMapping(value = "form", params = "goto07_2", method = RequestMethod.POST)
	public String goto07_2(Model model, HttpServletRequest request) {
		FT001F07_2Form form = new FT001F07_2Form();

	   	HttpSession httpSession = request.getSession();

	   	List<String> tittleList = new ArrayList<String>();//getTittleList();

	   	httpSession.setAttribute("tittleList", tittleList);

	   	String hhh = Service.getInstance().gotoNextGamen(request, thisForm, form, model);

	   	return hhh;
   }

    /**
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "form", params = "goto08", method = RequestMethod.POST)
    public String goto08(Model model, HttpServletRequest request) {
    	FT001F08Form form = new FT001F08Form();

        return Service.getInstance().gotoNextGamen(request, thisForm, form, model);
    }

    /**
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "form", params = "gotoShogi", method = RequestMethod.POST)
    public String gotoShogi(Model model, HttpServletRequest request) {
       	HttpSession httpSession = request.getSession();

       	FT00ShogiForm sakiForm = new FT00ShogiForm();

//		String []gamenData = {
//				"　　　　９　８　７　６　５　４　３　２　１　　　　",
//				"飛３　１京↓桂↓銀↓金↓王↓金↓銀↓桂↓京↓　　　",
//				"角４　２□　飛↓□　□　□　□　□　角↓□□　　　",
//				"金５　３歩↓歩↓歩↓歩↓歩↓歩↓歩↓□　歩↓　飛２",
//				"銀６　４□　□　□　□　□　王↓□　□　□　　角３",
//				"桂７　５□　□　飛↓□　□　□　□　□　□　　金４",
//				"京８　６□　□　□　□　□　□　□　□　□　　銀５",
//				"歩９　７歩↑歩↑歩↑□　歩↑歩↑歩↑歩↑歩↑　桂６",
//				"　　　８□　角↑□　□　□　□　□　飛↑□□　京７",
//				"　　　９京↑桂↑銀↑金↑王↑金↑銀↑桂↑京↑　歩８"
//			};
//		String []gamenData = {
//				"　  　　９　８　７　６　５　４　３　２　１　　　  ",
//				"飛11　１京↓桂↓銀↓金↓王↓金↓銀↓桂↓京↓　　  ",
//				"角11　２□　飛↓□　□　□　□　□　角↓□□　　  ",
//				"金11　３歩↓歩↓歩↓歩↓歩↓歩↓歩↓□　歩↓　飛11",
//				"銀11　４□　□　□　□　□　王↓□　□　□　　角11",
//				"桂11　５□　□　飛↓□　□　□　□　□　□　　金11",
//				"京11　６□　□　□　□　□　□　□　□　□　　銀11",
//				"歩11　７歩↑歩↑歩↑□　歩↑歩↑歩↑歩↑歩↑　桂11",
//				"　  　８□　角↑□　□　□　□　□　飛↑□□　京11",
//				"　  　９京↑桂↑銀↑金↑王↑金↑銀↑桂↑京↑　歩11"
//			};
//
//		Ban ban = ShogiCommon.makeBan2(gamenData);

		httpSession.setAttribute("tesuuCount", 0);

		httpSession.setAttribute("btnCount", 0);

        return Service.getInstance().gotoNextGamen(request, thisForm, sakiForm, model);
    }

    @RequestMapping(value = "form", params = "logout", method = RequestMethod.POST)
    public String logout(Model model, HttpServletRequest request) {
        return logout(request);
    }

    private byte[] StreamToByte(Resource resource) {

        int nRead;
        byte[] fileContent = new byte[16384];
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        //ファイルをバイト形式に変換
        try {

        	String hhhhh = resource.getFile().toString();

        	FileInputStream is = new FileInputStream(hhhhh);

//        	InputStream is = new FileInputStream(hhhhh);

            while ((nRead = is.read(fileContent, 0, fileContent.length)) != -1) {
                  buffer.write(fileContent, 0, nRead);
            }

            buffer.flush();

            buffer.close();

            is.close();

            return buffer.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void OutputSreamWrite(HttpServletResponse response, byte[] fileContent) {
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            os.write(fileContent);
            os.flush();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }


    /**
     * ファイルをダウンロードする
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "form", params = "downLoadFile", method = RequestMethod.POST)
    public String downLoadFile(HttpServletResponse response) {

    	String pathAndfileName = "C:/temp/zip/lll.zip";

    	File file = new File(pathAndfileName);

    	FileInputStream infile = null;

		try {
			infile = new FileInputStream(pathAndfileName);
	    	Long length = file.length();

	    	response.setContentType("text/plain");
	    	response.setContentLength(length.intValue());
	    	response.setHeader("Content-Disposition","attachment; filename=\"" + file.getName() +"\"");
	    	FileCopyUtils.copy(infile, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				infile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

    	return thisGamen;
    }

    /**
     * ファイルをダウンロードする
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "form", params = "downLoadsFile", method = RequestMethod.POST)
    public String downLoadsFile(HttpServletResponse response) throws IOException {

    	String pathAndfileName = "C:/temp/zip/lll.zip";

    	File file = new File(pathAndfileName);

    	FileInputStream infile = new FileInputStream(pathAndfileName) ;

    	Long length = file.length();

    	response.setContentType("text/plain");
    	response.setContentLength(length.intValue());
    	response.setHeader("Content-Disposition","attachment; filename=\"" + file.getName() +"\"");
    	FileCopyUtils.copy(infile, response.getOutputStream());

    	infile.close();

    	return thisGamen;
    }

    /**
     * ＺＩＰファイルをダウンロードする
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "form", params = "downLoadZip", method = RequestMethod.POST)
    public String downLoadZip(HttpServletResponse response) throws IOException {

    	String pathExcel = "C:/pleiades-e4.6/excel";
    	String pathZip   = "C:/pleiades-e4.6/zip";

    	String pathAndfileName = pathExcel + "/" + "aaa.xlsx";

    	String zipFileName = DateUtil.getInstance().getComDateSt();

    	String pathAndZipFileName = pathZip + "/" + zipFileName + ".zip";

    	ZipUtil.getInstance().folderToZip(pathExcel, pathAndZipFileName);

    	File file = new File(pathAndZipFileName);

    	FileInputStream infile = new FileInputStream(pathAndZipFileName) ;

    	Long length = file.length();

    	response.setContentType("text/plain; charset=UTF-8");
    	response.setContentLength(length.intValue());
    	response.setHeader("Content-Disposition","attachment; filename=\"" + file.getName() +"\"");
    	FileCopyUtils.copy(infile, response.getOutputStream());

    	infile.close();

    	return thisGamen;
    }

    /**
     * 文字列ハッシュ化
     * @param model
     * @return
     */
    @RequestMapping(value = "form", params = "hash", method = RequestMethod.POST)
    public String hash(Model model) {

    	String dateTime = DateUtil.getInstance().getComDateSt();

        String data = DbUtil.getInstance().sha512(dateTime);

        String []array = thisForm.getMapValues();

        String []arrayOv = thisForm.getOptionValues();

        System.out.println("sampleCheck1=" + thisForm.getSampleCheck1());
        System.out.println("sampleCheck2=" + thisForm.getSampleCheck2());
        System.out.println("sampleCheck3=" + thisForm.getSampleCheck3());

        System.out.println("OptionValues=" + arrayOv[0]);
        System.out.println("MapValues=" + array[0]);

		return thisGamen;
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "form", params = "dbInsert", method = RequestMethod.POST)
    public String dbInsert(Model model) {

        String comDateTime = DateUtil.getInstance().getComDateSt().substring(0, 8);

        SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

        DbToolMapper db = sqlSession.getMapper(DbToolMapper.class);

        Object []array = {"01", "098", comDateTime, "AABBCC"};

        String moji = "insert into t_1010 (corp_cd,rec_cd,start_ymd,item01) values ('?','?','?','?')";

        String sql = StringUtil.getInstance().bindMoji(moji, array);

        try{
        	db.insert(sql);

            sqlSession.commit();
        }catch(Exception e){
        	e.printStackTrace();
//        	log(Level.ERROR, e.getClass().getSimpleName() + " インサートに失敗しました");
        }finally{
        	sqlSession.close();
        }

		return thisGamen;
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "form", params = "dbInsertBean", method = RequestMethod.POST)
    public String dbInsertBean(Model model) {

        T_1010 t_1010 = new T_1010();

        t_1010.setCorpCd("01");

        List<Object> objectList = DbUtil.getInstance().selectNotNullMember(t_1010);

        T_1010 obj = (T_1010) objectList.get(0);

    	String comDateTime = DateUtil.getInstance().getComDateSt();

    	obj.setItem01(comDateTime);

        obj.setItem02(comDateTime);

        SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

        String sql = DbUtil.getInstance().getInsertSqlNotNullMember(obj);

        sqlSession.getMapper(DbToolMapper.class).insert(sql);

        sqlSession.commit();

        sqlSession.close();

		return thisGamen;
    }

    @RequestMapping(value = "form", params = "dbUpdate", method = RequestMethod.POST)
    public String dbUpdate(Model model) {

    	String comDateTime = DateUtil.getInstance().getComDateSt();

        SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

        DbToolMapper db = sqlSession.getMapper(DbToolMapper.class);

        Object []array = {comDateTime, "01", "013", "00010101", "00000000003"};

        String moji = "update t_1010 set item02='?' where corp_cd='?' and rec_cd='?' and start_ymd='?' and item01='?'";

        String sql = StringUtil.getInstance().bindMoji(moji, array);

        db.update(sql);

        sqlSession.commit();

        sqlSession.close();

		return thisGamen;
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "form", params = "dbUpdateBean", method = RequestMethod.POST)
    public String dbUpdateBean(Model model) {

        T_1010 t_1010 = new T_1010();

        t_1010.setCorpCd("01");

        List<Object> objectList = DbUtil.getInstance().selectNotNullMember(t_1010);

        T_1010 obj = (T_1010) objectList.get(0);

    	String comDateTime = DateUtil.getInstance().getComDateSt();

        obj.setItem02(comDateTime);

        SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

        String sql = DbUtil.getInstance().getUpdateSql(SystemConst.PM_KEY_T_1010, obj);
        sqlSession.getMapper(DbToolMapper.class).update(sql);

        sqlSession.commit();

        sqlSession.close();

		return thisGamen;
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "form", params = "dbDelete", method = RequestMethod.POST)
    public String dbDelete(Model model) {

        SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

        DbToolMapper db = sqlSession.getMapper(DbToolMapper.class);

        Object []array = {"099"};

        String moji = "delete from t_1010 where rec_cd='?'";

        String sql = StringUtil.getInstance().bindMoji(moji, array);

        Integer retInt = db.delete(sql);

        sqlSession.commit();

        sqlSession.close();

		return thisGamen;
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "form", params = "dbSelect", method = RequestMethod.POST)
    public String dbSelect(Model model) {

        SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

        DbToolMapper db = sqlSession.getMapper(DbToolMapper.class);

        List<Map<String, Object>> rset = db.select("select * from t_1010 limit 1");

        Map<String, Object> m = rset.get(0);

        T_1010 t_1010 = (T_1010) DbUtil.getInstance().mekeObject(new T_1010(), m);

        sqlSession.close();

		return thisGamen;
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "form", params = "dbSelectBean", method = RequestMethod.POST)
    public String dbSelectBean(Model model) {

        SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

        DbToolMapper db = sqlSession.getMapper(DbToolMapper.class);

        List<Map<String, Object>> rset = db.select("select * from t_1010 limit 1");

        Map<String, Object> m = rset.get(0);

        T_1010 t_1010 = (T_1010) DbUtil.getInstance().mekeObject(new T_1010(), m);

        String []primaryKey = {"corp_cd", "rec_cd", "start_ymd", "item01"};

        String sql = DbUtil.getInstance().getSelectSql(t_1010, primaryKey);

        t_1010 = (T_1010) DbUtil.getInstance().selectFirstOneRec(sqlSession, t_1010, sql);

//        rset = db.select(sql);
//
//        m = rset.get(0);
//
//        T_1010 t_1010_2 = (T_1010) DbUtil.getInstance().mekeObject(t_1010, m);

        sqlSession.close();

		return thisGamen;
    }

    @RequestMapping(value = "form", params = "fileCopy", method = RequestMethod.POST)
    public String fileCopy(Model model) {

    	String fileName = "aaa.xlsx";

    	String baseDirName = "C:/pleiades-e4.6/excel";

    	String dateTime = DateUtil.getInstance().getComDateSt();

    	String copyDirName = baseDirName + "/"  + dateTime;

    	FileUtil.getInstance().mkDirs(copyDirName);

    	String inPathAndfileName = baseDirName + "/" + fileName;

    	String outPathAndfileName = copyDirName + "/" + fileName;

    	FileUtil.getInstance().fileCopy(inPathAndfileName, outPathAndfileName);

    	FileUtil.getInstance().fileCopy(outPathAndfileName, inPathAndfileName);

    	File file = new File(copyDirName);

    	FileUtil.getInstance().recursiveDeleteFile(file);

		return thisGamen;
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "form", params = "doZip", method = RequestMethod.POST)
    public String doZip(Model model) {

    	String path = "C:/temp/zip/aaaaa";

    	String jjj = "C:/temp/zip/lll.zip";

    	ZipUtil.getInstance().folderToZip(path, jjj);

    	return thisGamen;
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "form", params = "doExp", method = RequestMethod.POST)
    public String doExp(Model model) {

    	Integer x = 0;
    	Integer y = 1000;
    	Integer z = y / x;

    	return thisGamen;
    }


	public String button_test01(HttpServletRequest request) {

		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		System.out.println("menuStart");

		map.put("serverStartTime", (String) SystemInfo.getInstance().getValue(Const.SERVER_START_TIME));

		Map<String, Object> map2 = new HashMap<>();
		map2.put("key01", "11111");
		map2.put("key02", "22222");
		map2.put("key03", 111.222);

		String hhh = JsonUtil.getInstance().makeObjectToJsonString(map2);

		map.put("map2333", hhh);

		String jsonData = SystemService.getInstance().makeJsonData(map);

		return jsonData;
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	public String menuStart(HttpServletRequest request) {

		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		System.out.println("menuStart");

		map.put("serverStartTime", (String) SystemInfo.getInstance().getValue(Const.SERVER_START_TIME));

		String jsonData = SystemService.getInstance().makeJsonData(map);

		return jsonData;
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	public String logout(HttpServletRequest request) {

		HttpSession httpSession = request.getSession();

		httpSession.setAttribute(Const.USER_ID, null);
		httpSession.setAttribute(Const.USER_PASSWORD, null);

		//ログイン画面初期化
		FT001F01Form form = new FT001F01Form();

		httpSession.setAttribute("fT001F01Form", form);

		String comDateTime = DateUtil.getInstance().getComDateTime(5);

		String nextUrl = (String) SystemInfo.getInstance().getValue(Const.URL);

		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		map.put("comDateTime", comDateTime);
		map.put("nextUrl"	 , nextUrl);

		String jsonData = SystemService.getInstance().makeJsonData(map);

		return jsonData;
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	public String getPages(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();

		SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		map.put("sessionId", httpSession.getId());

		map = MangaDb.getInstacce().getPics(sqlSession, map);

		sqlSession.close();

		String jsonData = SystemService.getInstance().makeJsonData(map);

		return jsonData;
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	public String syori001(HttpServletRequest request) {
		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String methodName	= (String) map.get("methodName");
		String start	= (String) map.get("start");
		String end		= (String) map.get("end");

		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map2 = new HashMap<>();
		map2.put("start"	, start);
		map2.put("end"	, end);
		list.add(map2);

		String jsonData = CommonUtil.getInstance().makeJsonSt(
				"methodName", "ret" + StringUtil.getInstance().headUpperCase(methodName),
				"list"		, list
		);

		return jsonData;
	}


	/**
	 * 処理００２
	 * @param request
	 * @return
	 */
	public String syori002(HttpServletRequest request) {

		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String methodName	= (String) map.get("methodName");
		String start		= (String) map.get("start");
		String end			= (String) map.get("end");

		String comDateTime = DateUtil.getInstance().getComDateTime(5);

		String pdfFileName  = "pdf_" + comDateTime + ".pdf";

		String jpegFileName = "jpeg_" + comDateTime + ".jpeg";

		String pdfPath = (String) SystemInfo.getInstance().getValue(Const.PDF_PATH);

		PdfDataBean pdfDataBean = PdfUtil.getInstance().makePdfDataBean();

		pdfDataBean.setFileName(pdfFileName);

		String jsonData = "";

		try{
			String json = JsonUtil.getInstance().makeObjectToJsonString(pdfDataBean);

			PdfDataBean pdfDataBean99 = (PdfDataBean) JsonUtil.getInstance().makeJsonStringToObject(pdfDataBean, json);

			PdfUtil.getInstance().pdfPrint(pdfPath, pdfDataBean99);

			String pathFilePdf  = pdfPath + pdfFileName;
			String pathFileJpeg = pdfPath + jpegFileName;

			//PDF⇒PEG
			PdfUtil.getInstance().pdfToJpeg(pathFilePdf, pathFileJpeg, 3);

			String imgString = FileUtil.getInstance().file2string(pathFileJpeg);

			List<Map<String, Object>> list = new ArrayList<>();
			Map<String, Object> map2 = new HashMap<>();

			map2.put("start", start);
			map2.put("end", end);
			list.add(map2);

			//PDFファイル削除
			FileUtil.getInstance().recursiveDeleteFile(pathFilePdf);

			//JPEGファイル削除
			FileUtil.getInstance().recursiveDeleteFile(pathFileJpeg);

			map.put("methodName"	, methodName);
			map.put("dataList"		, list);
			map.put("comDateTime"	, comDateTime);
			map.put("pdfFileName"	, pdfFileName);
			map.put("imgString"		, imgString);

			jsonData = SystemService.getInstance().makeJsonData(map);

		}catch(Exception e){

		}
		return jsonData;
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	public String syori005(HttpServletRequest request) {
		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String methodName	= (String) map.get("methodName");
		String start		= (String) map.get("start");
		String end			= (String) map.get("end");

		String comDateTime = DateUtil.getInstance().getComDateTime(5);

		String pdfFileName  = "pdf_" + comDateTime + ".pdf";

		String jpegFileName = "jpeg_" + comDateTime + ".jpeg";

		String pdfPath = (String) SystemInfo.getInstance().getValue(Const.PDF_PATH);

		PdfDataBean pdfDataBean = PdfUtil.getInstance().makePdfDataBean();

		pdfDataBean.setFileName(pdfFileName);

		String json = JsonUtil.getInstance().makeObjectToJsonString(pdfDataBean);

		PdfDataBean pdfDataBean99 = (PdfDataBean) JsonUtil.getInstance().makeJsonStringToObject(pdfDataBean, json);

		PdfUtil.getInstance().pdfPrint(pdfPath, pdfDataBean99);

		String pathFilePdf  = pdfPath + pdfFileName;

		//作成したPDFを文字列に変換する
		String imgString = FileUtil.getInstance().file2string(pathFilePdf);

		String pathFileJpeg = pdfPath + jpegFileName;

		//PDF⇒PEG
		PdfUtil.getInstance().pdfToJpeg(pathFilePdf, pathFileJpeg, 3);

		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map1 = new HashMap<>();

		map1.put("start", start);
		map1.put("end", end);
		list.add(map1);

		String jsonData = CommonUtil.getInstance().makeJsonSt(
				"methodName"	, "ret" + StringUtil.getInstance().headUpperCase(methodName),
				"list"			, list,
				"comDateTime"	, comDateTime,
				"pdfFileName"	, pdfFileName,
				"imgString"  	, imgString
		);

		//PDFファイル削除
		FileUtil.getInstance().recursiveDeleteFile(pathFilePdf);

		//JPEGファイル削除
		FileUtil.getInstance().recursiveDeleteFile(pathFileJpeg);

		return jsonData;
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	public String exe03_2(HttpServletRequest request) {

		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String methodName	= (String) map.get("methodName");
		String inKey		= "abc";

		List<String> list = new ArrayList<>();

		list.add("aaaaa");

		String jsonData = CommonUtil.getInstance().makeJsonSt(
				"methodName", "ret" + StringUtil.getInstance().headUpperCase(methodName),
				"array"		, list,
				"inKey"		, inKey
		);

		return jsonData;
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	public String abcde(HttpServletRequest request) {
		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String methodName	= (String) map.get("methodName");

		String jsonData = CommonUtil.getInstance().makeJsonSt(
				"methodName", "ret" + StringUtil.getInstance().headUpperCase(methodName)
		);

		return jsonData;
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	public String syori003(HttpServletRequest request) {

		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String methodName = (String) map.get("methodName");
		String start = (String) map.get("start");
		String end   = (String) map.get("end");

		String comDateTime = DateUtil.getInstance().getComDateTime(5);

		String pdfFileName  = "pdf_" + comDateTime + ".pdf";

		String jpegFileName = "jpeg_" + comDateTime + ".jpeg";

		String pdfPath = (String) SystemInfo.getInstance().getValue(Const.PDF_PATH);

		PdfDataBean pdfDataBean = PdfUtil.getInstance().makePdfDataBean();

		pdfDataBean.setFileName(pdfFileName);

		String jsonData = "";

		String json = JsonUtil.getInstance().makeObjectToJsonString(pdfDataBean);

		PdfDataBean pdfDataBean99 = (PdfDataBean) JsonUtil.getInstance().makeJsonStringToObject(pdfDataBean, json);

		PdfUtil.getInstance().pdfPrint(pdfPath, pdfDataBean99);

		String pathFilePdf  = pdfPath + pdfFileName;
		String pathFileJpeg = pdfPath + jpegFileName;

		//PDF⇒PEG
		PdfUtil.getInstance().pdfToJpeg(pathFilePdf, pathFileJpeg, 3);

		String imgString = FileUtil.getInstance().file2string(pathFileJpeg);

		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map2 = new HashMap<>();

		map2.put("start", start);
		map2.put("end", end);
		list.add(map2);

		jsonData = CommonUtil.getInstance().makeJsonSt(
			"methodName", "ret" + StringUtil.getInstance().headUpperCase(methodName),
			"list", list,
			"comDateTime", comDateTime,
			"pdfFileName", pdfFileName,
			"imgString"  , imgString
		);

		//PDFファイル削除
		FileUtil.getInstance().recursiveDeleteFile(pathFilePdf);

		//JPEGファイル削除
   	FileUtil.getInstance().recursiveDeleteFile(pathFileJpeg);

		return jsonData;
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	public String doPopup001(HttpServletRequest request) {
		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String methodName	= (String) map.get("methodName");
		String d1	= (String) map.get("d1");
		String d2	= (String) map.get("d2");
		String d3	= (String) map.get("d3");

		String rtnSt = d1 + ":" + d2 + ":"  + d3;

		String jsonData = CommonUtil.getInstance().makeJsonSt(
				"methodName", "ret" + StringUtil.getInstance().headUpperCase(methodName),
				"rtnSt"		, rtnSt
		);

		return jsonData;
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	public String checkboxClick(HttpServletRequest request) {

		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String inKey		= (String) map.get("inKey");

		String comDateTime = DateUtil.getInstance().getComDateTime(5);

		map.put("inKey", inKey);

		String jsonData = SystemService.getInstance().makeJsonData(map);

		return jsonData;
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	public String exe01(HttpServletRequest request) {

		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String methodName	= (String) map.get("methodName");
		String exe01Text	= StringUtil.getInstance().getSpase((String) map.get("exe01Text"));

		try {
			exe01Text = new String(URLDecoder.decode(exe01Text, "iso-8859-1").getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String comDateTime = DateUtil.getInstance().getComDateTime(5);

		map.put("comDateTime", comDateTime);

		String jsonData = SystemService.getInstance().makeJsonData(map);

		return jsonData;
	}

	/**
	 * リスナー作成用データ編集
	 * @param request
	 * @return
	 */
	public String makeListener(HttpServletRequest request) {

		boolean isLogin = SystemService.getInstance().isLogin(request);

		if(isLogin == false) {
			System.out.println("Not LOGIN");
		}

		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String bookNo = map.get("bookNo").toString();

		String serchBookNo = StringUtil.getInstance().changeFormat("000000", bookNo);

		SqlSession sqlSession = MangaDb.getInstacce().getSqlSession();

		Long count = MangaDb.getInstacce().count_T_0001(sqlSession, serchBookNo);

		T_1010 t_1010 = MangaDb.getInstacce().getMangaRec(sqlSession, serchBookNo);

		map.put("picCount", Long.toString(count));
		map.put("picHeight", t_1010.getItem04());
		map.put("picWidth", t_1010.getItem05());

		map = MangaDb.getInstacce().getPics(sqlSession, map);

		String jsonData = SystemService.getInstance().makeJsonData(map);

		return jsonData;
	}

	public String watchVideo(HttpServletRequest request) {

		boolean isLogin = SystemService.getInstance().isLogin(request);

		if(isLogin == false) {
			System.out.println("Not LOGIN");
		}

		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String jsonData = SystemService.getInstance().makeJsonData(map);

		return jsonData;
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	public String syori006(HttpServletRequest request) {
		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String comDateTime = DateUtil.getInstance().getComDateTime(5);

		map.put("comDateTime", comDateTime);
		String jsonData = SystemService.getInstance().makeJsonData(map);

		return jsonData;
	}
}
