package cj.studio.imc.be.router.plugin.service;

import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.gateway.stub.annotation.CjStubRef;
import cj.studio.imc.be.online.stub.ITerminusOnlineLifetimeStub;
import cj.studio.imc.be.router.args.DeviceInfo;
import cj.studio.imc.be.router.args.UserInfo;
import cj.studio.imc.be.router.service.IPeerEventService;

@CjBridge(aspects = "@rest")
@CjService(name = "peerEvent")
public class PeerEventService implements IPeerEventService {
	@CjStubRef(remote = "rest://backend/online/", stub = ITerminusOnlineLifetimeStub.class)
	ITerminusOnlineLifetimeStub online;

	@Override
	public void onTerminus(String path) {
		online.onTerminus(path);
	}

	@Override
	public void onDevice(String path, DeviceInfo device) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUser(String path, UserInfo user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void offTerminus(String path) {
		online.offTerminus(path);
	}

	@Override
	public void offDevice(String uuid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void offUser(UserInfo user) {
		// TODO Auto-generated method stub

	}

}
