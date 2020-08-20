package com.dylanvann.fastimage;

import androidx.annotation.Keep;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

// We need an AppGlideModule to be present for progress events to work.
@Keep
@GlideModule
public final class FastImageGlideModule extends AppGlideModule {
}
