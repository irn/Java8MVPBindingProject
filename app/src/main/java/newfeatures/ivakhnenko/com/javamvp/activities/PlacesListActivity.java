package newfeatures.ivakhnenko.com.javamvp.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import newfeatures.ivakhnenko.com.javamvp.R;
import newfeatures.ivakhnenko.com.javamvp.databinding.ActivityPlacesListBinding;

public class PlacesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPlacesListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_places_list);
        binding.setUser(null);
    }
}
