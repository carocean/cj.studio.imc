package cj.studio.imc.be.router.args;

public class UserInfo {
	String tenantCode;
	String userCode;
	public UserInfo() {
	}
	public UserInfo(String tenantCode, String userCode) {
		super();
		this.tenantCode = tenantCode;
		this.userCode = userCode;
	}
	public String getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
}
