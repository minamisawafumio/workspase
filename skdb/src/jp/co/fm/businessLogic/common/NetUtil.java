package jp.co.fm.businessLogic.common;

import java.net.SocketException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface NetUtil {

	/**
	 * 自分の IP アドレスを取得する
	 * @return
	 * @throws SocketException
	 */
	List<String> getHostAddressList() throws SocketException;

	/**
	 * ホスト名を取得する
	 * @return
	 */
	String getMyHostName();

	/**
	 * IPアドレスを取得する
	 * @return
	 */
	String getMyIpAddress();

	/**
	 * リモート端末の IP アドレス
	 * @param req
	 * @return
	 */
	String getRemoteAddr(HttpServletRequest req);

	/**
	 *
	 * @param request
	 * @return
	 */
	Map<String, List<String>> getHeaderInfo(HttpServletRequest request);

	/**
	 *
	 * @param strUrl
	 * @return
	 */
	String getReturnUrl(String strUrl);

	/*
	 * URLコンテンツをListで返す
	 **/
	List<String> getUrlContents(String url);

	NetUtil netUtil = new NetUtilImpl();

	public static NetUtil getInstance() {
		return netUtil;
	}
}