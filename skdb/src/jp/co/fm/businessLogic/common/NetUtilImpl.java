package jp.co.fm.businessLogic.common;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

class NetUtilImpl implements NetUtil {

	/**
	 * 自分の IP アドレスを取得する
	 * @return
	 * @throws SocketException
	 */
	@Override
	public List<String> getHostAddressList() throws SocketException {
		List<String> rtnList = new ArrayList<>();

		for(NetworkInterface n: Collections.list(NetworkInterface.getNetworkInterfaces()) ) {
			for (InetAddress addr : Collections.list(n.getInetAddresses()))  {
				if( addr instanceof Inet4Address && !addr.isLoopbackAddress() ){
					rtnList.add(addr.getHostAddress());
				}
			}
		}
		return rtnList;
	}

	/**
	 *
	 * @param url
	 * @return
	 */
	private List<String> getHostNameAndIpAddress() {
		try{
			InetAddress inetAddress = InetAddress.getLocalHost();
			String address = inetAddress.toString();
			return StringUtil.getInstance().splitStringList(address, "/");
		}catch(Exception e){
			return null;
		}
	}

	/**
	 * ホスト名を取得する
	 * @return
	 */
	@Override
	public String getMyHostName() {
		List<String> l = getHostNameAndIpAddress();
		return l.get(0);
	}

	/**
	 * IPアドレスを取得する
	 * @return
	 */
	@Override
	public String getMyIpAddress() {
		List<String> l = getHostNameAndIpAddress();
		return (String)l.get(1);
	}

	/**
	 * リモート端末の IP アドレス
	 * @param req
	 * @return
	 */
	@Override
	public String getRemoteAddr(HttpServletRequest req) {
		return req.getRemoteAddr();
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	@Override
	public Map<String, List<String>> getHeaderInfo(HttpServletRequest request) {
		Map<String, List<String>> rtnMap = new HashMap<>();
		Enumeration headernames = request.getHeaderNames();
	    while (headernames.hasMoreElements()){
	      String name = (String)headernames.nextElement();
	      Enumeration headervals = request.getHeaders(name);
	      List<String> list = new ArrayList<>();
	      while (headervals.hasMoreElements()){
	        String val = (String)headervals.nextElement();
	        list.add(val);
	      }
	      rtnMap.put(name, list);
	    }
	    return rtnMap;
	}

	/**
	 *
	 * @param strUrl
	 * @return
	 */
	@Override
	public String getReturnUrl(String strUrl){

		URL url = null;

		try {
			url = new URL(strUrl);
		} catch (MalformedURLException e) {

			e.printStackTrace();
			return null;
		}

		String query = URLEncoder.encode("message=テスト");

		int cl = query.length();

		StringBuffer str = new StringBuffer();

		try {
			URLConnection uc = url.openConnection();
			uc.setDoOutput(true);
			uc.setDoInput(true);
			uc.setAllowUserInteraction(false);
			uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			uc.setUseCaches(false);
			DataOutputStream dos = new DataOutputStream(uc.getOutputStream());

			dos.writeBytes(query);
			dos.flush();
			dos.close();

			DataInputStream dis = new DataInputStream(uc.getInputStream());
			String nextline;

			while((nextline=dis.readLine())!=null){
				str.append(nextline+"＼n");
			}

			dis.close();
		} catch (Exception ex) {
			return null;
		}
		return str.toString();
	}

	/*
	 * URLコンテンツをListで返す
	 **/
	@Override
	public List<String> getUrlContents(String url) {
		List<String> l = new ArrayList<>();

		try{
			URL u = new URL(url);
			Object obj = u.getContent();
			if(obj instanceof InputStream){
				BufferedReader br = new BufferedReader(new InputStreamReader((InputStream)obj));
				String line;
				while((line = br.readLine()) != null){
					l.add(line);
				}
				br.close();
			}
			return l;
		}catch(Exception e){
			return null;
		}
	}
}

