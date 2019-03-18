package cj.studio.imc.fe.channel.program.valve;

import cj.studio.ecm.Scope;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;
import cj.studio.gateway.socket.pipeline.IAnnotationInputValve;
import cj.studio.gateway.socket.pipeline.IIPipeline;
import cj.studio.gateway.socket.util.SocketContants;
import cj.studio.gateway.stub.IRest;
import cj.studio.imc.be.router.args.DeviceInfo;
import cj.studio.imc.be.router.args.UserInfo;
import cj.studio.imc.be.router.stub.IPeerEventStub;
import cj.ultimate.gson2.com.google.gson.Gson;

@CjService(name = "peerEventInputValve", scope = Scope.multiton)
public class PeerEventInputValve implements IAnnotationInputValve {
	@CjServiceRef(refByName = "$.rest")
	IRest rest;

	@Override
	public void onActive(String inputName, IIPipeline pipeline) throws CircuitException {
		IPeerEventStub peer = rest.forRemote("/backend/router/").open(IPeerEventStub.class, true);
		peer.onTerminus(inputName);
		pipeline.nextOnActive(inputName, this);
	}

	@Override
	public void flow(Object request, Object response, IIPipeline pipeline) throws CircuitException {
		Frame f = (Frame) request;
		if ("PEER/1.0".equals(f.protocol())) {
			IPeerEventStub peer = rest.forRemote("/backend/router/").open(IPeerEventStub.class, true);
			switch(f.command()) {
			case "onDevice":
				if (f.content().revcievedBytes() <= 0) {
					throw new CircuitException("404", "未提交设备信息");
				}
				DeviceInfo device = new Gson().fromJson(new String(f.content().readFully()), DeviceInfo.class);
				peer.onDevice(f.head(SocketContants.__frame_fromPipelineName), device);
				break;
			case "onUser":
				String principals=f.head("UC-Principals");
				String subject=f.head("UC-Subject");
				UserInfo user=new UserInfo(principals,subject);
				peer.onUser(f.head(SocketContants.__frame_fromPipelineName), user);
				break;
			}
			return;
		}
		pipeline.nextFlow(request, response, this);
	}

	@Override
	public void onInactive(String inputName, IIPipeline pipeline) throws CircuitException {
		IPeerEventStub peer = rest.forRemote("/backend/router/").open(IPeerEventStub.class, true);
		peer.offTerminus(inputName);
		pipeline.nextOnInactive(inputName, this);
	}

	@Override
	public int getSort() {
		return 2;
	}

}
