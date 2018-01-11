package nyc.c4q.androidtest_unit4final;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ColorAdapter adapter;
    protected HashMap<String, String> colorDict;
    protected List<String> colorsList;
    Context context=this;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        colorDict = new HashMap<>();
//        colorDict.put("indigo", "#4b0082");
//        colorDict.put("green", "#00ff00");
//        colorDict.put("blue", "#0000ff");
//        colorDict.put("red", "#ff0000");
        // TODO: adding all the colors and their values would be tedious, instead fetch it from the url below
        // https://raw.githubusercontent.com/operable/cog/master/priv/css-color-names.json
        makeRequestWithOkHttp();

//        colorsList = new ArrayList<>();
//        String[] names = new String[] {"blue", "red", "purple", "indigo", "orange", "brown", "black", "green"};
//        for(String n: names) colorsList.add(n);

      recyclerView = findViewById(R.id.rv);
        timer();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void timer() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms

                adapter = new ColorAdapter(colorsList, colorDict, context);
                recyclerView.setAdapter(adapter);
            }
        }, 2000);
    }

    private void makeRequestWithOkHttp() {
        String url = "https://raw.githubusercontent.com/operable/cog/master/priv/css-color-names.json";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {


                String jsonData= response.body().string();
                try {
                    JSONObject jsonObject= new JSONObject(jsonData);
                    colorDict= new HashMap<>();
                    colorsList= new ArrayList<>();
                    for (int i=0; i< jsonObject.length(); i++){
                        String colorName= jsonObject.names().get(i).toString();
                         colorDict.put(colorName, jsonObject.get(colorName).toString());
                         colorsList.add(colorName);

                    //Log.d("RESPONSE=", "onResponse: ***"+jsonObject.get(colorName).toString());
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
//        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected: "+item.toString(), Toast.LENGTH_SHORT).show();
        LinearLayout fragment_container;
        fragment_container=(LinearLayout)findViewById(R.id.fragment_container);

        if(fragment_container.getVisibility()==View.VISIBLE){
            fragment_container.setVisibility(View.GONE);
        }else {
            fragment_container.setVisibility(View.VISIBLE);
        }


        return super.onOptionsItemSelected(item);
    }

    // TODO: Add options menu with the item "Info" which is always visible
    // TODO: When "Info" menu item is clicked, display the fragment InfoFragment
    // TODO: If InfoFragment is already visible and I click "Info", remove InfoFragment from the view.
    // Link to creating options menu: https://github.com/C4Q/AC-Android/tree/v2/Android/Lecture-9-Menus-and-Navigation#anatomy-of-menu-xml
}
