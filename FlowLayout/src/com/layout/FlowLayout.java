package com.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup {

	public FlowLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public FlowLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
		// TODO Auto-generated method stub
		return new MarginLayoutParams(getContext(), attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		measureChildren(widthMeasureSpec, heightMeasureSpec);

		int width = 0;
		int height = 0;
		int lwidth = 0, rwidth = 0, theight = 0, bheight = 0;
		int countNums = getChildCount();
		MarginLayoutParams cParams = null;
		for (int i = 0; i < countNums; i++) {
			View view = getChildAt(i);
			int viewWidth = view.getMeasuredWidth();
			int viewHeight = view.getMeasuredHeight();
			cParams = (MarginLayoutParams) view.getLayoutParams();
			if (i == 0 || i == 1) {
				theight += cParams.leftMargin + viewWidth + cParams.rightMargin;
			}
			if (i == 2 || i == 3) {
				bheight += cParams.leftMargin + viewWidth + cParams.rightMargin;
			}
			if (i == 0 || i == 2) {
				lwidth += cParams.topMargin + viewHeight + cParams.bottomMargin;
			}

			if (i == 1 || i == 3) {
				rwidth += cParams.topMargin + viewHeight + cParams.bottomMargin;
			}
		}
		width = Math.max(lwidth, rwidth);
		height = Math.max(theight, bheight);
		setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize
				: width, heightMode == MeasureSpec.EXACTLY ? heightSize
				: height);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int countNums = getChildCount();
		MarginLayoutParams cParams = null;
		int cl = 0, ct = 0, cr = 0, cb = 0;
		for (int i = 0; i < countNums; i++) {
			View view = getChildAt(i);
			int viewWidth = view.getMeasuredWidth();
			int viewHeight = view.getMeasuredHeight();
			cParams = (MarginLayoutParams) view.getLayoutParams();

			if (i == 0) {
				cl = cParams.leftMargin;
				ct = cParams.topMargin;
			} else if (i == 1) {
				cl = getWidth() - viewWidth - cParams.rightMargin;
				ct = cParams.topMargin;
			} else if (i == 2) {
				cl = cParams.leftMargin;
				ct = getHeight() - viewHeight - cParams.bottomMargin;
			} else if (i == 3) {
				cl = getWidth() - viewWidth - cParams.rightMargin;
				ct = getHeight() - viewHeight - cParams.bottomMargin;
			}
			cr = cl + viewWidth;
			cb = ct + viewHeight;

			view.layout(cl, ct, cr, cb);
		}

	}

}
