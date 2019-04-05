package cj.studio.imc.fe.channel.program;

import cj.studio.ecm.CJSystem;
import cj.studio.ecm.IEntryPointActivator;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.context.IElement;
import cj.studio.ecm.net.CircuitException;
import cj.studio.gateway.IMicNode;
import cj.studio.gateway.stub.IRest;
import cj.studio.imc.be.router.stub.IPeerEventStub;

public class EmptyPeerOnlineActivator implements IEntryPointActivator {

	@Override
	public void activate(IServiceSite site, IElement args) {
		emptyPeerOnline(site);//此处耗时跟启动的连向router的连接数有关
	}

	private void emptyPeerOnline(IServiceSite site) {
		long v = System.currentTimeMillis();
		CJSystem.logging().info(getClass(), "开始清除在线列表...");
		IRest rest = (IRest) site.getService("$.rest");
		try {
			IPeerEventStub peer = rest.forRemote("/backend/router/").open(IPeerEventStub.class, true);
			IMicNode node = (IMicNode) site.getService(IMicNode.SERVICE_KEY);
			peer.emtpyPeerOnline(node.guid());
			CJSystem.logging().info(getClass(), "清除在线列表完成，耗时：" + (System.currentTimeMillis() - v));
		} catch (CircuitException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void inactivate(IServiceSite site) {
		emptyPeerOnline(site);
	}

}
