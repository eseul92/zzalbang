package dto;

import java.sql.Timestamp;

public class Dto {
	String userid;
	String userpw;
	String username;
	String usernickname;
	String useremail;
	String userphone;
	String userdate;
	int loginCheck;
	int bno;
	String btitle;
	String hash;
	String bcontent;
	int upload;
	int del;
	String url;
	Timestamp uploaddate;
	
	public Dto(){
		
	}
	public Dto(String usernickname, int bno, String btitle, String hash, String bcontent, int upload, int del, String url, Timestamp uploaddate){
		this.usernickname = usernickname;
		this.bno = bno;
		this.btitle = btitle;
		this.hash = hash;
		this.bcontent = bcontent;
		this.upload = upload;
		this.del = del;
		this.url = url;
		this.uploaddate = uploaddate;
	}
	//�뙣�뒪�썙�뱶 遺덉씪移� �깮�꽦�옄
	public Dto(String userid, int loginCheck){
		this.userid = userid;
		this.loginCheck = loginCheck;
	}
	
	//�븘�씠�뵒 以묐났�솗�씤 �깮�꽦�옄
	public Dto(String userid){
		this.userid = userid;
	}
	
	//�쉶�썝媛��엯 �깮�꽦�옄
	public Dto(String userid, String userpw, String username, String usernickname, String useremail, String userphone, String userdate){
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.usernickname = usernickname;
		this.useremail = useremail;
		this.userphone = userphone;
		this.userdate = userdate;
	}
	
	//濡쒓렇�씤�븷 �븣 �깮�꽦�옄
	public Dto(String userid, String userpw, String username, String usernickname, String useremail, String userphone, String userdate, int loginCheck){
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.usernickname = usernickname;
		this.useremail = useremail;
		this.userphone = userphone;
		this.userdate = userdate;
		this.loginCheck = loginCheck;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsernickname() {
		return usernickname;
	}
	public void setUsernickname(String usernickname) {
		this.usernickname = usernickname;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public String getUserdate() {
		return userdate;
	}
	public void setUserdate(String userdate) {
		this.userdate = userdate;
	}
	public int getLoginCheck() {
		return loginCheck;
	}
	public void setLoginCheck(int loginCheck) {
		this.loginCheck = loginCheck;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public int getUpload() {
		return upload;
	}
	public void setUpload(int upload) {
		this.upload = upload;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	public Timestamp getUploaddate() {
		return uploaddate;
	}
	public void setUploaddate(Timestamp uploaddate) {
		this.uploaddate = uploaddate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}