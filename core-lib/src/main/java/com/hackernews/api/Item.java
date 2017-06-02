package com.hackernews.api;

import com.google.gson.annotations.SerializedName;
import com.yzeng.hackernews.util.ItemUtils;

import java.io.Serializable;
import java.util.List;

public class Item implements Serializable{
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

    @SerializedName("deleted")
    public boolean deleted;

    @SerializedName("by")
    public String by;

    @SerializedName("descendants")
    public int descendants;

    @SerializedName("id")
    public int id;

    @SerializedName("score")
    public int score;

    @SerializedName("time")
    public long time;

    @SerializedName("title")
    public String title;

    @SerializedName("type")
    public String type;

    @SerializedName("url")
    public String url;

    @SerializedName("kids")
    public List<Integer> kids;

    @SerializedName("text")
    public String text;

    public int level;

    public int indexInLevel;

    public Item(){

    }

    public int getIndexInLevel() {
        return indexInLevel;
    }

    public void setIndexInLevel(int indexInLevel) {
        this.indexInLevel = indexInLevel;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public int getDescendants() {
        return descendants;
    }

    public void setDescendants(int descendants) {
        this.descendants = descendants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<Integer> getKids() {
        return kids;
    }

    public void setKids(List<Integer> kids) {
        this.kids = kids;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    static public String getDescription(Item item){
        String score = item.getScore() + " " + "pointes";
        String author = "by" +" "+item.getBy();
        String time = ItemUtils.getByTime(item);
        String comments = (item.getKids() != null ? item.getKids().size() : 0) +" "+"comments";
        return score + " " + author +" "+time +" | " + comments;
    }

    static public String getCommentTitle(Item item){
        String author = item.getBy();
        String time = ItemUtils.getByTime(item);
        return author +" " +time;
    }


//    public boolean equals(Object other) {
//        return other.equals(this, other);
//    }

    public int hashCode() {
        return 0;
    }
}
