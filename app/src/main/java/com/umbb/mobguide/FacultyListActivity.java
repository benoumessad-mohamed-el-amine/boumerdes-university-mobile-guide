package com.umbb.mobguide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import com.umbb.mobguide.data.DataManager;
import com.umbb.mobguide.models.Faculty;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity to display the list of university faculties with search.
 */
public class FacultyListActivity extends AppCompatActivity {

    private ArrayList<Faculty> facultyList;
    private FacultyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initializes the layout with a Toolbar and ListView.
        setContentView(R.layout.activity_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.faculties_title);

        facultyList = DataManager.getFaculties();
        adapter = new FacultyAdapter(this, facultyList);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Opens the department list for the selected faculty.
                Faculty selected = adapter.getItem(position);
                Intent intent = new Intent(FacultyListActivity.this, DepartmentListActivity.class);
                intent.putExtra("faculty", selected);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflates the search menu item into the toolbar.
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filters the faculty list as the user types.
                adapter.filter(newText);
                return true;
            }
        });
        return true;
    }

    /**
     * Custom adapter for Faculty items.
     */
    private class FacultyAdapter extends ArrayAdapter<Faculty> {
        private List<Faculty> originalList;
        private List<Faculty> filteredList;

        public FacultyAdapter(Context context, List<Faculty> list) {
            super(context, 0, list);
            this.originalList = new ArrayList<>(list);
            this.filteredList = list;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Recycles the view for better performance in the ListView.
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
            }
            Faculty faculty = getItem(position);
            TextView title = convertView.findViewById(R.id.item_title);
            title.setText(faculty.getName());
            return convertView;
        }

        public void filter(String query) {
            // Logic to filter the faculty list based on search query.
            filteredList.clear();
            if (query.isEmpty()) {
                filteredList.addAll(originalList);
            } else {
                for (Faculty f : originalList) {
                    if (f.getName().toLowerCase().contains(query.toLowerCase())) {
                        filteredList.add(f);
                    }
                }
            }
            notifyDataSetChanged();
        }
    }
}
