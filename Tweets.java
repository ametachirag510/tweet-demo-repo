/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chirag.twitter;

import java.nio.charset.Charset;
import java.util.Objects;
import java.util.Random;
/**
 
 Tweets class has 3 instance variable tweetId,userTweetCount,tweetContent 
  1. tweetId = for uniqueNess
  
  2. userTweetCount - if it is not necessary that tweets posted by all users are in sorted order like user 1 will tweet having id 1, 
                      so for every tweet  userTweetCount is given so it will be helpful in retrieve latest 10 tweets (include own tweets or in case follow,
                      others tweets).
                      we can also use date here.
  
  3.tweetContent =  Random generated String for generating tweet content.
  
  4. tweetCount = it is static variable,so will be inititalzied once, On every tweets posted by any user will 'tweetCount' be increment by 1 
                 and tweetCount value assigned to userTweetCount.
                 
  5.MinTweetId = Minimum Tweet id given to tweetId.   
  
  6.MaxTweetId = Maximum Tweet Id  id given to tweetId.                 
  
  7  getRandomStringForTweetContent() - this method is used to generate random tweet content.
  
  8. Tweet implements Comparable so it will used in Treeset used in fetch News Feeds.
  
 */
public class Tweets implements Comparable<Tweets> {

    Integer tweetId;
    Integer userTweetCount;
    String tweetContent;

    
    static Integer tweetCount = 0;
    static Integer MinTweetId = 0;
    static Integer MaxTweetId = 10000;

    public Tweets() {

    }

    public Tweets(Integer tweetId) {
        this.tweetId = tweetId;
    }

    public Tweets(Integer tweetId, String tweetContent, Integer tweetCount) {
        this.tweetContent = getRandomStringForTweetContent();
        this.tweetId = tweetId;
        this.userTweetCount = ++tweetCount;
    }

    public Integer getTweetId() {
        return tweetId;
    }

    public void setTweetId(Integer tweetId) {
        this.tweetId = tweetId;
    }

    public static Integer getTweetCount() {
        return tweetCount;
    }

    public static void setTweetCount(Integer tweetCount) {
        Tweets.tweetCount = tweetCount;
    }

    @Override
    public String toString() {
        return "Tweets{" + "tweetId=" + tweetId + ", userTweetCount=" + userTweetCount + ", tweetContent=" + tweetContent + '}';
    }

    @Override
    public int compareTo(Tweets o) {
        return this.userTweetCount.compareTo(o.userTweetCount);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.tweetId);
//        hash = 89 * hash + Objects.hashCode(this.userTweetCount);
//        hash = 89 * hash + Objects.hashCode(this.tweetContent);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        
        System.out.println("equals override method called");
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tweets other = (Tweets) obj;
    
        if (!Objects.equals(this.tweetId, other.tweetId)) {
            return false;
        }
     
        return true;
    }
    
    

    public String getRandomStringForTweetContent() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}
