package servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * This class is the API servlet and simply explains the information
 * 
 * @author Jimmy Dagres
 * 
 * @version Oct 9, 2013
 * 
 */
@SuppressWarnings( "serial" )
public class ApiServlet extends HttpServlet
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
        resp.getWriter().write(
                "The main servlet has a list of sites available.\n" );
        resp.getWriter()
                .write( "\nThe data on the /data server will display a JSONArray "
                        + "\ncontaining all of the cities close to the location given"
                        + "\nto main server. The location sent to the main server "
                        + "\nshould contain the desired coordinates with the keys;"
                        + "\n\"longitude\" and \"latitude\". The JSONArray returned"
                        + "\nfrom the /data server will contain; the city, country "
                        + "\nand WOEID of every nearby location with a trending "
                        + "\nhashtag." );
    }
}