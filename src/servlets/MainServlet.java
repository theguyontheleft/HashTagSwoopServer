package servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import twitter4j.GeoLocation;

/**
 * @author Jimmy Dagres
 * 
 * @version Oct 9, 2013
 * 
 */
@SuppressWarnings( "serial" )
public class MainServlet extends HttpServlet
{
    // Stores the location sent to the main server, this needs to be static so
    // it's values are available to the derived servlets
    static protected boolean locationSet = false;
    static protected double longitude_;
    static protected double latitude_;

    /**
     * Get the current users location and use it to pull trending hashtags from
     * that location. WOEID = Where On Earth ID, this is important as trends for
     * a foreign country will return trends that are not understandable to
     * another country
     */
    static protected GeoLocation geoLocation_ = null;

    /**
     * @param args
     * @throws Exception
     */
    public static void main( String[] args ) throws Exception
    {
        Server server = new Server( 7070 );

        WebAppContext context = new WebAppContext();
        context.setWar( "war" );
        context.setContextPath( "/" );
        server.setHandler( context );

        server.start();
        server.join();
    }

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
        resp.getWriter().write( "Go hokies! Swoop!" );
        resp.getWriter()
                .write( "\nUse the /data to get the trending city within the "
                        + "closest given client location\n"
                        + "Or /api for the API \n" );

        if ( locationSet )
        {
            resp.getWriter()
                    .write( "Longitude received: " + longitude_ + "\n" );
            resp.getWriter().write( "Latitude received: " + latitude_ + "\n" );
        }
    }

    /*
     * This is the code that is run when the location is posted to the website
     * 
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
     * , javax.servlet.http.HttpServletResponse)
     */
    protected void
            doPost( HttpServletRequest request, HttpServletResponse resp )
                    throws IOException
    {
        resp.setContentType( "text/plain" );

        try
        {
            // Get the latitude and longitude from the
            longitude_ =
                    Double.parseDouble( request.getParameter( "longitude" ) );

            latitude_ =
                    Double.parseDouble( request.getParameter( "latitude" ) );

            // Get the WOEID (Where on Earth ID) from found GPS data
            geoLocation_ = new GeoLocation( latitude_, longitude_ );

            locationSet = true;
        }
        catch ( Exception ex )
        {
            locationSet = false;
        }
    }
}