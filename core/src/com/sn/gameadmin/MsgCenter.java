package com.sn.gameadmin;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.sn.gameadmin.net.Message;

public class MsgCenter {

	private static MsgCenter instance = new MsgCenter();
	public static MsgCenter getInstance() {
		return instance;
	}
	
	private Queue<Message> recvMsgs = new ConcurrentLinkedQueue<Message>();
	
	private MsgCenter() {
		
	}
	
	public void addMsg(Message msg) {
		recvMsgs.add(msg);
	}
	
	public Message popMsg() {
		if (recvMsgs.isEmpty()) {
			return null;
		}
		return recvMsgs.poll();
	}
	
	public void clearMsgs() {
		recvMsgs.clear();
	}
}
