package cj.studio.imc.be.online.args;

public class DeviceInfo {
	String uuid;
	String displayName;
	String version;
	String desc;
	public DeviceInfo() {
	}
	public DeviceInfo(String uuid, String displayName, String version, String desc) {
		super();
		this.uuid = uuid;
		this.displayName = displayName;
		this.version = version;
		this.desc = desc;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
