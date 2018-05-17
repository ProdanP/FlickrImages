package eu.prodan.flickrimages.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;

import eu.prodan.flickrimages.R;

/**
 * Created by User on 17.05.2018.
 */

public class DynamicToolbar extends Toolbar {
    private Context mContext;
    private EditText editText;

    public DynamicToolbar(Context context) {
        super(context);
        this.mContext = context;
        init();

    }

    public DynamicToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public DynamicToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.toolbar_content, this);
        editText = view.findViewById(R.id.search_input);
    }

    public void hideToolbar() {
        this.animate().translationY(-this.getHeight()).setInterpolator(new AccelerateInterpolator(2));
    }

    public void showToolBar() {
        this.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
    }

    public String getTextFromInput(){
        return editText.getText().toString();
    }
}
