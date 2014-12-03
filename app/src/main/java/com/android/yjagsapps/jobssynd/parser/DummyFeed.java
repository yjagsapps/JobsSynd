package com.android.yjagsapps.jobssynd.parser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import com.android.yjagsapps.jobssynd.util.Feed;

/**
 * Created by Jags on 12/2/2014.
 */
public class DummyFeed implements Serializable {

    // Create a new item count
    private int itemCount = 0;
    // Create a new item list
    private List<Feed> itemList;
    // Serializable ID
    private static final long serialVersionUID = 1L;

    public DummyFeed(){
        // Initialize the item list
        itemList = new Vector<Feed>(0);
    }

    public void addItem(Feed item) {
        // Add an item to the Vector
        itemList.add(item);
        // Increment the item count
        itemCount++;
    }

    public Feed getItem(int position) {
        // Return the item at the chosen position
        return itemList.get(position);
    }

    public int getItemCount() {
        // Return the number of items in the feed
        return itemCount;
    }
}
