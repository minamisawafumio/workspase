package jp.co.fm.businessLogic.table;

 /**
 * ユーザマスタ
 */
public class T_0160 {
    private String corpCd;        //会社コード
    private String userId;        //ユーザＩＤ
    private String delFlg;        //削除フラグ
    private String pswd;          //パスワード
    private String pswdOld;       //パスワード旧
    private String birthYmd;      //生年月日
    private String mailaddress;   //メールアドレス
    private String username;      //ユーザ名
    private String makeYmdhms;    //作成日時
    private String updtYmdhms;    //更新日時
    private String makeUsr;       //作成者
    private String updtUsr;       //更新者


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

    public String getDelFlg() {
       return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    public String getPswd() {
       return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getPswdOld() {
       return pswdOld;
    }

    public void setPswdOld(String pswdOld) {
        this.pswdOld = pswdOld;
    }

    public String getBirthYmd() {
       return birthYmd;
    }

    public void setBirthYmd(String birthYmd) {
        this.birthYmd = birthYmd;
    }

    public String getMailaddress() {
       return mailaddress;
    }

    public void setMailaddress(String mailaddress) {
        this.mailaddress = mailaddress;
    }

    public String getUsername() {
       return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
