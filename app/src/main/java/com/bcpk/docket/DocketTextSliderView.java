// EXTENDS TextSliderView from the AndroidImageSlider package to fix a bug. Bundles are inaccessible.

package com.bcpk.docket;

import android.content.Context;
import android.os.Bundle;

import com.daimajia.slider.library.SliderTypes.TextSliderView;

public class DocketTextSliderView extends TextSliderView {

    private Bundle mBundle;

    public DocketTextSliderView(Context context) {
        super(context);
    }

    public TextSliderView bundle(Bundle bundle) {
        mBundle = bundle;
        return this;
    }

    @Override
    public Bundle getBundle(){
        return mBundle;
    }
}
