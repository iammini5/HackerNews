package com.yzeng.hackernews.util;

import com.hackernews.api.Item;


public class ItemUtils {

    static public String[] getListIndex(String str){
        str = str.replace("[", "").replace("]", "");
        return str.split(",");
    }

    static public String getByTime(Item item){
        long currentTime = System.currentTimeMillis();
        long d = currentTime - item.getTime() * 1000;
        long seconds = d/1000;
        int days = (int)(seconds/(24*3600));
        String str = "";
        if(days > 0){
            str = days +" "+ "days"+" "+"ago";
        }else{
            int hours = (int)(seconds/3600);
            if(hours > 0){
                str = hours +" "+"hour"+" "+"ago";
            }else{
                int minutes = (int)(seconds/60);
                if(minutes > 0){
                    str = minutes + " "+"minute"+" "+"ago";
                }else{
                    str = seconds + " "+"second" +" "+"ago";
                }
            }
        }
        return str;
    }
}
