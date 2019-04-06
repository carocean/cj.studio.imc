package cj.studio.imc.be.pusher.args;

//以下nexus信息用于收件人反向回推，如果没有以下项，如何知道回推到哪里？
public interface IImcAddresserInfo {
	String addresser();

	void addresser(String addresser);

	String tenant();

	void tenant(String tenant);

	String nexusEntry();

	void nexusEntry(String entry);
}
