package com.sn.gameadmin.net;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class MessageEncoder implements ProtocolEncoder {

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		Message msg = (Message)message;
		IoBuffer ib = IoBuffer.allocate(Message.HEADLEN).setAutoExpand(true);
		ib.putInt(msg.packlen);
		ib.putInt(msg.cmd);
		ib.putInt(msg.bodylen);
		ib.put(msg.body.getBytes(Charset.forName("utf-8")));
		ib.flip();
		out.write(ib);
		out.flush();
	}

	@Override
	public void dispose(IoSession session) throws Exception {
		
	}

}
