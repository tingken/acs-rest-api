/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.remote.player;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The purpose of this class is ...
 * TODO javadoc for class TextPlayInfo
 */
@JsonInclude(value = Include.NON_NULL)
public class TextPlayInfo {

    @JsonProperty("Content")
    private String content;
    @JsonProperty("TargetIds")
    private List<Integer> targetIds = new ArrayList<Integer>();
    /**
     * 0: groups; 1: end-device.
     */
    @JsonProperty("TargetType")
    private int targetType = 1;
    @JsonProperty("Time")
    private String time = "";
    @JsonProperty("Playtime")
    private int playtime = 1;
    @JsonProperty("PlayInterval")
    private Integer playInterval;
    @JsonProperty("PlayPrior")
    private int playPrior;
    @JsonProperty("Volume")
    private Integer volume;

    // Getter Methods 

    public String getContent() {
        return content;
    }

    public List<Integer> getTargetIds() {
        return targetIds;
    }

    public int getTargetType() {
        return targetType;
    }

    public String getTime() {
        return time;
    }

    public int getPlaytime() {
        return playtime;
    }

    public Integer getPlayInterval() {
        return playInterval;
    }

    public int getPlayPrior() {
        return playPrior;
    }

    public Integer getVolume() {
        return volume;
    }

    // Setter Methods 

    public void setContent(String content) {
        this.content = content;
    }

    public void setTargetIds(List<Integer> targetIds) {
        this.targetIds = targetIds;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPlaytime(int playtime) {
        this.playtime = playtime;
    }

    /**
     * @param playInterval of which the unit is second.
     */
    public void setPlayInterval(int playInterval) {
        this.playInterval = playInterval;
    }

    public void setPlayPrior(int playPrior) {
        this.playPrior = playPrior;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

}
