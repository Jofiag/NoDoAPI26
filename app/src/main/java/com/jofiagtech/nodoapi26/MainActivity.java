package com.jofiagtech.nodoapi26;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jofiagtech.nodoapi26.model.NoDo;
import com.jofiagtech.nodoapi26.model.NoDoViewModel;
import com.jofiagtech.nodoapi26.ui.RecyclerViewAdapter;
import com.jofiagtech.nodoapi26.util.NoDoRepository;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int NODO_REQUEST_CODE = 1;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private NoDoViewModel mNoDoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setAllUiNeeds();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewNoDoActivity.class);
                startActivityForResult(intent, NODO_REQUEST_CODE);
            }
        });

    }

    private void setAllUiNeeds(){
        //mNoDoViewModel = new NoDoViewModel(getApplication());
        mNoDoViewModel = ViewModelProviders.of(this).get(NoDoViewModel.class);
        mNoDoViewModel.getAllNoDoItem().observe(this, new Observer<List<NoDo>>() {
            @Override
            public void onChanged(List<NoDo> noDos) {
                mAdapter.setNoDoList(noDos);
            }
        });


        mRecyclerView = findViewById(R.id.recycler_view);
        mAdapter = new RecyclerViewAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NODO_REQUEST_CODE && resultCode == RESULT_OK){
            assert data != null;
            String text = data.getStringExtra("nodo");
            NoDo noDo = new NoDo(text);
            mNoDoViewModel.insert(noDo);
        }
        else
            Toast.makeText(this, "Empty fiels", Toast.LENGTH_SHORT).show();
    }
}
