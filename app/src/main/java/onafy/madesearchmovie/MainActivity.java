package onafy.madesearchmovie;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieModel>>{
    ListView listView;
    MovieAdapter adapter;
    EditText etTitle;
    Button btnSearch;

    static final String EXTRAS_TITLE = "EXTRAS_TITLE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);
        adapter.notifyDataSetChanged();
        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(adapter);

        etTitle = (EditText) findViewById(R.id.et_title);
        btnSearch = (Button) findViewById(R.id.btn_searchTitle);

        btnSearch.setOnClickListener(listener);

        String title = etTitle.getText().toString();
        if(TextUtils.isEmpty(title)){
            title = "a";
        }
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_TITLE, title);

        //Inisiasi dari Loader, dimasukkan ke dalam onCreate
        getLoaderManager().initLoader(0, bundle, this);
    }

    @Override
    public Loader<ArrayList<MovieModel>> onCreateLoader(int i, Bundle bundle) {
        String movies = "";
        if (bundle != null){
            movies = bundle.getString(EXTRAS_TITLE);
        }
        return new MyAsyncTaskLoader(this,movies);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieModel>> loader, ArrayList<MovieModel> movieItems) {
        adapter.setData(movieItems);

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieModel>> loader) {
        adapter.setData(null);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String title = etTitle.getText().toString();

            // Jika edit text-nya kosong maka do nothing
            if (TextUtils.isEmpty(title)) return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_TITLE, title);
            getLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };
}
