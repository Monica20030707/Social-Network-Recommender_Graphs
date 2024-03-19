/***********************************************************
* Name: Monica Nguyen
* Date: 16 Jun 2023
* Description: This is a JUnit test class for the 
* LastFMRecommender class. It tests various functionalities 
* of the LastFMRecommender class.
*
***********************************************************/
import java.util.*;

/**
 * The LastFMRecommenderTest class provides test cases for the LastFMRecommender class.
 */
public class LastFMRecommenderTest {
    public static Scanner scanner = new Scanner(System.in);

    /**
     * Main method to execute the test cases.
     * 
     * @param args Command-line arguments.
     */
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

    /**
     * Test case to list friends of a user.
     * 
     * @param recommender The LastFMRecommender object.
     * @param userId      The ID of the user.
     */
    public static void testListFriends(LastFMRecommender recommender, int userId) {
        recommender.listFriends(userId);
        System.out.println();
    }

    /**
     * Test case to find common friends between two users.
     * 
     * @param recommender The LastFMRecommender object.
     * @param userId1     The ID of the first user.
     * @param userId2     The ID of the second user.
     */
    public static void testCommonFriends(LastFMRecommender recommender, int userId1, int userId2) {
        recommender.commonFriends(userId1, userId2);
        System.out.println();
    }

    /**
     * Test case to list artists listened by both users.
     * 
     * @param recommender The LastFMRecommender object.
     * @param userId1     The ID of the first user.
     * @param userId2     The ID of the second user.
     */
    public static void testListArtists(LastFMRecommender recommender, int userId1, int userId2) {
        recommender.listArtists(userId1, userId2);
        System.out.println();
    }

    /**
     * Test case to list the top 10 most popular artists.
     * 
     * @param recommender The LastFMRecommender object.
     */
    public static void testListTop10(LastFMRecommender recommender) {
        recommender.listTop10();
        System.out.println();
    }

    /**
     * Test case to recommend 10 most popular artists for a user.
     * 
     * @param recommender The LastFMRecommender object.
     * @param userId      The ID of the user.
     */
    public static void testRecommend10(LastFMRecommender recommender, int userId) {
        recommender.recommend10(userId);
        System.out.println();
    }
}