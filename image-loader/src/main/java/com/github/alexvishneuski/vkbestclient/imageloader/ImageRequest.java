package com.github.alexvishneuski.vkbestclient.imageloader;


import android.widget.ImageView;

import java.lang.ref.WeakReference;

class ImageRequest {
    String url;
    WeakReference<ImageView> target;
    int width;
    int height;

    private ImageRequest(Builder builder) {
        url = builder.url;
        target = builder.target;
    }

    public static final class Builder {
        private final NOrda nOrda;
        private String url;
        private WeakReference<ImageView> target;

        Builder(NOrda nOrda) {
            this.nOrda = nOrda;
        }

        Builder load(String val) {
            url = val;
            return this;
        }

        public void into(ImageView val) {
            target = new WeakReference<>(val);
            nOrda.enqueue(this.build());
        }

        ImageRequest build() {
            return new ImageRequest(this);
        }
    }
}
