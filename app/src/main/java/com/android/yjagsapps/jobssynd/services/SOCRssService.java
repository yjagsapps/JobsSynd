package com.android.yjagsapps.jobssynd.services;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.android.yjagsapps.jobssynd.JobListAdaptor;
import com.android.yjagsapps.jobssynd.R;
import com.android.yjagsapps.jobssynd.util.Job;
import com.android.yjagsapps.jobssynd.services.RssHandler;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Jags on 12/3/2014.
 */
public class SOCRssService extends AsyncTask<String, Void, List<Job>>{
    private ProgressDialog progress;
    private Context context;
    private ListView socListView;

    public SOCRssService(Activity jobsActivity) {
        //context = socListView.getContext();
        context = jobsActivity;
        this.socListView = (ListView) jobsActivity.findViewById(R.id.listView);
        //progress = new ProgressDialog(context);
        //progress.setMessage("Loading...");
    }

    protected void onPreExecute() {
        Log.e("ASYNC", "PRE EXECUTE");
        //progress.show();
    }

    @Override
    protected List<Job> doInBackground(String... urls) {
       // return null;
        String feed = urls[0];
        Log.e("In the doBackground...", feed + " >>  ");
        URL url = null;
        try {

            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();

            url = new URL(feed);
            RssHandler rh = new RssHandler();

            xr.setContentHandler(rh);
            xr.parse(new InputSource(url.openStream()));


            Log.e("ASYNC", "PARSING FINISHED");
            Log.e("No. of jobs: " + rh.getJobsAdded(), " OK");
            return rh.getJobList();

        } catch (IOException e) {
            Log.e("RSS Handler IO", e.getMessage() + " >> " + e.toString());
        } catch (SAXException e) {
            Log.e("RSS Handler SAX", e.toString());
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            Log.e("RSS Handler Parser Config", e.toString());
        }

        return null;
    }

    protected  void onPostExecute(final List<Job>  jobs) {
        Log.e("ASYNC", "POST EXECUTE");
        //TO DO
        final Activity activity = (Activity) context;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                /**
                List<Job> jobs1 = new ArrayList<Job>();
                for(int i=0;i<100;i++){
                    Job j  = new Job();
                    j.setJobTitle("Programmer " + i);
                    j.setJobLocation("Bangalore " + i);
                    j.setPubDate("10/10/2014");
                    jobs1.add(j);
                }
                **/


                JobListAdaptor adaptor = new JobListAdaptor(activity,jobs);
                //ListView list = (ListView) socDisplayAct.findViewById(R.id.listView);
                socListView.setAdapter(adaptor);
                adaptor.notifyDataSetChanged();
                //progress.dismiss();

            }
        });


    }
}