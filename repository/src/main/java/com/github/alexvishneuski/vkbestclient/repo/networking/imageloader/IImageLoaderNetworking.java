package com.github.alexvishneuski.vkbestclient.repo.networking.imageloader;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by alex_vishneuski on 30.11.2017.
 */

public interface IImageLoaderNetworking {

    public Bitmap loadImage(String pSourcePath);

}
