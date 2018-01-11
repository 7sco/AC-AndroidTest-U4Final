package nyc.c4q.androidtest_unit4final;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by justiceo on 1/9/18.
 */

public class InfoFragment extends Fragment {
    Button moreBtn;
    TextView more_textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.info_fragment, container, false);
        moreBtn=(Button)v.findViewById(R.id.moreBtn);
        more_textView=(TextView) v.findViewById(R.id.more_textView);
        moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (more_textView.getVisibility()== View.VISIBLE){
                    more_textView.setVisibility(View.GONE);
                }
                else {
                    more_textView.setVisibility(View.VISIBLE);
                }

            }
        });
        return v;
    }
}
