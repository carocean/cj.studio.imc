package cj.studio.imc.be.router.program.veiw;

import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.net.Circuit;
import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;
import cj.studio.gateway.socket.app.IGatewayAppSiteResource;
import cj.studio.gateway.socket.app.IGatewayAppSiteWayWebView;
//接收推送中心推来的消息。在该类向channel推送时，如果通道404则清除该通道在线信息，并回发消息给推送器，由推送器重发。
@CjService(name = "/messageReciever.service")
public class MessageReciever implements IGatewayAppSiteWayWebView {

	@Override
	public void flow(Frame frame, Circuit circuit, IGatewayAppSiteResource resource) throws CircuitException {
		

	}

}
