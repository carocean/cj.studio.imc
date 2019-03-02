package cj.studio.imc.fe.channel.program.view;

import java.util.HashMap;
import java.util.Map;

import cj.studio.backend.uc.stub.IAuthenticationStub;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.net.Circuit;
import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;
import cj.studio.gateway.socket.app.IGatewayAppSiteResource;
import cj.studio.gateway.socket.app.IGatewayAppSiteWayWebView;
import cj.studio.gateway.stub.annotation.CjStubRef;
@CjBridge(aspects = "@rest")
@CjService(name="/public/auth.service")
public class AuthView implements IGatewayAppSiteWayWebView {
	@CjStubRef(remote="rest://backend/uc/",stub=IAuthenticationStub.class)
	IAuthenticationStub auth;
	@Override
	public void flow(Frame f, Circuit c, IGatewayAppSiteResource ctx) throws CircuitException {
		String user=f.parameter("user");
		String pwd=f.parameter("pwd");
		Map<String, String> props=new HashMap<>();
		props.put("ttlMillis", Integer.MAX_VALUE+"");
		String token=auth.authenticate("auth.password", user, pwd, props);
		c.content().writeBytes(token.getBytes());
	}

}
