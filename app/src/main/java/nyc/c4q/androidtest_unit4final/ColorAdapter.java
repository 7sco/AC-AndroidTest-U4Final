package nyc.c4q.androidtest_unit4final;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

/**
 * Created by justiceo on 1/7/18.
 */

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {

    private static String TAG = "ColorAdapter";
    private List<String> colorNames;
    private HashMap<String, String> colorDict;
    Context context;

    public ColorAdapter(List<String> colors, HashMap<String, String> colorMap, Context context) {
        Sort.selectionSort(colors, true);
        colorNames = colors;
        colorDict = colorMap;
        this.context=context;
    }

    @Override
    public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.color_itemview, parent, false);
        return new ColorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ColorViewHolder holder, int position) {
        final String color = colorNames.get(position);
        holder.name.setText(color);
        try {
            holder.name.setTextColor(Color.parseColor(getColor(color)));
        } catch (Exception e) { // default to black if color is not available or invalid hex.

            if(colorDict.get(color)== null){
                holder.name.setTextColor(Color.parseColor("#0000"));
            }

            // display a long toast with the text "{color_name} has a HEX value of {color_hex}
            // for example: "blue has a HEX value of #0000ff"
        }

        Log.d(TAG, "Unable to parse color: " + color);
        // TODO: When the name in a viewHolder is clicked,
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, color+" Has a HEX value of: "+ colorDict.get(color), Toast.LENGTH_SHORT).show();
                Log.d("CLICKED==", "onClick: "+holder.name.getText());
            }
        });
    }

    @Override
    public int getItemCount() {
        return colorNames.size();
    }

    public String getColor(String s) {
        if(colorDict.containsKey(s.toLowerCase())) {
            return colorDict.get(s.toLowerCase());
        }
        return "#000000";
    }

    class ColorViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        CardView card_view;

        public ColorViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.color_name);
            card_view=(CardView)itemView.findViewById(R.id.card_view);
        }
    }
}
