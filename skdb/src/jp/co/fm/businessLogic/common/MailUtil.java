package jp.co.fm.businessLogic.common;

import java.util.Map;

import javax.mail.Message;

public interface MailUtil {

	String DATE_FORMAT_2 = "yyyy MM'/'dd HH':'mm";

	/**
	 *
	 * @param count
	 * @return
	 */
	Map<String, Object> getMailBean(Integer count);

	/**
	 *
	 * @param count
	 * @return
	 */
	Message getMessage(int count);

	/**
	 * メ�?セージ件数取�?
	 * @return
	 */
	Integer getMessageCount();

	/**
	 *
	 */
	Message[] getMessage();

	/**
	 *
	 * @return
	 */
	Boolean open();

	/**
	 *
	 * @return
	 */
	boolean close();

	/**
	 * メールアドレスチェ�?ク
	 * @param mailAddress
	 * @return
	 */
	boolean checkMailAddress(String mailAddress);

	MailUtil mailUtil = new MailUtilImpl();

	public static MailUtil getInstancce() {
		return mailUtil;
	}

}