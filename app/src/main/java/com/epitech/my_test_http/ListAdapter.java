package com.epitech.my_test_http;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.epitech.my_test_http.parser.RssFeed;

/**
 * Adapter pour la vue RssFeed. Affiche seulement le titre et la date.
 * Mais toutes les informations sont disponibles il suffit
 * de choisir ce que l'on veut afficher
 *
 * Created by zero on 28/04/15.
 */
public class                ListAdapter extends BaseAdapter {
	private LayoutInflater  layoutInflater;
	private RssFeed[]       _feed;

	public ListAdapter(Context c, RssFeed[] rssFeed) {
		layoutInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_feed = rssFeed;
	}

	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View listItem, ViewGroup parent) {
		if (listItem == null) {
			listItem = layoutInflater.inflate(R.layout.feed_list_item, null);
		}
		Log.v("Value of position:", String.valueOf(position));
		((TextView)listItem.findViewById(R.id.title)).setText(_feed[0].getItem(position).getTitle());
		((TextView)listItem.findViewById(R.id.date)).setText(_feed[0].getItem(position).getAuthor());
		return listItem;
	}
}