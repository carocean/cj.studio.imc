package cj.studio.imc.fe.channel.program;

import cj.studio.ecm.IEntryPointActivator;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.context.IElement;
import cj.studio.ecm.net.CircuitException;
import cj.studio.gateway.IMicNode;
import cj.studio.gateway.stub.IRest;
import cj.studio.imc.be.router.stub.IPeerEventStub;

public class EmptyPeerOnlineActivator implements IEntryPointActivator{

	@Override
	public void activate(IServiceSite site, IElement args) {
		emptyPeerOnline(site);
	}

	private void emptyPeerOnline(IServiceSite site) {
		IRest rest=(IRest)site.getService("$.rest");
		try {
			IPeerEventStub peer = rest.forRemote("/backend/router/").open(IPeerEventStub.class, true);
			IMicNode node=(IMicNode)site.getService(IMicNode.SERVICE_KEY);
			peer.emtpyPeerOnline(node.guid());
		} catch (CircuitException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void inactivate(IServiceSite site) {
		emptyPeerOnline(site);
	}

}
