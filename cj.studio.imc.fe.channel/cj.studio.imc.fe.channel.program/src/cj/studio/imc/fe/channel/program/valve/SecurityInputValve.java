package cj.studio.imc.fe.channel.program.valve;

import java.util.Map;

import cj.studio.backend.uc.stub.ITokenStub;
import cj.studio.ecm.Scope;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;
import cj.studio.gateway.socket.pipeline.IAnnotationInputValve;
import cj.studio.gateway.socket.pipeline.IIPipeline;
import cj.studio.gateway.stub.IRest;
import cj.ultimate.util.StringUtil;

@CjService(name = "securityInputValve", scope = Scope.multiton)
public class SecurityInputValve implements IAnnotationInputValve {
	@CjServiceRef(refByName = "$.rest")
	IRest rest;
	private ITokenStub token;

	@Override
	public void onActive(String inputName, IIPipeline pipeline) throws CircuitException {
		pipeline.nextOnActive(inputName, this);
		this.token = rest.forRemote("/backend/uc/").open(ITokenStub.class);
	}

	@Override
	public void flow(Object request, Object response, IIPipeline pipeline) throws CircuitException {
		if (request instanceof Frame) {
			Frame f = (Frame) request;
			String url = f.relativePath();
			if (url.startsWith("/public/")) {
				pipeline.nextFlow(request, response, this);
				return;
			}
			String cjtoken = f.parameter("cjtoken");
			if (StringUtil.isEmpty(cjtoken)) {
				throw new CircuitException("801", "拒绝服务。cjtoken为空！");
			}
			Map<String, Object> map = token.parse(cjtoken);
			f.head("UC-User", (String) map.get("user"));
			f.head("UC-Tenant", (String) map.get("sub"));
			pipeline.nextFlow(request, response, this);
			return;
		}
		throw new CircuitException("801", "拒绝服务。没有权限！");
	}

	@Override
	public void onInactive(String inputName, IIPipeline pipeline) throws CircuitException {
		pipeline.nextOnInactive(inputName, this);
	}

	@Override
	public int getSort() {
		return 2;
	}

}
