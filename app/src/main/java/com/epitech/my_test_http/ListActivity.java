package com.epitech.my_test_http;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.epitech.my_test_http.parser.RssFeed;
import com.epitech.my_test_http.util.WriteObjectFile;

import static com.epitech.my_test_http.parser.ChangeRssFeed._feedCount;
import static com.epitech.my_test_http.parser.ChangeRssFeed.getFeedName;

/**
 * On affiche la feed RSS du site entré par l'utilisateur
 *
 * Created by zero on 28/04/15.
 */
public class            ListActivity extends Activity {
    private boolean     _isRefresh = false;
	private ListAdapter _adapter;
    private ListView    _list;
	private RssFeed[]   _feed = new RssFeed[2];

	@Override
	protected void      onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feed_list);
		if (android.os.Build.VERSION.SDK_INT >= 11) {
			getActionBar().setDisplayShowHomeEnabled(false);
		}
		// On recupere la feed à partir d'un document
		// C'est ici que l'on a besoin que la classe soit serialize
		// on passe d'un document à une classe.
		for (int i = 0; i < _feedCount; ++i) {
			_feed[i] = (RssFeed) new WriteObjectFile(this).readObject(getFeedName(i));
		}
		_list = (ListView)findViewById(R.id.listView);
		_list.setVerticalFadingEdgeEnabled(true);
		_adapter = new ListAdapter(this, _feed);
		_list.setAdapter(_adapter);
	}

	@Override
	public boolean      onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
        // Menu pour changer le lien rss
		MenuItem change = menu.add(Menu.NONE, 0, Menu.NONE, "CHANGE FEED");
		// Menu pour rafraichir
        MenuItem reload = menu.add(Menu.NONE, 1, Menu.NONE, "RELOAD");
		if(android.os.Build.VERSION.SDK_INT >= 11){
			change.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
			reload.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		}
		return true;
	}

	//@Override
	//public boolean      onOptionsItemSelected(MenuItem item) {
//		_isRefresh = true;
//		switch (item.getItemId()) {
//		case 0:
//			changeFeed(_isRefresh, this);
//			return true;
//		case 1:
//			new LoadRSSFeed(this, _RssFeeDUrl).execute();
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
	//}

	@Override
	public void         onDestroy() {
		super.onDestroy();
		_adapter.notifyDataSetChanged();
	}

	@Override 
	public void         onResume(){
		super.onResume();
//		if(_isRefresh){
//			_feed = (RssFeed)new WriteObjectFile(this).readObject(getFeedName());
//			_adapter = new ListAdapter(ListActivity.this, _feed);
//			_list.setAdapter(_adapter);
//			_isRefresh = false;
//		}
	}
}