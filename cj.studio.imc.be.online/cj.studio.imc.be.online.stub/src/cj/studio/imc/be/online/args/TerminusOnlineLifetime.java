package cj.studio.imc.be.online.args;

public class TerminusOnlineLifetime {
	String path;// 终端所在的路径，它在生命期上是唯一的,terminus://terminuschannel/routerchannel
	String peerOnMicNode;
	String routerOnMicNode;
	DeviceInfo device;
	UserInfo user;
	long ctime;

	public String getPeerOnMicNode() {
		return peerOnMicNode;
	}

	public String getRouterOnMicNode() {
		return routerOnMicNode;
	}

	public void setPeerOnMicNode(String peerOnMicNode) {
		this.peerOnMicNode = peerOnMicNode;
	}

	public void setRouterOnMicNode(String routerOnMicNode) {
		this.routerOnMicNode = routerOnMicNode;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public DeviceInfo getDevice() {
		return device;
	}

	public void setDevice(DeviceInfo device) {
		this.device = device;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public long getCtime() {
		return ctime;
	}

	public void setCtime(long ctime) {
		this.ctime = ctime;
	}

}
