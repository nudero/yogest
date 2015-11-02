package com.sn.gameadmin.net;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import net.sf.json.JSONObject;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.sn.gameadmin.Const;


public class AdminNet {
	
	private NioSocketConnector connector = null;
	private IoSession session = null;
	
	private static AdminNet instance = null;
	public static AdminNet getInstance() {
		if (instance == null) {
			instance = new AdminNet();
		}
		return instance;
	}
	
	private AdminNet() {
		connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(5000);
		connector.getFilterChain().addLast("MessageCodec", new ProtocolCodecFilter(new MessageCodecFactory()));
		connector.setHandler(new AdminSessionHandler());
	}
	
	public boolean connect() {
		disconnect();
		
		try {
			ConnectFuture future = connector.connect(new InetSocketAddress(Const.ADMIN_IP, Const.ADMIN_PORT)); // 121.40.190.33
			future.awaitUninterruptibly();
			session = future.getSession();
		}
		catch (RuntimeIoException e) {
			System.err.println("connect to server failed");
			return false;
		}
		System.out.println("connect to server success");
		return true;
	}
	
	public void disconnect() {
		if (session != null) {
			session.close(true);
			session = null;
		}
	}
	
	public void send(int cmd, Object... args) {
		if (session == null) {
			return;
		}
		session.write(makeMsg(cmd, args));
	}
	
	public static JSONObject makeJson(Object... args) {
		JSONObject json = new JSONObject();
		for(int i = 0; i < args.length-1; i += 2) {
			json.put(args[i], args[i+1]);
		}
		return json;
	}
	
	public static Message makeMsg(int cmd, Object... args) {
		return makeMsg(cmd, makeJson(args));
	}
	
	public static Message makeMsg(int cmd, JSONObject body) {
		String bodystr = body.toString();
		int bodystrlen = bodystr.getBytes(Charset.forName("utf-8")).length;
		
		Message msg = new Message();
		msg.openid = 0;
		msg.cmd = cmd;
		msg.body = bodystr;
		msg.bodylen = bodystrlen;
		msg.packlen = Message.HEADLEN + msg.bodylen;
		return msg;
	}
}
