package com.github.alexvishneuski.vkbestclient.repo.networking.imageloader.impl;

import android.widget.ImageView;

import com.github.alexvishneuski.vkbestclient.imageloader.NOrda;
import com.github.alexvishneuski.vkbestclient.repo.networking.imageloader.IImageLoaderNetworking;

/**
 * Created by alex_vishneuski on 30.11.2017.
 */

public class ImageLoaderNetworkingImpl implements IImageLoaderNetworking {
    @Override
    public void loadImage(String pSourcePath, ImageView pDestinationView) {
        NOrda.INSTANCE.load(pSourcePath).into(pDestinationView);
    }
}
