package com.sn.gameadmin.net;

import net.sf.json.JSONObject;

public class Message {
	public static final int HEADLEN = 12;
	
	public int openid;
	public int packlen;
	public int cmd;
	public int bodylen;
	public String body;
	
	public String toDetailString() {
		return "openid="+openid+", packlen="+packlen+", cmd="+cmd+", bodylen="+bodylen+", body="+body;
	}
	
	public String toString() {
		return "id="+openid+" cmd="+cmd+" body="+body;
	}
	
	public JSONObject getJsonBody() {
		return JSONObject.fromObject(body);
	}
}
