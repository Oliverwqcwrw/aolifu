package com.aolifu.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;

/**
 * CRC32 *  * @author jianggujin *
 */
public class HQCRC32 {
    private static HQCRC32 crc32 = new HQCRC32();

    public static HQCRC32 getInstance() {
        return crc32;
    }

    private HQCRC32() {
    }

    private static final int STREAM_BUFFER_LENGTH = 1024;

    public long encrypt(byte[] data) {
        CRC32 crc32 = new CRC32();
        crc32.update(data);
        return crc32.getValue();
    }

    public long encrypt(InputStream data) throws IOException {
        final byte[] buffer = new byte[STREAM_BUFFER_LENGTH];
        int read = data.read(buffer, 0, STREAM_BUFFER_LENGTH);
        CRC32 crc32 = new CRC32();
        while (read > -1) {
            crc32.update(buffer, 0, read);
            read = data.read(buffer, 0, STREAM_BUFFER_LENGTH);
        }
        return crc32.getValue();
    }
}