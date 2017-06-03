package com.splashbase.api;

import com.google.gson.annotations.SerializedName;
import com.yzeng.hackernews.util.ItemUtils;

import java.io.Serializable;
import java.util.List;

public class Picture implements Serializable {
    /**
     * by : yzeng
     * descendants : 276
     * id : 14182262
     * kids : [14183715,14183335,14182725,14183897,14185812,14184510,14182468,14183231,14183996,14185671,14186599,14182580,14182449,14185571,14182428,14182666,14182519,14185159,14185864,14182636,14188340,14188085,14187692,14183433,14188219,14184034,14186796,14184363,14183146,14183368,14186885,14188137,14185899,14183653,14183633,14182753,14182495,14185561,14182606,14188405,14183406,14184720,14183388,14183281,14186900,14183399,14185084,14186509,14186695,14183990,14183674,14188301,14183760,14184114,14183236,14183248,14183318,14183457,14184636,14185946,14184630,14184555,14184144,14184611,14184490,14183023,14183098,14183881,14182715,14184440,14182573,14183251,14184962,14187249,14182545]
     * score : 1189
     * time : 1493014335
     * title : Lyrebird An API to copy the voice of anyone
     * type : story
     * url : https://lyrebird.ai/demo
     */

    /**
     * Using public to avoid Parceler warning
     */


    @SerializedName("id")
    public int id;

    @SerializedName("url")
    public String url;

    public String getLarge_url() {
        return large_url;
    }

    public void setLarge_url(String large_url) {
        this.large_url = large_url;
    }

    public int getSource_id() {
        return source_id;
    }

    public void setSource_id(int source_id) {
        this.source_id = source_id;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @SerializedName("large_url")
    public String large_url;

    @SerializedName("source_id")
    public int source_id;

    @SerializedName("copyright")
    public String copyright;


    @SerializedName("site")
    public String site;


    public Picture(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        if(url == null || url.isEmpty()){
            return "No URL!";
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    static public String getDescription(Picture item){
        String description = item.getId() + " "+ item.getSite() +" "+ item.getCopyright();
        return description;
    }
}
