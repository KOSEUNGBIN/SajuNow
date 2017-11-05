package com.landvibe.common.send;

import java.net.URLEncoder;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;
import com.landvibe.core.history.History;
import com.landvibe.core.history.HistoryBo;

@Component
public class SendGcm {

	@Autowired
	private HistoryBo historyBo;

	public boolean send(long history_no, String message, long sender_no, boolean end_yn, String sender_name, int select_history , boolean isSubscribe) {

		String regId = historyBo.getRegId(sender_no, history_no);
		if (!regId.equals("LOGOUT") || regId.equals("BLOCK") ) {
			try {
				History history = historyBo.getHistory(history_no);

				Sender sender = new Sender("{GOOGLE GCM API KEY}"); // GOOGLE_API_KEY

				// GCM 으로 역술인에게 인삿말 push
				Random random = null;
				int TTLTime = 60;
				int RETRY = 3;

				if (random == null) {
					random = new Random(System.currentTimeMillis());
				}

				String messageCollapseKey = String.valueOf(Math.abs(random.nextInt()));

				Message.Builder gcmMessageBuilder = new Message.Builder();
				gcmMessageBuilder.collapseKey(messageCollapseKey).delayWhileIdle(true).timeToLive(TTLTime);
				gcmMessageBuilder.addData("msg", URLEncoder.encode(message, "UTF-8"));
				gcmMessageBuilder.addData("history_no", history_no + ""); // 개설된
																			// 채팅방의
																			// history_no
				gcmMessageBuilder.addData("end_yn", ""+end_yn);
				gcmMessageBuilder.addData("sender_name", sender_name);
				gcmMessageBuilder.addData("select_history", ""+select_history);
				gcmMessageBuilder.addData("isSubscribe", ""+isSubscribe);

				Message gcmMessage = gcmMessageBuilder.build();
				sender.send(gcmMessage, regId, RETRY);
				return true;
			} catch (Exception e) {
				System.out.println(e);
				return false;
			}
		} else
			return false;
	}

}
