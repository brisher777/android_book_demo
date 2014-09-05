package us.holypotatoes.todo_list;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class ToDoListActivity extends Activity implements NewItemFragment.OnNewItemAddedListener {
    private ArrayList<ToDoItem> todo_items;
    private ToDoItemAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //inflate the view
        setContentView(R.layout.activity_my);

        //Get references to the fragments
        FragmentManager fm = getFragmentManager();
        ToDoListFragment toDoListFragment = (ToDoListFragment)fm.findFragmentById(R.id.ToDoListFragment);

        todo_items = new ArrayList<ToDoItem>();
        aa = new ToDoItemAdapter(this,R.layout.todolist_item,todo_items);
        toDoListFragment.setListAdapter(aa);

    }

    public void onNewItemAdded(String newItem) {
        ToDoItem newToDoItem = new ToDoItem(newItem);
        todo_items.add(0, newToDoItem);
        aa.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
