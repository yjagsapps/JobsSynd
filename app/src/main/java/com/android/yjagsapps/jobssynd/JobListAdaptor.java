package com.android.yjagsapps.jobssynd;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.yjagsapps.jobssynd.util.Job;

import java.util.List;

/**
 * Created by Jags on 12/3/2014.
 */
public class JobListAdaptor extends ArrayAdapter<Job> {

    public JobListAdaptor(Activity activity, List<Job> jobs) {
        super(activity, 0, jobs);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Activity activity = (Activity) getContext();
        LayoutInflater inflater = activity.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.feed_list_item, null);
        Job job = getItem(position);

        //Job Title display
        TextView jobTitleView = (TextView) rowView.findViewById(R.id.title);
        jobTitleView.setText(job.getJobTitle());

        //Job location display
        TextView jobLocationView = (TextView) rowView.findViewById(R.id.location);
        jobLocationView.setText(job.getJobLocation());

        // Job Published data display
        TextView jobPublishedDateView = (TextView) rowView.findViewById(R.id.date);
        jobPublishedDateView.setText(job.getPubDate());

        //Job URL link
        //The visibility is set to gone in the layout.
        TextView jobURLLink = (TextView) rowView.findViewById(R.id.jobURL);
        jobURLLink.setText(job.getJobURL());


        return rowView;
    }

}
