package com.fragmentanim;

import android.support.annotation.IntDef;
import android.view.animation.Transformation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by k13 on 6/6/16.
 */
public class AnimationCube extends ViewAnimationProperty
{
    @IntDef({LEFT, RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    @interface Direction {}
    public static final int LEFT  = 1;
    public static final int RIGHT = 2;

    protected final @Direction int mDirection;
    protected final boolean mEnter;

    /**
     * Create new Animation.
     * @param direction Direction of animation
     * @param enter true for Enter / false for Exit
     * @param duration Duration of Animation
     * @return
     */
    public static AnimationCube create(@Direction int direction, boolean enter, long duration) {
        switch (direction) {
            case LEFT:
            case RIGHT:
                return new HorizontalCubeAnimation(direction, enter, duration);
        }
        return null;
    }

    private AnimationCube(@Direction int direction, boolean enter, long duration) {
        mDirection = direction;
        mEnter = enter;
        setDuration(duration);
    }

    private static class VerticalCubeAnimation extends AnimationCube {

        private VerticalCubeAnimation(@Direction int direction, boolean enter, long duration) {
            super(direction, enter, duration);
        }

        /*@Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            mPivotX = width * 0.5f;
            mPivotY = (mEnter == (mDirection == UP)) ? 0.0f : height;
            mCameraZ = -height * 0.015f;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            float value = mEnter ? (interpolatedTime - 1.0f) : interpolatedTime;
            if (mDirection == DOWN) value *= -1.0f;
            mRotationX = value * 90.0f;
            mTranslationY = -value * mHeight;

            super.applyTransformation(interpolatedTime, t);
            applyTransformation(t);
        }*/

    }

    private static class HorizontalCubeAnimation extends AnimationCube {

        private HorizontalCubeAnimation(@Direction int direction, boolean enter, long duration) {
            super(direction, enter, duration);
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            mPivotX = (mEnter == (mDirection == LEFT)) ? 0.0f : width;
            mPivotY = height * 0.5f;
            mCameraZ = -width * 0.015f;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            float value = mEnter ? (interpolatedTime - 1.0f) : interpolatedTime;
            if (mDirection == RIGHT) value *= -1.0f;
            mRotationY = -value * 90.0f;
            mTranslationX = -value * mWidth;

            super.applyTransformation(interpolatedTime, t);
            applyTransformation(t);
        }

    }
}
