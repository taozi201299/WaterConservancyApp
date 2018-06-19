package com.syberos.shuili.entity;

import java.io.Serializable;

/**
 * Created by jidan on 18-3-19.
 */

public abstract class AbstractResponse implements Serializable {
    public int code = -1;

    public boolean isSuccess() {
        return code == 200;
    }

}

