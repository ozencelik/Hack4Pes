package design.hibiscus.hack4city;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS-PC on 8.10.2017.
 */

public class NewLocationaActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

        private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
        private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 100;

        public static final String TAG = NewLocationaActivity.class.getSimpleName();

        private static final int PERMISSIONS_REQUEST_FINE_LOCATION = 111;
        private static final int PLACE_PICKER_REQUEST = 1;


        private PlaceListAdapter mAdapter;
        private RecyclerView mRecyclerView;
        private boolean mIsEnabled;
        private GoogleApiClient mClient;

        private TextView t;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_location);

        mRecyclerView = (RecyclerView) findViewById(R.id.places_list_rec_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PlaceListAdapter(this, null);

        mRecyclerView.setAdapter(mAdapter);
        t = (TextView)findViewById(R.id.loc);

        FragmentActivity f = new FragmentActivity();

        mClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .build();


    }


    @Override
        public void onConnected(@Nullable Bundle bundle) {
            refreshPlacesData();
            Log.i(TAG, "API Client Connected Succesful");
        }

        @Override
        public void onConnectionSuspended(int i) {

        }

        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            Log.e(TAG, "API Client Connection Failed");
        }

        public void refreshPlacesData(){
            Uri uri = PlaceContract.PlaceEntry.CONTENT_URI;
            Cursor data = getContentResolver().query(uri,null,null,null,null);
            if(data == null || data.getCount() == 0) return;

            List<String> guids = new ArrayList<>();

            while(data.moveToNext()){
                guids.add(data.getString(data.getColumnIndex(PlaceContract.PlaceEntry.COLUMN_PLACE_ID)));
            }
            final PendingResult<PlaceBuffer> placeResult= Places.GeoDataApi.getPlaceById(mClient,
                    guids.toArray(new String[guids.size()]));

            placeResult.setResultCallback(new ResultCallback<PlaceBuffer>() {
                @Override
                public void onResult(@NonNull PlaceBuffer places) {
                    mAdapter.swapPlaces(places);
                }
            });



        }

        public void onAddPlaceButtonClicked(View view) throws GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_FINE_LOCATION);

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_FINE_LOCATION);

            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Location Permission !!!",Toast.LENGTH_SHORT).show();
            }

            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            Intent i = builder.build(this);
            startActivityForResult(i, PLACE_PICKER_REQUEST);

        }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK){
            Place place = PlacePicker.getPlace(this, data);

            if(place == null){
                Log.i(TAG, "No place selected");
                return;
            }

            String placeID = place.getId();

            ContentValues cValues = new ContentValues();

            cValues.put(PlaceContract.PlaceEntry.COLUMN_PLACE_ID, placeID);
            //getContentResolver().insert(PlaceContract.PlaceEntry.CONTENT_URI, cValues);

            refreshPlacesData();
        }


    }
}
