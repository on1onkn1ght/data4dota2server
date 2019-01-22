package com.onionknight.data4dota2.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author :fdy
 * @Date :Created in 9:52 2019/1/19
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public class MatchJson {
    private Long id;
    private byte[] overview;
    private byte[] detail;
    private String replay_url;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getOverview() {
        return overview;
    }

    public void setOverview(byte[] overview) {
        this.overview = overview;
    }

    public byte[] getDetail() {
        return detail;
    }

    public void setDetail(byte[] detail) {
        this.detail = detail;
    }

    public String getReplay_url() {
        return replay_url;
    }

    public void setReplay_url(String replay_url) {
        this.replay_url = replay_url;
    }
}
