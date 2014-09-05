package us.holypotatoes.todo_list;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by ben on 9/3/14.
 */
public class NewItemFragment extends Fragment {
    private OnNewItemAddedListener onNewItemAddedListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onNewItemAddedListener = (OnNewItemAddedListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnNewItemAddedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_item_fragment, container, false);
        final EditText todo_edit_text = (EditText) view.findViewById(R.id.todo_edit_text);
        todo_edit_text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == android.view.KeyEvent.ACTION_DOWN) {
                    if ((i == android.view.KeyEvent.KEYCODE_DPAD_CENTER) ||
                            (i == android.view.KeyEvent.KEYCODE_ENTER)) {
                        String newItem = todo_edit_text.getText().toString();
                        onNewItemAddedListener.onNewItemAdded(newItem);
                        todo_edit_text.setText("");
                        return true;
                    }
                }
                return false;
            }
        });
        return view;
    }

    public interface OnNewItemAddedListener {
        public void onNewItemAdded(String newItem);
    }



}
