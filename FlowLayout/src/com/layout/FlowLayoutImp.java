package com.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayoutImp extends ViewGroup {

	public FlowLayoutImp(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public FlowLayoutImp(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public FlowLayoutImp(Context context, AttributeSet attrs) {
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
		int countNums = getChildCount();
		MarginLayoutParams cParams = null;
		int widthLine=0,heightLine=0;
		for (int i = 0; i < countNums; i++) {
			View view = getChildAt(i);
			int viewWidth = view.getMeasuredWidth();
			int viewHeight = view.getMeasuredHeight();
			cParams = (MarginLayoutParams) view.getLayoutParams();
			if(widthLine+cParams.leftMargin + viewWidth + cParams.rightMargin>widthSize){
				
				width=Math.max(widthLine, width);
				widthLine=cParams.leftMargin + viewWidth + cParams.rightMargin;
				height+=cParams.topMargin + viewHeight + cParams.bottomMargin;
				heightLine=cParams.topMargin + viewHeight + cParams.bottomMargin;
				
			}else{
				widthLine+=cParams.leftMargin + viewWidth + cParams.rightMargin;
				heightLine=Math.max(heightLine, cParams.topMargin + viewHeight + cParams.bottomMargin);
			}
			if(i==countNums-1){
				height+=heightLine;
			}
		}
		setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize
				: width, heightMode == MeasureSpec.EXACTLY ? heightSize
				: height);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int countNums = getChildCount();
		MarginLayoutParams cParams = null;
		int cl = 0, ct = 0, cr = 0, cb = 0;
		int currentheight=0;
		int maxHeightLine=0;
		for (int i = 0; i < countNums; i++) {
			View view = getChildAt(i);
			int viewWidth = view.getMeasuredWidth();
			int viewHeight = view.getMeasuredHeight();
			cParams = (MarginLayoutParams) view.getLayoutParams();
			if(cl+viewWidth+cParams.leftMargin+cParams.rightMargin<getWidth()){
				
				cl+=cParams.leftMargin;
				ct=currentheight+cParams.topMargin;
				cr=cl+viewWidth;
				cb=ct+viewHeight;
				maxHeightLine=Math.max(maxHeightLine, cParams.topMargin+viewHeight);
				view.layout(cl, ct, cr, cb);
				cl+=viewWidth+cParams.rightMargin;
			}else{
				cl=cParams.leftMargin;
				currentheight+=maxHeightLine;
				maxHeightLine=0;
				ct=currentheight+cParams.topMargin;
				
				cr=cl+viewWidth;
				cb=ct+viewHeight;
				maxHeightLine=Math.max(maxHeightLine, cParams.topMargin+viewHeight);
				view.layout(cl, ct, cr, cb);
				cl+=viewWidth+cParams.rightMargin;
			}

		}

	}

}
