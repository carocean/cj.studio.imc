package cj.studio.imc.fe.channel.program.valve;

import cj.studio.ecm.Scope;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.gateway.socket.pipeline.IAnnotationInputValve;
import cj.studio.gateway.socket.pipeline.IIPipeline;
import cj.studio.gateway.stub.IRest;
import cj.studio.imc.be.router.stub.IPeerEventStub;

@CjService(name = "peerEventInputValve", scope = Scope.multiton)
public class PeerEventInputValve implements IAnnotationInputValve {
	@CjServiceRef(refByName="$.rest")
	IRest rest;
	@Override
	public void onActive(String inputName, IIPipeline pipeline) throws CircuitException {
		IPeerEventStub peer=rest.forRemote("/backend/router/").open(IPeerEventStub.class,true);
		peer.onTerminus(inputName);
		pipeline.nextOnActive(inputName, this);
	}

	@Override
	public void flow(Object request, Object response, IIPipeline pipeline) throws CircuitException {
		pipeline.nextFlow(request, response, this);
	}

	@Override
	public void onInactive(String inputName, IIPipeline pipeline) throws CircuitException {
		IPeerEventStub peer=rest.forRemote("/backend/router/").open(IPeerEventStub.class,true);
		peer.offTerminus(inputName);
		pipeline.nextOnInactive(inputName, this);
	}

	@Override
	public int getSort() {
		return 2;
	}

}
