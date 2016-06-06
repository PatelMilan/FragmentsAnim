package com.fragmentanimation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import com.fragmentanim.AnimationCube;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by k13 on 6/6/16.
 */
public class FragmentExample extends Fragment
{

    @IntDef({CUBE})
    public @interface AnimationStyle {}

    public static final int CUBE     = 0;
    @IntDef({NODIR, LEFT, RIGHT})
    public @interface AnimationDirection {}
    public static final int NODIR = 0;
    public static final int LEFT  = 1;
    public static final int RIGHT = 2;

    private static final long DURATION = 500;

    @AnimationStyle
    private static int sAnimationStyle = CUBE;

    @Bind(R.id.textAnimationStyle)
    TextView mTextAnimationStyle;

    public static FragmentExample newInstance(@AnimationDirection int direction) {
        FragmentExample f = new FragmentExample();
        f.setArguments(new Bundle());
        f.getArguments().putInt("direction", direction);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.content_anim, null);
        int color = Color.rgb((int) Math.floor(Math.random() * 128) + 64,
                (int) Math.floor(Math.random() * 128) + 64,
                (int) Math.floor(Math.random() * 128) + 64);
        view.setBackgroundColor(color);
        ButterKnife.bind(this, view);
        setAnimationStyleText();
        return view;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim)
    {
        switch (sAnimationStyle)
        {
            case CUBE:
                switch (getArguments().getInt("direction"))
                {
                    case LEFT:
                        return AnimationCube.create(AnimationCube.LEFT, enter, DURATION);
                    case RIGHT:
                        return AnimationCube.create(AnimationCube.RIGHT, enter, DURATION);
                }break;
        }return null;
    }

  @OnClick(R.id.buttonLeft)
    void onButtonLeft() {
        getArguments().putInt("direction", LEFT);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.layout_main, FragmentExample.newInstance(LEFT));
        ft.commit();
    }

    @OnClick(R.id.buttonRight)
    void onButtonRight() {
        getArguments().putInt("direction", RIGHT);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.layout_main, FragmentExample.newInstance(RIGHT));
        ft.commit();
    }

    @OnClick(R.id.textAnimationStyle)
    public void switchAnimationStyle(View view) {
        @AnimationStyle int[] styles;
        styles = new int[]{CUBE};
        for (int i = 0; i<styles.length-1; ++i) {
            if (styles[i] == sAnimationStyle) {
                setAnimationStyle(styles[i+1]);
                return;
            }
        }
        setAnimationStyle(CUBE);
    }

    public void setAnimationStyle(@AnimationStyle int style) {
        if (sAnimationStyle != style) {
            sAnimationStyle = style;
            setAnimationStyleText();
            Snackbar.make(getView(), "Animation Style is Changed", Snackbar.LENGTH_SHORT).show();
        }
    }

    private void setAnimationStyleText() {
        switch (sAnimationStyle)
        {
            case CUBE:
                mTextAnimationStyle.setText("Fragment Animation's");
                break;
        }
    }

}
