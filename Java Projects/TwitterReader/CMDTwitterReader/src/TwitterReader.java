import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

/**
 * Created by Matthew on 5/18/2017.
 */
public class TwitterReader {

    final static String consumerKey = "Your consumer key here";
    final static String consumerSecret = "Your consumer Secret here";
    final static String accessToken = "Your access token here";
    final static String accessSecret = "Your access Secret here";

    public static void main(String[] args) throws TwitterException {

        String twitterHandle;
        int numOfTweets;


        if(args.length != 2){
            System.out.println("Please enter two arguments:\n" +
                    "1st argument: name of Twitter handle.\n" +
                    "2nd argument: # of tweets you want to display\n" +
                    "Example: TwitterReader @gofooda 10");
            System.exit(1);
        }

        if (!args[0].startsWith("@")){
            System.out.println("1st argument must start with @");
            System.exit(2);
        }

        if (!isPosNum(args[1])){
            System.out.println("2nd argument must be a positive number");
            System.exit(3);
        }

        twitterHandle = args[0];
        numOfTweets = Integer.parseInt(args[1]);


        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessSecret);

        TwitterFactory twitterFactory = new TwitterFactory(builder.build());
        Twitter twitter = twitterFactory.getInstance();


        List<Status> statuses = twitter.getUserTimeline(twitterHandle);

        for (int i = 0; i < numOfTweets; i++){
            System.out.println(statuses.get(i).getUser().getName() + " Tweeted >>>>>>> " + statuses.get(i).getText());
        }


    }


    private static boolean isPosNum(String string){
        try {
            Integer num = Integer.parseInt(string);
            if (num < 0){
                return false;
            }
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
