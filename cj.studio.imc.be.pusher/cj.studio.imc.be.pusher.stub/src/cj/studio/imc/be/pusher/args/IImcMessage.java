package cj.studio.imc.be.pusher.args;

import org.jsoup.nodes.Document;

public interface IImcMessage {
	IImcAddresserInfo addresser();

	IImcRecipientsInfo recipients();

	long ctime();

	void ctime(long ctime);
	//内容板。内容板是有格式的文本，因此它由html dom树表示比较便利，如：表情、图片、文件、等元素，客户端将该dom解释为界面显示，因此该dom是由imc提供的有限模板格式组成，不是任意的html格式
	Document content();
	
	void content(Document content);
}
