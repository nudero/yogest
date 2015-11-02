package com.sn.gameadmin.net;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class MessageCodecFactory implements ProtocolCodecFactory {
	
	private ProtocolEncoder encoder = null;
	private ProtocolDecoder decoder = null;
	
	public MessageCodecFactory() {
		encoder = new MessageEncoder();
		decoder = new MessageDecoder();
	}
	
	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

}
