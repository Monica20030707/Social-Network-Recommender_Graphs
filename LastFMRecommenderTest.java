/***********************************************************
* Name: Monica Nguyen
* Date: 16 Jun 2023
* Course Code: CS401
* Description: This is used to testing the method for the
* LastFMRecommender, by input the userID number.
*
***********************************************************/
import java.util.*;

public class LastFMRecommenderTest {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        LastFMRecommender recommender = new LastFMRecommender();
        recommender.loadData("lastfm_dataset/artists.dat", "lastfm_dataset/user_artists.dat", "lastfm_dataset/user_friends.dat");

        int userId;
        do {
            System.out.print("Enter the user ID (or 1 to exit): ");
            userId = scanner.nextInt();
        
            if (userId != 1) {
                testListFriends(recommender, userId);
                System.out.print("Enter other the user ID: ");
                int otherUserId = scanner.nextInt();
                testListFriends(recommender, otherUserId);
                testCommonFriends(recommender, userId, otherUserId);
                testListArtists(recommender, userId, otherUserId);
                testListTop10(recommender);
                testRecommend10(recommender, userId);
                testRecommend10(recommender, otherUserId);
            }
        } while (userId != 1);
    }

    public static void testListFriends(LastFMRecommender recommender, int userId) {
        recommender.listFriends(userId);
        System.out.println();
    }

    public static void testCommonFriends(LastFMRecommender recommender, int userId1, int userId2) {
        recommender.commonFriends(userId1, userId2);
        System.out.println();
    }

    public static void testListArtists(LastFMRecommender recommender, int userId1, int userId2) {
        recommender.listArtists(userId1, userId2);
        System.out.println();
    }

    public static void testListTop10(LastFMRecommender recommender) {
        recommender.listTop10();
        System.out.println();
    }

    public static void testRecommend10(LastFMRecommender recommender, int userId) {
        recommender.recommend10(userId);
        System.out.println();
    }
}