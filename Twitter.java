/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chirag.twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * *
 *
 * 1. userTweets - Hashmap used to put user with tweets, userId is unique, So
 * only One user id with Unique is possible and Set of tweets is used for unquie
 * tweets
 *
 * 2 usersFollowees - usersFollowees HashMap used to main user with its Unqiue
 * Followees.
 *
 * 3 allTweets - allTweets Set is used track tweet will not be Duplicate for all
 * employee. if user1 post tweets having tweets id 1 will not allocate two other
 * user's tweet.
 *
 * 4.postTweet() - will post Tweet for Given User Id
 *
 * 5 getNewsFeed() - get recent 10 tweets (tweets include own tweets and
 * followees tweets in case if user followed to others)
 *
 * 6. follow() - if user follow to other user, other users to whom current user
 * will follow,their tweets will be in its recent tweets
 *
 * 7. unfollow() - if user unfollow to other like user2, then user2's tweet will
 * not be in current user's recent tweets
 */
public class Twitter {

    HashMap<Integer, Set<Tweets>> userTweets = new HashMap<>();
    HashMap<Integer, Set<Integer>> usersFollowees = new HashMap<>();
    HashSet<Tweets> allTweets = new HashSet<>();

    //Method Will post A tweet
    void postTweet(int userId, int tweetId) {
        if (userId >= 0 && userId <= 500 && tweetId >= Tweets.MinTweetId && tweetId <= Tweets.MaxTweetId) {

            if (!allTweets.contains(new Tweets(tweetId))) {
                Tweets tweet = new Tweets(tweetId, "", Tweets.tweetCount);
                Set<Tweets> userTweetSet = userTweets.get(userId);
                // if user already posted some tweets Earlier
                if (userTweetSet != null && !userTweetSet.isEmpty()) {
                    userTweets.get(userId).add(tweet);
                } else {
                    // if user is Posting tweet  First Time
                    Set<Tweets> tweetSet = new HashSet<>();
                    tweetSet.add(tweet);
                    userTweets.put(userId, tweetSet);
                }

                System.out.println("Hello User " + userId + " ,Your Tweet has been Posted SuccessFully.");
                Tweets.tweetCount = Tweets.tweetCount + 1;
                allTweets.add(tweet);
            } else {
                System.out.println("Tweet with Tweet Id already in the System");
            }

        } else {
            System.out.println("User with This userID and tweetId with tweet id not Allowed");

        }

    }

    List<Integer> getNewsFeed(int userId) {
        if (userId >= 0 && userId <= 500) {

            TreeSet<Tweets> totalTweets = new TreeSet<>();
            if (userTweets.containsKey(userId)) {
                Set<Tweets> usrTweet = userTweets.get(userId);
                System.out.println("usrTweet:: " + usrTweet);
                totalTweets.addAll(usrTweet);
            }
            Set<Integer> followeeIds = usersFollowees.get(userId);

            if (followeeIds != null && followeeIds.size() > 0) {
                followeeIds.forEach(followeeId -> {
                    if (userTweets.containsKey(followeeId)) {
                        Set<Tweets> followeeTweet = userTweets.get(followeeId);
                        System.out.println("followeeTweet:: " + followeeTweet);
                        totalTweets.addAll(followeeTweet);
                    }

                });
            }

            System.out.println("totalTweets:: " + totalTweets);
            List<Integer> list = new ArrayList<>();
            for (Tweets t : totalTweets.descendingSet()) {
                list.add(t.getTweetId());
            }

            System.out.println("User " + userId + " Latest Tweets are:  " + list.stream().limit(10).collect(Collectors.toList()));
            return list.stream().limit(10).collect(Collectors.toList());
        } else {
            System.out.println("User with This userID not Allowed");
            return new ArrayList<>();
        }
    }

    void follow(int userId, int followeeId) {
        if ((userId >= 0 && userId <= 500) && (followeeId > 0 && followeeId <= 500)) {
            Set<Integer> followeeIdSet = new HashSet<>();
            followeeIdSet.add(followeeId);
            usersFollowees.put(userId, followeeIdSet);
            System.out.println("usersFollowees:: " + usersFollowees.toString());
            System.out.println("User " + userId + " followed " + followeeId);
        } else {
            System.out.println("userId and followeeId should be less than or equal to 500 And greater than equal to 0");
        }
    }

    void unfollow(int userId, int followeeId) {
        if ((userId >= 0 && userId <= 500) && (followeeId > 0 && followeeId <= 500) && usersFollowees != null && usersFollowees.size() > 0) {
            if (usersFollowees.containsKey(userId)) {
                Set<Integer> followeesIds = usersFollowees.get(userId);
                if (followeesIds.contains(followeeId)) {
                    followeesIds.remove(followeeId);

                    System.out.println("User " + userId + " unfollowed " + followeeId);
                }
            }

        } else {
            System.out.println("userId and followeeId should be less than or equal to 500 And greater than equal to 0");
        }

    }

}
