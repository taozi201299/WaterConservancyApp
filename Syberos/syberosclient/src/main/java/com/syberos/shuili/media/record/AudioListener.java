package com.syberos.shuili.media.record;

public interface AudioListener {
    void progress(int time);

    void stop(String filePath, int recordDuration);

    void fail();
}
