package cj.studio.imc.be.router.args;

public class UserInfo {
	String appCode;
	String userCode;
	public UserInfo() {
	}
	public UserInfo(String appCode, String userCode) {
		super();
		this.appCode = appCode;
		this.userCode = userCode;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
}
