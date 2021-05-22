package jp.co.fm.businessLogic.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;


public class MailUtilImpl implements MailUtil {

	private String host; // ホストアドレス
	private String user; // アカウン�?
	private String password; // パスワー�?

	private Folder folder = null;
	private Store  store = null;

	public MailUtilImpl(){

	}

	/**
	 *
	 * @param host ホストアドレス
	 * @param user アカウン�?
	 * @param password パスワー�?
	 */
	public MailUtilImpl(String host, String user, String password){
		this.host = host;
		this.user = user;
		this.password=password;
	}

	/**
	 *
	 * @param count
	 * @return
	 */
	@Override
	public Map<String, Object> getMailBean(Integer count){

		Message message = getMessage(count);

		if (message == null) {
			return null;
		}

		Map<String, Object> mailBean = new HashMap<>();

	    try{
		    Address[] address = message.getFrom();

		    // 差出人
		    if (address != null) {
		    	List<String> fromList = new ArrayList<>();
		    	for (Integer j = 0; j < address.length; j++) {
		    		fromList.add(MimeUtility.decodeText(address[j].toString()));
		    	}
		    	mailBean.put("FromList", fromList);
		    }

		    address = message.getRecipients(RecipientType.TO);

		    // 宛�??
		    if (address != null) {
		    	List<String> toList = new ArrayList<>();
		    	for (Integer j = 0; j < address.length; j++) {
		    		toList.add(MimeUtility.decodeText(address[j].toString()));
		    	}

		    	mailBean.put("ToList", toList);
		    }

		    // 題名
		    mailBean.put("Subject", message.getSubject());


		    // 日�?
		    Date date = DateUtil.getInstance().changeDate(message.getSentDate());

		    if(date != null){
		    	String dayFormat = DateUtil.getInstance().getYyyyMMdd(date);
		    	mailBean.put("SentDate", dayFormat);

		    }
		    // サイズ
		    mailBean.put("Size", message.getSize());

		    // �?容
		    mailBean.put("Content", message.getContent());
	    }catch(Exception e){
	    	e.printStackTrace();
            return null;
	    }

		return mailBean;
	}

	/**
	 *
	 * @param count
	 * @return
	 */
	@Override
	public Message getMessage(int count){

		try {
			Message message = folder.getMessage(count);
			return message;
		} catch (MessagingException e) {

		}
		return null;
	}

	/**
	 * メ�?セージ件数取�?
	 * @return
	 */
	@Override
	public Integer getMessageCount(){
		try {
			return folder.getMessageCount();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 *
	 */
	@Override
	public Message[] getMessage(){
		Message[] messages = null;

		try {
			messages = folder.getMessages();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return messages;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public Boolean open(){
		boolean rtnBool = false;
		// 接続しま�?
		Session session = Session.getDefaultInstance(System.getProperties(), null);

		try {
			store = session.getStore("pop3");
			store.connect(host, -1, user, password);
			folder = store.getFolder("INBOX");
			// フォル�?ーを開きま�?
			folder.open(Folder.READ_ONLY);
			rtnBool = true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return rtnBool;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public boolean close() {
		boolean rtnBool = false;

		try {
			folder.close(false);
			store.close();
			rtnBool = true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return rtnBool;
	}

	/**
	 * メールアドレスチェ�?ク
	 * @param mailAddress
	 * @return
	 */
	@Override
	public boolean checkMailAddress(String mailAddress) {
		try {
			new InternetAddress(mailAddress, true);
		}
		catch (AddressException e) {
			return false;
		}
		return true;
	}
}
