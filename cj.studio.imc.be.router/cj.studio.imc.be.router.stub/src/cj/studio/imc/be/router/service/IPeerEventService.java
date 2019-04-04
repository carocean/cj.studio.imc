package cj.studio.imc.be.router.service;

import cj.studio.imc.be.router.args.DeviceInfo;
import cj.studio.imc.be.router.args.UserInfo;

public interface IPeerEventService {
	void onTerminus(String path, String peerOnMicNode, String routerOnMicNode);

	void emtpyPeerOnline(String peerOnMicNode);

	void emtpyRouterOnline(String routerOnMicNode);

	void onDevice(String path, DeviceInfo device);

	void onUser(String path, UserInfo user);

	void offTerminus(String path);

	void offDevice(String uuid);

	void offUser(UserInfo user);

}
