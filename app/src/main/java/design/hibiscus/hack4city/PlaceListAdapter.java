package design.hibiscus.hack4city;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.location.places.PlaceBuffer;

/**
 * Created by ASUS-PC on 8.10.2017.
 */

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.PlaceViewHolder>{
    private Context nContext;
    private PlaceBuffer nplaces;

    public PlaceListAdapter(Context context, PlaceBuffer places){
        this.nContext = context;
        nplaces = places;

    }


    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(nContext);
        View view = inflater.inflate(R.layout.item_place_card, parent, false);

        return new PlaceViewHolder(view);
    }


    public void swapPlaces(PlaceBuffer newPlaces){
        nplaces = newPlaces;
        if(nplaces == null){
            this.notifyDataSetChanged();
        }
    }
    @Override
    public void onBindViewHolder(PlaceListAdapter.PlaceViewHolder holder, int position) {
        String placeName = nplaces.get(position).getName().toString();
        String placeAddress = nplaces.get(position).getAddress().toString();
        holder.nameTextView.setText(placeName);
        holder.adressTextView.setText(placeAddress);
    }

    @Override
    public int getItemCount() {
        if(nplaces == null)return 0;

        return nplaces.getCount();
    }

    class PlaceViewHolder extends RecyclerView.ViewHolder{
        TextView nameTextView, adressTextView;

        public PlaceViewHolder(View itemView){
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
            adressTextView = (TextView) itemView.findViewById(R.id.address_text_view);
        }

    }
}
