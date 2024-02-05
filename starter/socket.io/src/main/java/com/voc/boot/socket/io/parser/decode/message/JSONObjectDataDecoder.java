package com.voc.boot.socket.io.parser.decode.message;

import com.voc.boot.socket.io.parser.decode.DecoderContext;
import com.voc.boot.socket.io.parser.Packet;
import com.voc.boot.socket.io.utils.PacketSeparator;
import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONTokener;

@Slf4j
public class JSONObjectDataDecoder extends MessageDecoder {
    @Override
    public void decode(DecoderContext<ByteBuf, Packet> context) {
        log.info("7. look up json object data");
        ByteBuf buf = context.getData();
        int dataJsonObjectEndIndex = buf.bytesBefore(PacketSeparator.DATA_JSON_OBJECT.getValue());
        byte[] bytes = new byte[dataJsonObjectEndIndex + 1];
        buf.readBytes(bytes);
        String data = new String(bytes, CharsetUtil.UTF_8);
        Object json = new JSONTokener(data).nextValue();
        context.geTagret().setData(json);
    }

    @Override
    public boolean test(DecoderContext<ByteBuf, Packet> context) {
        return super.test(context) && context.getData().bytesBefore(PacketSeparator.DATA_JSON_OBJECT.getValue()) > 0;
    }
}