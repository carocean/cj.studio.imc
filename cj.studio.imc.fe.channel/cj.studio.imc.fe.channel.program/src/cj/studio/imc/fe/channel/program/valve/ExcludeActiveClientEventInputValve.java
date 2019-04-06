package cj.studio.imc.fe.channel.program.valve;

import cj.studio.ecm.Scope;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.net.CircuitException;
import cj.studio.gateway.socket.pipeline.IAnnotationInputValve;
import cj.studio.gateway.socket.pipeline.IIPipeline;
import cj.studio.gateway.socket.util.SocketContants;
//排除客户端（cluster目标）建立远程连接时其pipleline.active事件向该应用触发，这样应用仅接受由server端触发的激活和失活事件
@CjService(name = "exludeActiveEventInputValve", scope = Scope.multiton)
public class ExcludeActiveClientEventInputValve implements IAnnotationInputValve {

	@Override
	public void onActive(String inputName, IIPipeline pipeline) throws CircuitException {
		if (!"server".equals(pipeline.prop(SocketContants.__pipeline_fromNetType))) {
			return;
		}
		pipeline.nextOnActive(inputName, this);
	}

	@Override
	public void flow(Object request, Object response, IIPipeline pipeline) throws CircuitException {
		pipeline.nextFlow(request, response, this);
	}

	@Override
	public void onInactive(String inputName, IIPipeline pipeline) throws CircuitException {
		if (!"server".equals(pipeline.prop(SocketContants.__pipeline_fromNetType))) {
			return;
		}
		pipeline.nextOnInactive(inputName, this);
	}

	@Override
	public int getSort() {
		return 0;
	}

}
