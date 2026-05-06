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
import com.umbb.mobguide.models.Department;
import com.umbb.mobguide.models.Faculty;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity to display the list of departments for a specific faculty.
 */
public class DepartmentListActivity extends AppCompatActivity {

    private ArrayList<Department> departmentList;
    private DepartmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Receives the faculty object from the intent extras safely.
        Faculty faculty = (Faculty) getIntent().getSerializableExtra("faculty");
        setContentView(R.layout.activity_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(String.format(getString(R.string.departments_title), faculty.getName()));

        departmentList = DataManager.getDepartmentsByFaculty(faculty.getId());
        adapter = new DepartmentAdapter(this, departmentList);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Opens the detail activity for the selected department.
                Department selected = adapter.getItem(position);
                Intent intent = new Intent(DepartmentListActivity.this, DetailActivity.class);
                intent.putExtra("type", "department");
                intent.putExtra("data", selected);
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
                // Filters the department list synchronously with the user input.
                adapter.filter(newText);
                return true;
            }
        });
        return true;
    }

    /**
     * Custom adapter for Department items.
     */
    private class DepartmentAdapter extends ArrayAdapter<Department> {
        private List<Department> originalList;
        private List<Department> filteredList;

        public DepartmentAdapter(Context context, List<Department> list) {
            super(context, 0, list);
            this.originalList = new ArrayList<>(list);
            this.filteredList = list;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Reuses the item row layout for the ListView display.
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
            }
            Department department = getItem(position);
            TextView title = convertView.findViewById(R.id.item_title);
            title.setText(department.getName());
            return convertView;
        }

        public void filter(String query) {
            // Implements simple filtering logic for department search.
            filteredList.clear();
            if (query.isEmpty()) {
                filteredList.addAll(originalList);
            } else {
                for (Department d : originalList) {
                    if (d.getName().toLowerCase().contains(query.toLowerCase())) {
                        filteredList.add(d);
                    }
                }
            }
            notifyDataSetChanged();
        }
    }
}
