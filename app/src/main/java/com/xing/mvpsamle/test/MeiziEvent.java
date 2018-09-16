package com.xing.mvpsamle.test;

import java.util.List;

/**
 * Created by Administrator on 2018/9/15.
 */

public class MeiziEvent {
    private List<MeiziBean> meiziBeanList;

    public MeiziEvent() {
    }

    public MeiziEvent(List<MeiziBean> meiziBeanList) {
        this.meiziBeanList = meiziBeanList;
    }

    public List<MeiziBean> getMeiziBeanList() {
        return meiziBeanList;
    }

    public void setMeiziBeanList(List<MeiziBean> meiziBeanList) {
        this.meiziBeanList = meiziBeanList;
    }
}
