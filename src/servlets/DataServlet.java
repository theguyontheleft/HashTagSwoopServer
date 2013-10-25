package servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 * This servlet posts the JSON data for the most popular hashtags in the past 24
 * hours.
 * 
 * @author Jimmy Dagres
 * 
 * @version Oct 9, 2013
 * 
 */
@SuppressWarnings( "serial" )
public class DataServlet extends MainServlet
{
    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
     * , javax.servlet.http.HttpServletResponse)
     */
    protected void
            doGet( HttpServletRequest request, HttpServletResponse resp )
                    throws IOException
    {
        resp.setContentType( "text/plain" );

        if ( locationSet )
        {
            getDailyJSONString( resp );

            // stringToDisplay +=
            // "{ \"location\" : [{ \"longitude\" : "
            // + longitude_
            // + ", \"latitude\" : " + latitude_ + " }]}";
        }

    }

    /**
     * This function gets the JSON data from an asynchronous thread and returns
     * the JSON data. geolocation must be static in order to not be null
     * 
     * @return the JSON string
     */
    @SuppressWarnings( { "static-access" } )
    private String getDailyJSONString( HttpServletResponse resp )
    {
        resp.setContentType( "text/plain" );

        try
        {
            // Ideally I wanted to pull twitter data to display the popular hash
            // tags, but unfortunately . So I am simply going to pull the
            // location from the server.
            AccessToken a =
                    new AccessToken(
                            "95992848-XXzeXtTzDJ381EGarAFaO7YQCXINKj7Hj2Opewv2t",
                            "wd7m6iqqWA0JzkluHHp8fAwKTjS6454umrMfdsyKcE" );

            Twitter twitter = new TwitterFactory().getInstance();
            twitter.setOAuthConsumer(
                    "aW7ZFJXcG1KADBcxDAAZg",
                    "6v8vjx3lY78YphukOPObvnwBwQAehD4rN1oYPiA" );

            twitter.setOAuthAccessToken( a );

            ResponseList<twitter4j.Location> dailyTrends;

            // ****** The following function (getAvailableTrends) is deprecated,
            // this function would ideally return the most popular trends based
            // on their location, because it is deprecated this particular idea
            // is on hold:
            // dailyTrends = twitter.getAvailableTrends( super.geoLocation_ );

            // Because the data I am looking for (the most
            // popular hash tags) is not available. so instead I am displaying a
            // JSON of the closest city to the client of which has a trending
            // hashtag
            if ( null != super.geoLocation_ )
            {
                // geolocation must be static or it will always be null
                dailyTrends = twitter.getClosestTrends( super.geoLocation_ );
                String stringToDisplay = "{";

                for ( int j = 0; j < dailyTrends.size(); j++ )
                {
                    // Create a JSON Array with the longitude and latitude
                    // Example: { "location" : [{ "Country" :
                    // "United States", "CityName" :
                    // "Blacksburg", "WOEID" : 2414469}]}
                    stringToDisplay +=
                            "\"location\" : [{ \"Country\" : \""
                                    + dailyTrends.get( j ).getCountryName()
                                    + "\", \"City\" : \""
                                    + dailyTrends.get( j ).getName() + "\", "
                                    + "\"WOEID\" : \""
                                    + dailyTrends.get( j ).getWoeid() + "\"}]";
                }

                stringToDisplay += "}";

                resp.getWriter()
                        .write( stringToDisplay );
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }

        return "";
    }
}
