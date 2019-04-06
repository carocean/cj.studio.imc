package cj.studio.imc.be.pusher.args;

/*
 * <pre>
 * 以下例子均是由第三方来规定，imc并不去理解此中含义，仅将RecipientsInfo发给第三方查询以得到用户集合
 * 例一： 租户为：cctv,连接项为：group,收件人为群号：28238383;3994884，收件人如果是多个则以；号隔开
 * 例二： 租户为：cctv,连接项为：person,收件人为群号：lig;tom，收件人如果是多个则以；号隔开
 * 例三：租户为：cctv,连接项为：group,收件人为群号：28238383[tom];3994884.28238383[tom]这表示与群28238383中的用户tom发私信
 * </pre>
 */
public interface IImcRecipientsInfo {
	String recipients();

	void recipients(String recipients);

	String tenant();

	void tenant(String tenant);

	String nexusEntry();

	void nexusEntry(String entry);
}
