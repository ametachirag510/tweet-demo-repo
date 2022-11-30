/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chirag.twitter;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ameta
 */
public class TwitterDemo {

    public static void showOption() {
        System.out.println("Select Any Option \n Option 1: Post Tweet \n Option 2 : Fetch Tweets By user  \n Option 3: Follow User  \n Option 4: Unfollow \n Enter Number Greatre than 4 for Exit");
    }

    public static void main(String[] args) {

        System.out.println("Welcom to Our Application!!!");
        System.out.println("User Id must be between 0 and 500");
        System.out.println("Tweet Id must be between 0 and 10000");
        showOption();
        Scanner sc = new Scanner(System.in);

        Twitter twitter = new Twitter();

        Integer option = sc.nextInt();
        while (option <= 4) {
            switch (option) {

                case 1:
                    System.out.println("Enter User Id (In Number)");
                    Integer userId = sc.nextInt();

                    System.out.println("Enter twitterId (In Number)");
                    Integer twitterId = sc.nextInt();

                    twitter.postTweet(userId, twitterId);

                    // break keyword terminates the
                    // code execution here itself
                    break;

                case 2:
                    System.out.println("Enter User Id (In Number)");
                    userId = sc.nextInt();
                    List<Integer> user1Tweets = twitter.getNewsFeed(userId);
                    System.out.println("user1Tweets::" + user1Tweets);

                    break;

                case 3:
                    System.out.println("Enter User Id Who will follow to Other User");
                    Integer user1 = sc.nextInt();
                    System.out.println("Enter User Id To whom Followed");
                    Integer user2 = sc.nextInt();
                    twitter.follow(user1, user2);

                    List<Integer> userTweetsAfterFollowuser = twitter.getNewsFeed(user1);
                    System.out.println("user1TweetsAfterFollowuser2::" + userTweetsAfterFollowuser);
//                  showInnerSwitchCase(twitter, sc);
                    break;

                case 4:
                    System.out.println("Enter User Id Who will unfollow to Other User");
                    Integer unFolUserId1 = sc.nextInt();
                    System.out.println("Enter User Id To whom unFollowed");
                    Integer unFolUserId2 = sc.nextInt();
                    twitter.unfollow(unFolUserId1, unFolUserId2);
                    List<Integer> user1TweetsAfterUnfollowUser2 = twitter.getNewsFeed(unFolUserId1);
                    System.out.println("user1TweetsAfterUnfollowUser2::" + user1TweetsAfterUnfollowUser2);
                    break;

                default:
                    System.out.println("Application Close...Thank you for choosing us..");
                    System.out.println("no match");
                    System.exit(0);
            }
            showOption();
            option = sc.nextInt();
        }
    }

}
