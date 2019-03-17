package cj.studio.imc.be.online.service;

import java.util.List;

import cj.studio.imc.be.online.args.DeviceInfo;
import cj.studio.imc.be.online.args.TerminusOnlineLifetime;
import cj.studio.imc.be.online.args.UserInfo;

public interface ITerminusOnlineLifetimeService {
	void onTerminus(String path);

	void onDevice(String path, DeviceInfo device);

	void onUser(String path, UserInfo user);

	void offTerminus(String path);

	void offUser(UserInfo user);// 用户从终端生命期上下线,仅仅是清楚其生命期上的用户字段，将不能向该用户推送消息,但终端并不下线

	void offDevice(String uuid);// 将致使终端下线,也就是终端生命期上的设备信息永不为空

	List<TerminusOnlineLifetime> page(int currPage, int pageSize);

	List<TerminusOnlineLifetime> getByUser(UserInfo user);

	TerminusOnlineLifetime getByDevice(String uuid);

	TerminusOnlineLifetime getByPath(String path);

	long onlineUserCount();

	long onlineTerminusCount();
}
