package cj.studio.imc.be.online.program.stub;

import java.util.List;

import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;
import cj.studio.imc.be.online.args.DeviceInfo;
import cj.studio.imc.be.online.args.TerminusOnlineLifetime;
import cj.studio.imc.be.online.args.UserInfo;
import cj.studio.imc.be.online.service.ITerminusOnlineLifetimeService;
import cj.studio.imc.be.online.stub.ITerminusOnlineLifetimeStub;

@CjService(name = "/terminus/online.service")
public class TerminusOnlineLifetimeStub extends GatewayAppSiteRestStub implements ITerminusOnlineLifetimeStub {
	@CjServiceRef(refByName = "online_plugin.online")
	ITerminusOnlineLifetimeService online;

	@Override
	public void onTerminus(String path) {
		online.onTerminus(path);
	}

	@Override
	public void onDevice(String path, DeviceInfo device) {
		online.onDevice(path, device);
	}

	@Override
	public void onUser(String path, UserInfo user) {
		online.onUser(path, user);
	}

	@Override
	public void offTerminus(String path) {
		online.offTerminus(path);
	}

	@Override
	public void offUser(UserInfo user) {
		online.offUser(user);
	}


	@Override
	public void offDevice(String uuid) {
		online.offDevice(uuid);
	}

	@Override
	public List<TerminusOnlineLifetime> page(int currPage, int pageSize) {
		return online.page(currPage, pageSize);
	}

	@Override
	public List<TerminusOnlineLifetime> getByUser(UserInfo user) {
		return online.getByUser(user);
	}

	@Override
	public TerminusOnlineLifetime getByDevice(String uuid) {
		return online.getByDevice(uuid);
	}

	@Override
	public TerminusOnlineLifetime getByPath(String path) {
		return online.getByPath(path);
	}

	@Override
	public long onlineUserCount() {
		return online.onlineUserCount();
	}

	@Override
	public long onlineTerminusCount() {
		return online.onlineTerminusCount();
	}

}
