package design.hibiscus.hack4city;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LocationActivity extends Activity {
    private static final String TAG = "GetData : ";

    private final String Vapurİskeleleri = "VapurIskeleleri";
    private final String BisimIstasyonlar = "BisimIstasyonlar";

    public static String URI = "http://hackathon.izmir.bel.tr/api/token";

    private TextView verileriGoster;
    private JSONObject json;


    private boolean isDepartureSet = true;
    private boolean isArrivalSet = false;

    //private Button getLoc;

    String[] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText departure;
    EditText arrival;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        //verileriGoster = (TextView)findViewById(R.id.textView);
        //getLoc = (Button)findViewById(R.id.getLocation);

        listView = (ListView)findViewById(R.id.listViewDeparture);
        departure = (EditText)findViewById(R.id.textSearchDeparture);
        arrival = (EditText)findViewById(R.id.textSearchArrival);

        listView.setVisibility(View.INVISIBLE);

        initList();
        departure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDepartureSet = true;
                isArrivalSet = false;
            }
        });
        departure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().equals("")){
                    initList();
                    listView.setVisibility(View.INVISIBLE);
                }
                else{
                    searchItem(s.toString());
                    listView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        arrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isArrivalSet = true;
                isDepartureSet = false;
            }
        });
        arrival.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    initList();
                    listView.setVisibility(View.INVISIBLE);
                }
                else{
                    searchItem(s.toString());
                    listView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id) {

                if(isDepartureSet){
                    departure.setText(listView.getItemAtPosition(position).toString());
                }
                else if(isArrivalSet){
                    arrival.setText(listView.getItemAtPosition(position).toString());
                    new Intent(LocationActivity.this,TabActivity.class);
                }

            }
        });


//        getLoc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new GetData(LocationActivity.this).execute("access_token");
//            }
//        });



    }

    public void searchItem(String textToSearch){
        for(String item:items){
            if(!item.contains(textToSearch)){
                listItems.remove(item);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void initList(){
        items = new String[]{"almanya","izmir","buca"};
        listItems = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(LocationActivity.this,R.layout.list_item, R.id.textItem, listItems);
        listView.setAdapter(adapter);
    }

    protected JSONObject readData() throws JSONException, IOException {

        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(URI);

        HttpResponse response = client.execute(get);
        StatusLine status = response.getStatusLine();

        int statusCode = status.getStatusCode();

        if(statusCode == 200){
            HttpEntity entity = response.getEntity();
            String data = EntityUtils.toString(entity);
            JSONArray posts = new JSONArray(data);
            JSONObject last = posts.getJSONObject(0);

            Log.d(TAG, "statusCode:200");

            return last;
        }
        Log.d(TAG, "statusCode:500");
        return null;
    }


    public class GetData extends AsyncTask<String, String, String>{

        private Context context;

        public GetData(Context context) {
            this.context = context;
        }
        
        @Override
        protected String doInBackground(String... params) {

            try {
                json = readData();
                String gelDataGel = json.getString(params[0]);

                return gelDataGel;
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            verileriGoster.setText(s);

        }



    }
}
