package com.mokalab.butler.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * This draws an equilateral triangle within the set bounds, i.e. setBounds(). The triangle will point in the
 * specified mDirection. The default mDirection is NORTH and the default mColor is black.
 */
public class EquiTriangleDrawable extends Drawable {

    private int mColor = Color.BLACK;
    private Direction mDirection = Direction.NORTH;

    public EquiTriangleDrawable() {
        super();
    }

    public EquiTriangleDrawable(int color, Direction direction) {
        super();
        mColor = color;
        mDirection = direction;
    }

    public enum Direction {
        NORTH, SOUTH, EAST, WEST;
    }

    /* (non-Javadoc)
    * @see android.graphics.drawable.Drawable#draw(android.graphics.Canvas)
    */
    @Override
    public void draw(Canvas canvas) {

        Paint p = new Paint();
        p.setStyle(Style.FILL);
        p.setColor(getColor());
        Path path = getEquilateralTriangle();
        canvas.drawPath(path, p);
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    /* Returns zero
    * @see android.graphics.drawable.Drawable#getOpacity()
    */
    @Override
    public int getOpacity() {

        return 0;
    }

    private Path getEquilateralTriangle() {

        Point startPoint, p2, p3;
        Rect bounds = getBounds();
        int width = bounds.right - bounds.left;
        int height = bounds.bottom - bounds.top;
        switch (mDirection){
            case NORTH:
                startPoint = new Point(bounds.left, bounds.bottom);
                p2 = new Point(startPoint.x + width, startPoint.y);
                p3 = new Point(startPoint.x + (width / 2), startPoint.y - height);
                break;
            case SOUTH:
                startPoint = new Point(bounds.left, bounds.top);
                p2 = new Point(startPoint.x + width,startPoint.y);
                p3 = new Point(startPoint.x + (width / 2), startPoint.y + height);
                break;
            case EAST:
                startPoint = new Point(bounds.left, bounds.top);
                p2 = new Point(startPoint.x, startPoint.y + height);
                p3 = new Point(startPoint.x + width, startPoint.y + (height / 2));
                break;
            case WEST:
            default:
                startPoint = new Point(bounds.right, bounds.top);
                p2 = new Point(startPoint.x, startPoint.y + height);
                p3 = new Point(startPoint.x - width, startPoint.y + (height / 2));
                break;
        }

        Path path = new Path();
        path.moveTo(startPoint.x, startPoint.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);

        return path;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public Direction getDirection() {
        return mDirection;
    }

    public void setDirection(Direction direction) {
        mDirection = direction;
    }
}