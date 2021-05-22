package jp.co.fm.businessLogic.table;

 /**
 * ユーザ明細
 */
public class T_0161 {
    private String corpCd;            //会社コード
    private String userId;            //ユーザＩＤ
    private Integer userIdSerialNo;   //ユーザ明細番号
    private String userKey;           //ユーザキー
    private String delFlg;            //削除フラグ
    private String value;             //値
    private String makeYmdhms;        //作成日時
    private String updtYmdhms;        //更新日時
    private String makeUsr;           //作成者
    private String updtUsr;           //更新者


    public String getCorpCd() {
       return corpCd;
    }

    public void setCorpCd(String corpCd) {
        this.corpCd = corpCd;
    }

    public String getUserId() {
       return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUserIdSerialNo() {
       return userIdSerialNo;
    }

    public void setUserIdSerialNo(Integer userIdSerialNo) {
        this.userIdSerialNo = userIdSerialNo;
    }

    public String getUserKey() {
       return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getDelFlg() {
       return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    public String getValue() {
       return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMakeYmdhms() {
       return makeYmdhms;
    }

    public void setMakeYmdhms(String makeYmdhms) {
        this.makeYmdhms = makeYmdhms;
    }

    public String getUpdtYmdhms() {
       return updtYmdhms;
    }

    public void setUpdtYmdhms(String updtYmdhms) {
        this.updtYmdhms = updtYmdhms;
    }

    public String getMakeUsr() {
       return makeUsr;
    }

    public void setMakeUsr(String makeUsr) {
        this.makeUsr = makeUsr;
    }

    public String getUpdtUsr() {
       return updtUsr;
    }

    public void setUpdtUsr(String updtUsr) {
        this.updtUsr = updtUsr;
    }

}
