package cj.studio.imc.be.router.program;

import cj.studio.ecm.IEntryPointActivator;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.context.IElement;
import cj.studio.gateway.IMicNode;
import cj.studio.imc.be.router.service.IPeerEventService;

public class EmptyPeerOnlineActivator implements IEntryPointActivator{

	@Override
	public void activate(IServiceSite site, IElement args) {
		emptyPeerOnline(site);
	}

	private void emptyPeerOnline(IServiceSite site) {
		IMicNode node=(IMicNode)site.getService(IMicNode.SERVICE_KEY);
		IPeerEventService peer=(IPeerEventService)site.getService("router@plugin.peerEvent");
		peer.emtpyRouterOnline(node.guid());
	}

	@Override
	public void inactivate(IServiceSite site) {
		emptyPeerOnline(site);
	}

}
