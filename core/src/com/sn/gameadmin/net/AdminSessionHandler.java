package com.sn.gameadmin.net;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.sn.gameadmin.MsgCenter;

public class AdminSessionHandler extends IoHandlerAdapter {

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println(session.getRemoteAddress().toString()+" closed");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		System.err.println(session.getRemoteAddress().toString()+" exception: " + cause.getMessage());
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		MsgCenter.getInstance().addMsg((Message) message);
//		System.out.println(message.toString());
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
//		System.out.println(message.toString());
	}

}
