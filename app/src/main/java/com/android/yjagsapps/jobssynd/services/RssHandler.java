package com.android.yjagsapps.jobssynd.services;

/**
 * Created by Jags on 12/3/2014.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.android.yjagsapps.jobssynd.util.Job;



public class RssHandler extends DefaultHandler{
    // Feed and Article objects to use for temporary storage
    private Job currentJob = new Job();
    private List<Job> jobList = new ArrayList<Job>();

    // Number of articles added so far
    private int jobsAdded = 0;

    // Number of articles to download
    private static final int JOBS_LIMIT = 15000;

    //Current characters being accumulated
    StringBuffer chars = new StringBuffer();


    public List<Job> getJobList() {
        return jobList;
    }



    /*
     * This method is called everytime a start element is found (an opening XML marker)
     * here we always reset the characters StringBuffer as we are only currently interested
     * in the the text values stored at leaf nodes
     *
     * (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    public void startElement(String uri, String localName, String qName, Attributes atts) {
        chars = new StringBuffer();
    }



    /*
     * This method is called everytime an end element is found (a closing XML marker)
     * here we check what element is being closed, if it is a relevant leaf node that we are
     * checking, such as Title, then we get the characters we have accumulated in the StringBuffer
     * and set the current Article's title to the value
     *
     * If this is closing the "entry", it means it is the end of the article, so we add that to the list
     * and then reset our Article object for the next one on the stream
     *
     *
     * (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("title")){
            //currentJob.setJobTitle(chars.toString());

            String tempTitle = chars.toString();
            String tempLocation = "";
            Pattern p = Pattern.compile("\\(.*?\\)");
            Matcher m = p.matcher(tempTitle);
            while (m.find()) {
                tempLocation = m.group(0);
            }
            currentJob.setJobTitle(chars.toString().replace(tempLocation, ""));
            currentJob.setJobLocation(tempLocation);

        } else if (qName.equalsIgnoreCase("pubDate")){
            currentJob.setPubDate(chars.toString());
        }
        else if (qName.equalsIgnoreCase("link")){
            currentJob.setJobURL(chars.toString());
        }

        /**
        else if (localName.equalsIgnoreCase("published")){
            currentJob.setPubDate(chars.toString());
        } else if (localName.equalsIgnoreCase("id")){
            currentJob.setGuid(chars.toString());
        } else if (localName.equalsIgnoreCase("author")){
            currentJob.setAuthor(chars.toString());
        } else if (localName.equalsIgnoreCase("content")){
            currentJob.setEncodedContent(chars.toString());
        }
         **/

         else if (qName.equalsIgnoreCase("item")){

        }


        // Check if looking for article, and if article is complete
        if (qName.equalsIgnoreCase("item")) {

            jobList.add(currentJob);

            currentJob = new Job();

            // Lets check if we've hit our limit on number of articles
            jobsAdded++;
            if (jobsAdded >= JOBS_LIMIT)
            {
                throw new SAXException();
            }
        }
    }


    /*
     * This method is called when characters are found in between XML markers, however, there is no
     * guarante that this will be called at the end of the node, or that it will be called only once
     * , so we just accumulate these and then deal with them in endElement() to be sure we have all the
     * text
     *
     * (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    public void characters(char ch[], int start, int length) {
        chars.append(new String(ch, start, length));
    }

    public int getJobsAdded() {
        return jobsAdded;
    }

    public void setJobsAdded(int jobsAdded) {
        this.jobsAdded = jobsAdded;
    }
}
