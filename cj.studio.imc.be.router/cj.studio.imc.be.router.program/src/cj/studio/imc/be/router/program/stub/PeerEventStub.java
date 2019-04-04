package cj.studio.imc.be.router.program.stub;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.annotation.CjServiceSite;
import cj.studio.ecm.net.Circuit;
import cj.studio.ecm.net.Frame;
import cj.studio.gateway.IMicNode;
import cj.studio.gateway.socket.util.SocketContants;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;
import cj.studio.imc.be.router.args.DeviceInfo;
import cj.studio.imc.be.router.args.UserInfo;
import cj.studio.imc.be.router.service.IPeerEventService;
import cj.studio.imc.be.router.stub.IPeerEventStub;
@CjService(name = "/event/peer.service")
public class PeerEventStub extends GatewayAppSiteRestStub implements IPeerEventStub {
	@CjServiceRef(refByName = "router@plugin.peerEvent")
	IPeerEventService peer;
	@CjServiceSite
	IServiceSite site;
	@Override
	protected Object doMethod(GatewayAppSiteRestStub stub, Method m, Object[] args, Frame frame, Circuit circuit)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		switch (m.getName()) {
		case "onTerminus":
		case "onDevice":
		case "onUser":
		case "offTerminus":
			String path = String.format("on://%s/%s", args[0], frame.head(SocketContants.__frame_fromPipelineName));
			args[0] = path;
			break;
		}
		return super.doMethod(stub, m, args, frame, circuit);
	}

	@Override
	public void onTerminus(String path,String micNode) {
		IMicNode node=(IMicNode)site.getService(IMicNode.SERVICE_KEY);
		peer.onTerminus(path,micNode,node.guid());

	}
	@Override
	public void emtpyPeerOnline(String peerMicNode) {
		peer.emtpyPeerOnline(peerMicNode);
	}
	@Override
	public void onDevice(String path, DeviceInfo device) {
		peer.onDevice(path, device);
	}

	@Override
	public void onUser(String path, UserInfo user) {
		peer.onUser(path, user);
	}

	@Override
	public void offTerminus(String path) {
		peer.offTerminus(path);
	}

	@Override
	public void offDevice(String uuid) {
		peer.offDevice(uuid);
	}

	@Override
	public void offUser(UserInfo user) {
		peer.offUser(user);
	}

}
