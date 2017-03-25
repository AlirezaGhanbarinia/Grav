package com.github.glomadrian.grav.generator.animation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.github.glomadrian.grav.R;
import com.github.glomadrian.grav.figures.Grav;

public class VerticalMoveAnimator extends GravAnimatorGenerator<Grav> {
  private float variance = 50;
  private long minAnimationDuration = 2000;
  private long maxAnimationDuration = 3000;

  @Override
  protected ValueAnimator createValueAnimator(Grav grav, int width, int height) {
    PointF startPoint = grav.getStartPoint();
    ValueAnimator valueAnimator = ValueAnimator.ofFloat(startPoint.y - variance, startPoint.y + variance);
    valueAnimator.setDuration(getRandomDuration(minAnimationDuration, maxAnimationDuration));
    valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
    valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
    return valueAnimator;
  }

  private long getRandomDuration(long min, long max){
    return min + (int)(Math.random() * max);
  }

  @Override
  protected UpdageGravListener<Grav> createUpdateListener() {
    return new UpdageGravListener<Grav>() {
      @Override
      public void onUpdate(Grav grav, ValueAnimator animator) {
        float value = (float) animator.getAnimatedValue();
        grav.setY(value);
      }
    };
  }

  @Override
  public void configure(TypedArray attributeSet, Context context) {
    variance = attributeSet.getDimension(R.styleable.GravView_animation_variance, variance);
    minAnimationDuration = attributeSet.getInteger(R.styleable.GravView_min_animation_time, (int) minAnimationDuration);
    maxAnimationDuration = attributeSet.getInteger(R.styleable.GravView_max_animation_time, (int) maxAnimationDuration);
  }
}
