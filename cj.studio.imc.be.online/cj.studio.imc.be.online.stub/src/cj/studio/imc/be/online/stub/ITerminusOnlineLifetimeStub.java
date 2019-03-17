package cj.studio.imc.be.online.stub;

import java.util.ArrayList;
import java.util.List;

import cj.studio.gateway.stub.annotation.CjStubInContentKey;
import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubReturn;
import cj.studio.gateway.stub.annotation.CjStubService;
import cj.studio.imc.be.online.args.DeviceInfo;
import cj.studio.imc.be.online.args.TerminusOnlineLifetime;
import cj.studio.imc.be.online.args.UserInfo;

/**
 * 在线生命期
 * 
 * @author caroceanjofers
 *
 */
//一个终端对应一个在线生命期，一个生命期上包括在终端上的设备信息、在其上的用户信息等
@CjStubService(bindService = "/terminus/online.service", usage = "终端在线生命期管理存根")
public interface ITerminusOnlineLifetimeStub {
	@CjStubMethod(usage = "终端上线")
	void onTerminus(@CjStubInParameter(key = "path", usage = "终端所在路径：on://terminuschannel/routerchannel") String path);

	@CjStubMethod(command = "post", usage = "终端上线")
	void onDevice(@CjStubInParameter(key = "path", usage = "终端所在路径：on://terminuschannel/routerchannel") String path,
			@CjStubInContentKey(key = "device", usage = "设备") DeviceInfo device);

	@CjStubMethod(command = "post", usage = "终端上线")
	void onUser(@CjStubInParameter(key = "path", usage = "终端所在路径：on://terminuschannel/routerchannel") String path,
			@CjStubInContentKey(key = "user", usage = "用户") UserInfo user);

	@CjStubMethod(usage = "下线终端")
	void offTerminus(@CjStubInParameter(key = "path", usage = "终端所在路径：on://terminuschannel/routerchannel") String path);

	@CjStubMethod(command = "post", usage = "下线用户.用户从终端生命期上下线,仅仅是清楚其生命期上的用户字段，将不能向该用户推送消息,但终端并不下线")
	void offUser(@CjStubInContentKey(key = "user", usage = "用户") UserInfo user);//

	@CjStubMethod(usage = "下线设备")
	void offDevice(@CjStubInParameter(key = "uuid", usage = "设备号") String uuid);// 将致使终端下线,也就是终端生命期上的设备信息永不为空

	@CjStubMethod(usage = "获取一页")
	@CjStubReturn(elementType = TerminusOnlineLifetime.class, type = ArrayList.class, usage = "列表")
	List<TerminusOnlineLifetime> page(@CjStubInParameter(key = "currPage", usage = "当前页号") int currPage,
			@CjStubInParameter(key = "pageSize", usage = "页大小") int pageSize);

	@CjStubMethod(command = "post", usage = "获取用户的在线终端")
	@CjStubReturn(elementType = TerminusOnlineLifetime.class, type = ArrayList.class, usage = "列表")
	List<TerminusOnlineLifetime> getByUser(@CjStubInContentKey(key = "user", usage = "用户") UserInfo user);

	@CjStubMethod(usage = "设备所在的终端")
	TerminusOnlineLifetime getByDevice(@CjStubInParameter(key = "uuid", usage = "设备号") String uuid);

	@CjStubMethod(usage = "按终端路径获取终端生命期")
	TerminusOnlineLifetime getByPath(
			@CjStubInParameter(key = "path", usage = "终端所在路径：on://terminuschannel/routerchannel") String path);

	@CjStubMethod(usage = "在线用户数。已登录用户的终端")
	long onlineUserCount();

	@CjStubMethod(usage = "在线终端数")
	long onlineTerminusCount();
}
