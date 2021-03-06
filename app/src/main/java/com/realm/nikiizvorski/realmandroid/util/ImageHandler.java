package com.realm.nikiizvorski.realmandroid.util;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * The type Image handler.
 */
public class ImageHandler extends SimpleTarget<GlideDrawable> {

    private ImageView mImageIcon;

    /**
     * Instantiates a new Image handler.
     *
     * @param imageIcon the image icon
     */
    public ImageHandler(ImageView imageIcon) {
        mImageIcon = imageIcon;
    }

    @Override
    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
        mImageIcon.setImageDrawable(resource);
        GlideBitmapDrawable drawable = (GlideBitmapDrawable) mImageIcon.getDrawable().getCurrent();
        Bitmap bitmap = drawable.getBitmap();
    }
}
