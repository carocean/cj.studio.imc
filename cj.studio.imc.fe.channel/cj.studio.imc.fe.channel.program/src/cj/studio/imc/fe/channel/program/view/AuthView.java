package cj.studio.imc.fe.channel.program.view;

import java.util.Map;

import cj.studio.backend.uc.stub.IAuthenticationStub;
import cj.studio.backend.uc.stub.ITenantStub;
import cj.studio.backend.uc.stub.ITokenStub;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.net.Circuit;
import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;
import cj.studio.gateway.socket.app.IGatewayAppSiteResource;
import cj.studio.gateway.socket.app.IGatewayAppSiteWayWebView;
import cj.studio.gateway.stub.annotation.CjStubRef;
import cj.ultimate.util.StringUtil;
@CjBridge(aspects = "@rest")
@CjService(name="/public/auth.service")
public class AuthView implements IGatewayAppSiteWayWebView {
	@CjStubRef(remote="rest://backend/uc/",stub=IAuthenticationStub.class)
	IAuthenticationStub auth;
	@CjStubRef(remote="rest://backend/uc/",stub=ITokenStub.class)
	ITokenStub token;
	@CjStubRef(remote="rest://backend/uc/",stub=ITenantStub.class)
	ITenantStub tenantStub;
	@Override
	public void flow(Frame f, Circuit c, IGatewayAppSiteResource ctx) throws CircuitException {
		String user=f.parameter("user");
		if(StringUtil.isEmpty(user)) {
			throw new CircuitException("404", "缺少参数：user");
		}
		String pwd=f.parameter("pwd");
		if(StringUtil.isEmpty(pwd)) {
			throw new CircuitException("404", "缺少参数：pwd");
		}
		String tenant=f.parameter("tenant");
		if(StringUtil.isEmpty(tenant)) {
			throw new CircuitException("404", "缺少参数：tenant");
		}
		Map<String, Object> entry=token.parse(tenant);
		String tenantCode=(String)entry.get("sub");
		if(tenantStub.getTenant(tenantCode)==null) {
			throw new CircuitException("404", "租户不存在："+tenantCode);
		}
		String token=auth.authenticate("auth.password",tenantCode, user, pwd, Integer.MAX_VALUE);
		c.content().writeBytes(token.getBytes());
	}

}
