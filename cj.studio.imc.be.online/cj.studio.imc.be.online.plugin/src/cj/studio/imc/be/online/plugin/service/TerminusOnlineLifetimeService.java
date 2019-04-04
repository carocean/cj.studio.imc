package cj.studio.imc.be.online.plugin.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.IDocument;
import cj.lns.chip.sos.cube.framework.IQuery;
import cj.lns.chip.sos.cube.framework.TupleDocument;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.imc.be.online.args.DeviceInfo;
import cj.studio.imc.be.online.args.TerminusOnlineLifetime;
import cj.studio.imc.be.online.args.UserInfo;
import cj.studio.imc.be.online.service.ITerminusOnlineLifetimeService;
import cj.ultimate.gson2.com.google.gson.Gson;

@CjService(name = "online")
public class TerminusOnlineLifetimeService implements ITerminusOnlineLifetimeService {
	@CjServiceRef(refByName = "mongodb.netos.imc")
	ICube online;

	@Override
	public void onTerminus(String path,String peerOnMicNode,String routerOnMicNode) {
		TerminusOnlineLifetime t=getByPath(path);
		if(t!=null) {
			offTerminus(path);
		}
		TerminusOnlineLifetime tolt = new TerminusOnlineLifetime();
		tolt.setPath(path);
		tolt.setPeerOnMicNode(peerOnMicNode);
		tolt.setRouterOnMicNode(routerOnMicNode);
		tolt.setCtime(System.currentTimeMillis());
		online.saveDoc("online.lifetime", new TupleDocument<>(tolt));
	}
	@Override
	public void emtpyPeerOnline(String peerOnMicNode) {
		online.deleteDocs("online.lifetime", String.format("{'tuple.peerOnMicNode':'%s'}", peerOnMicNode));
	}
	@Override
	public void emtpyRouterOnline(String routerOnMicNode) {
		online.deleteDocs("online.lifetime", String.format("{'tuple.routerOnMicNode':'%s'}", routerOnMicNode));
	}
	@Override
	public void onDevice(String path, DeviceInfo device) {
		Bson where = Document.parse(String.format("{'tuple.path':'%s'}", path));
		String json = new Gson().toJson(device).replace("\"", "'");
		Bson update = Document.parse(String.format("{$set:{'tuple.device':%s}}", json));
		online.updateDocOne("online.lifetime", where, update);
	}

	@Override
	public void onUser(String path, UserInfo user) {
		Bson where = Document.parse(String.format("{'tuple.path':'%s'}", path));
		String json = new Gson().toJson(user).replace("\"", "'");
		Bson update = Document.parse(String.format("{$set:{'tuple.user':%s}}", json));
		online.updateDocOne("online.lifetime", where, update);
	}

	@Override
	public void offTerminus(String path) {
		online.deleteDocOne("online.lifetime", String.format("{'tuple.path':'%s'}", path));
	}

	@Override
	public void offUser(UserInfo user) {
		List<TerminusOnlineLifetime> list = getByUser(user);
		for (TerminusOnlineLifetime t : list) {
			Bson where = Document.parse(String.format("{'tuple.path':'%s'}", t.getPath()));
			Bson update = Document.parse(String.format("{$unset:{'tuple.user':''}}"));
			online.updateDocOne("online.lifetime", where, update);
		}
	}

	@Override
	public void offDevice(String uuid) {
		TerminusOnlineLifetime t = getByDevice(uuid);
		Bson where = Document.parse(String.format("{'tuple.path':'%s'}", t.getPath()));
		Bson update = Document.parse(String.format("{$unset:{'tuple.device':''}}"));
		online.updateDocOne("online.lifetime", where, update);
	}

	@Override
	public List<TerminusOnlineLifetime> page(int currPage, int pageSize) {
		String cjql = String.format("select {'tuple':'*'}.skip(%s).limit(%s) from tuple online.lifetime %s where {}",
				 currPage, pageSize,TerminusOnlineLifetime.class.getName());
		IQuery<TerminusOnlineLifetime> q = online.createQuery(cjql);
		List<IDocument<TerminusOnlineLifetime>> list = q.getResultList();
		List<TerminusOnlineLifetime> newlist = new ArrayList<>();
		for (IDocument<TerminusOnlineLifetime> doc : list) {
			newlist.add(doc.tuple());
		}
		return newlist;
	}

	@Override
	public List<TerminusOnlineLifetime> getByUser(UserInfo user) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple online.lifetime %s where {'tuple.user.appCode':'%s','tuple.user.userCode':'%s'}",
				TerminusOnlineLifetime.class.getName(), user.getAppCode(), user.getUserCode());
		IQuery<TerminusOnlineLifetime> q = online.createQuery(cjql);
		List<IDocument<TerminusOnlineLifetime>> list = q.getResultList();
		List<TerminusOnlineLifetime> newlist = new ArrayList<>();
		for (IDocument<TerminusOnlineLifetime> doc : list) {
			newlist.add(doc.tuple());
		}
		return newlist;
	}

	@Override
	public TerminusOnlineLifetime getByDevice(String uuid) {
		String cjql = String.format("select {'tuple':'*'} from tuple online.lifetime %s where {'tuple.device.uuid':'%s'}",
				TerminusOnlineLifetime.class.getName(), uuid);
		IQuery<TerminusOnlineLifetime> q = online.createQuery(cjql);
		IDocument<TerminusOnlineLifetime> doc = q.getSingleResult();
		if (doc == null)
			return null;
		return doc.tuple();
	}

	@Override
	public TerminusOnlineLifetime getByPath(String path) {
		String cjql = String.format("select {'tuple':'*'} from tuple online.lifetime %s where {'tuple.path':'%s'}",
				TerminusOnlineLifetime.class.getName(), path);
		IQuery<TerminusOnlineLifetime> q = online.createQuery(cjql);
		IDocument<TerminusOnlineLifetime> doc = q.getSingleResult();
		if (doc == null)
			return null;
		return doc.tuple();
	}

	@Override
	public long onlineUserCount() {
		String cjql = String.format("select {'tuple':'*'}.count() from tuple online.lifetime %s where {'tuple.user':{$exists:true}}",
				TerminusOnlineLifetime.class.getName());
		return online.count(cjql).count();
	}

	@Override
	public long onlineTerminusCount() {
		return online.tupleCount("online.lifetime");
	}

}
