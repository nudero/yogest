package com.sn.gameadmin.net;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageDecoder extends CumulativeProtocolDecoder {
	
	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(MessageDecoder.class);
	
	@Override
	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		int stpos = in.position();
		if (in.remaining() < Message.HEADLEN) {
			in.position(stpos);
			return false;
		}
		
		int packlen = in.getInt();
		int cmd = in.getInt();
		int bodylen = in.getInt();
		
		// check packlen, bodylen, cmd is valid
		if (packlen < Message.HEADLEN || bodylen < 0 || packlen != Message.HEADLEN + bodylen) {
			// TODO: exception happens
			in.position(stpos);
			return false;
		}
		if (in.remaining() < bodylen) { // package is not ready
			in.position(stpos);
			return false;
		}
		
		byte[] bytes = new byte[bodylen];
		in.get(bytes);
		
		Message msg = new Message();
		msg.openid = 0;
		msg.packlen = packlen;
		msg.cmd = cmd;
		msg.bodylen = bodylen;
		msg.body = new String(bytes,Charset.forName("utf-8"));
		out.write(msg);
		
//		if (msg.body.length() != bodylen) {
//			// TODO: exception happens
//			log.error(msg.toString());
//		}
		
		return true;
	}

}
