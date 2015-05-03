package com.epitech.my_test_http.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.ContextThemeWrapper;

import com.epitech.my_test_http.R;
import com.epitech.my_test_http.parser.DomParser;
import com.epitech.my_test_http.parser.RssFeed;

import static com.epitech.my_test_http.parser.ChangeRssFeed.getFeedName;

/**
 * Charge un flux Rss à partir de l'URL et ecrit l'objet dans un fichier
 * dans le dossier /data de l'appli.
 * Une fois que les donnees ont ete parsees ont appel la classe qui se charge
 * de l'affichage.
 * Created by zero on 28/04/15.
 */
public class                LoadRSSFeed extends AsyncTask<Void, Void, Void> {
	private Context         _parent;
	private ProgressDialog  _refreshDialog;
	private RssFeed         _feed;
	private String          _RssFeedUrl;
	private int				_feedCount = 0;

	public LoadRSSFeed(Context c, String url) {
        _parent = c;
		this._RssFeedUrl = url;
	}

	@Override
	protected Void          doInBackground(Void... params) {
		// Parse the RSSFeed and save the object
        _feed = new DomParser().parseXml(this._RssFeedUrl);
		return null;
	}

    // On va creer une fenetre pour afficher une animation de chargement
	@Override
	protected void          onPreExecute(){
		_refreshDialog = new ProgressDialog(new ContextThemeWrapper(_parent, R.style.AppTheme));
		_refreshDialog.setMessage("Loading feed...");
		_refreshDialog.setIndeterminate(true);
		_refreshDialog.setCanceledOnTouchOutside(false);
		_refreshDialog.setCancelable(true);
		_refreshDialog.show();
	}

	@Override
	protected void          onPostExecute(Void result) {
		super.onPostExecute(result);
		// On ecrit le resultat dans un fichier pour faciliter le parsing
		new WriteObjectFile(_parent).writeObject(_feed, getFeedName(_feedCount));
		_feedCount++;
		_refreshDialog.dismiss();
	}
}