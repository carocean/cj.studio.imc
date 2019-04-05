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
	public void onTerminus(String path,String peerOnMicNode,String routerOnMicNode) {
		online.onTerminus(path,peerOnMicNode,routerOnMicNode);
	}
	@Override
	public void emtpyPeerOnline(String peerOnMicNode) {
		online.emtpyPeerOnline(peerOnMicNode);
	}
	@Override
	public void emtpyRouterOnline(String routerOnMicNode) {
		online.emtpyRouterOnline(routerOnMicNode);
	}
	@Override
	public void onDevice(String path, DeviceInfo device) {
		cj.studio.imc.be.online.args.DeviceInfo d = new cj.studio.imc.be.online.args.DeviceInfo();
		d.setDesc(device.getDesc());
		d.setDisplayName(device.getDisplayName());
		d.setUuid(device.getUuid());
		d.setVersion(device.getVersion());
		online.onDevice(path, d);
	}

	@Override
	public void onUser(String path, UserInfo user) {
		cj.studio.imc.be.online.args.UserInfo d = new cj.studio.imc.be.online.args.UserInfo();
		d.setTenantCode(user.getTenantCode());
		d.setUserCode(user.getUserCode());
		online.onUser(path, d);

	}

	@Override
	public void offTerminus(String path) {
		online.offTerminus(path);
	}

	@Override
	public void offDevice(String uuid) {
		online.offDevice(uuid);
	}

	@Override
	public void offUser(UserInfo user) {
		cj.studio.imc.be.online.args.UserInfo d = new cj.studio.imc.be.online.args.UserInfo();
		d.setTenantCode(user.getTenantCode());
		d.setUserCode(user.getUserCode());
		online.offUser(d);
	}

}
