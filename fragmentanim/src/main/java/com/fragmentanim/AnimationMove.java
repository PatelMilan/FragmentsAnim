package com.fragmentanim;

import android.support.annotation.IntDef;
import android.view.animation.Transformation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by k13 on 6/6/16.
 */
public class AnimationMove  extends ViewAnimationProperty {

    @IntDef({UP, DOWN, LEFT, RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    @interface Direction {}
    public static final int UP    = 1;
    public static final int DOWN  = 2;
    public static final int LEFT  = 3;
    public static final int RIGHT = 4;

    protected final @Direction int mDirection;
    protected final boolean mEnter;

    /**
     * Create new Animation.
     * @param direction Direction of animation
     * @param enter true for Enter / false for Exit
     * @param duration Duration of Animation
     * @return
     */
    public static AnimationMove create(@Direction int direction, boolean enter, long duration) {
        switch (direction) {
            case UP:
            case DOWN:
                return new VerticalMoveAnimation(direction, enter, duration);
            case LEFT:
            case RIGHT:
                return new HorizontalMoveAnimation(direction, enter, duration);
        }
        return null;
    }

    private AnimationMove(@Direction int direction, boolean enter, long duration) {
        mDirection = direction;
        mEnter = enter;
        setDuration(duration);
    }

    private static class VerticalMoveAnimation extends AnimationMove {

        private VerticalMoveAnimation(@Direction int direction, boolean enter, long duration) {
            super(direction, enter, duration);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            float value = mEnter ? (interpolatedTime - 1.0f) : interpolatedTime;
            if (mDirection == DOWN) value *= -1.0f;
            mTranslationY = -value * mHeight;

            super.applyTransformation(interpolatedTime, t);
            applyTransformation(t);
        }

    }

    private static class HorizontalMoveAnimation extends AnimationMove {

        private HorizontalMoveAnimation(@Direction int direction, boolean enter, long duration) {
            super(direction, enter, duration);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            float value = mEnter ? (interpolatedTime - 1.0f) : interpolatedTime;
            if (mDirection == RIGHT) value *= -1.0f;
            mTranslationX = -value * mWidth;

            super.applyTransformation(interpolatedTime, t);
            applyTransformation(t);
        }

    }

}