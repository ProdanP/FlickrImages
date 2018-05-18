package eu.prodan.flickrimages.ui.views;

import android.content.Context;
import android.graphics.PorterDuff;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;

import eu.prodan.flickrimages.R;

/**
 * Created by User on 17.05.2018.
 */

public class DynamicToolbar extends Toolbar {
    private Context mContext;
    private EditText editText;
    private ImageView searchIcon;

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
        editText.getBackground().setColorFilter(ContextCompat.getColor(mContext,R.color.transparentWhite), PorterDuff.Mode.SRC_IN);
        searchIcon = view.findViewById(R.id.search_icon);
        searchIcon.setColorFilter(ContextCompat.getColor(mContext,R.color.transparentWhite), PorterDuff.Mode.SRC_IN);
    }

    public String getTextFromInput(){
        return editText.getText().toString();
    }

    public void setOnSearchIconClickListner(OnClickListener onClickListener){
        searchIcon.setOnClickListener(onClickListener);
    }

    public void setOnEditTextKeyListener(OnKeyListener onKeyListener){
        editText.setOnKeyListener(onKeyListener);
    }
}
