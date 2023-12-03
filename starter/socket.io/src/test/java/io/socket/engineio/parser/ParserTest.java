package io.socket.engineio.parser;

import io.socket.engineio.handler.HandshakeResponse;
import io.socket.engineio.protocol.EngineIO.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class ParserTest {
    @Test
    public void V4Handshake() {
        Parser v4 = Parser.PROTOCOL_V4;
        Packet<Object> open = new Packet<>(PacketType.OPEN);
        HandshakeResponse handshakeResponse = new HandshakeResponse<>("1");
        open.setData(handshakeResponse);
        v4.encodePacket(open, false, new Parser.EncodeCallback<Object>() {
            @Override
            public void call(Object data) {
                log.debug("{}", data);
            }
        });
    }
    @Test
    public void V4Open() {
        Parser v4 = Parser.PROTOCOL_V4;
//        Packet<Object> open = new Packet<>(PacketType.OPEN);
//        HandshakeResponse handshakeResponse = new HandshakeResponse<>("1");
//        open.setData(handshakeResponse);
        String data = "0{\"token\":\"123456\"}";
        Packet<?> packet = v4.decodePacket(data);
        log.debug("{}", packet);
//        v4.encodePacket(open, false, new Parser.EncodeCallback<Object>() {
//            @Override
//            public void call(Object data) {
//                log.debug("{}", data);
//            }
//        });
    }

}
