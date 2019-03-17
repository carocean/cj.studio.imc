package cj.studio.imc.be.router.stub;

import cj.studio.gateway.stub.annotation.CjStubInContentKey;
import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubService;
import cj.studio.imc.be.router.args.DeviceInfo;
import cj.studio.imc.be.router.args.UserInfo;

@CjStubService(bindService = "/event/peer.service", usage = "开放给peer（imchannel的存根)")
public interface IPeerEventStub {
	@CjStubMethod(usage = "终端上线")
	void onTerminus(@CjStubInParameter(key = "peerChannel", usage = "peer信道号") String peerChannel);

	@CjStubMethod(command = "post", usage = "设备上线")
	void onDevice(@CjStubInParameter(key = "peerChannel", usage = "peer信道号") String peerChannel,
			@CjStubInContentKey(key = "device", usage = "设备") DeviceInfo device);
	@CjStubMethod(command = "post", usage = "用户上线")
	void onUser(@CjStubInParameter(key = "peerChannel", usage = "peer信道号") String peerChannel,
			@CjStubInContentKey(key = "user", usage = "用户") UserInfo user);
	@CjStubMethod(command = "get", usage = "终端下线")
	void offTerminus(@CjStubInParameter(key = "peerChannel", usage = "peer信道号") String peerChannel);
	@CjStubMethod(command = "get", usage = "设备下线")
	void offDevice(@CjStubInParameter(key = "uuid", usage = "设备号") String uuid);
	@CjStubMethod(command = "post", usage = "用户下线")
	void offUser(@CjStubInContentKey(key = "user", usage = "用户") UserInfo user);
}
