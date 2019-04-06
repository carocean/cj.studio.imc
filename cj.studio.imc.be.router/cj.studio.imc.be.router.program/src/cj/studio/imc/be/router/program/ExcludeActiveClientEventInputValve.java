package cj.studio.imc.be.router.program;

import cj.studio.ecm.Scope;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.net.CircuitException;
import cj.studio.gateway.socket.pipeline.IAnnotationInputValve;
import cj.studio.gateway.socket.pipeline.IIPipeline;
import cj.studio.gateway.socket.util.SocketContants;

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
