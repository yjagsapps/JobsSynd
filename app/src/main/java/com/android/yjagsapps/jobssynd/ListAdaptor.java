package com.android.yjagsapps.jobssynd;

import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.yjagsapps.jobssynd.parser.DummyFeed;
import com.android.yjagsapps.jobssynd.parser.RSSFeed;

import com.android.yjagsapps.jobssynd.util.Feed;
import java.util.List;
/**
 * Created by Jags on 12/2/2014.
 */
public class ListAdaptor extends BaseAdapter {

    // Create a new LayoutInflater
    private LayoutInflater layoutInflater;
    // Create a new RSSFeed
    private RSSFeed feed;
    private DummyFeed feed1;

    public ListAdaptor(Context c, RSSFeed rssFeed) {
        // Set the layout inflater
        layoutInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Set the RSS feed
        feed = rssFeed;
    }

    //Alternate Constructor
    public ListAdaptor(Context c, DummyFeed listFeed) {
        // Set the layout inflater
        layoutInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Set the RSS feed
        feed1 = listFeed;
    }

    @Override
    public int getCount() {
        // Set the total list item count
        return feed1.getItemCount();
    }

    @Override
    public Object getItem(int position) {
        // Return the position
        return position;
    }

    @Override
    public long getItemId(int position) {
        // Return the position
        return position;
    }

    @Override
    public View getView(int position, View listItem, ViewGroup parent) {
        // If a list item is null
        if (listItem == null) {
            // Inflate a list item
            listItem = layoutInflater.inflate(R.layout.feed_list_item, null);
        }
        // Set the views in the layout
        //((TextView)listItem.findViewById(R.id.title)).setText(feed.getItem(position).getTitle());
        // Bit of formatting for adding the author
        //((TextView)listItem.findViewById(R.id.date)).setText(feed.getItem(position).getDate() + " - " + feed.getItem(position).getAuthor());
        //// Return the new list item


        // Set the views in the layout
        ((TextView)listItem.findViewById(R.id.title)).setText(feed1.getItem(position).getName());
        // Bit of formatting for adding the author
        ((TextView)listItem.findViewById(R.id.date)).setText(feed1.getItem(position).getId()+ " - " + feed1.getItem(position).getURL());
        // Return the new list item




        return listItem;
    }

}
