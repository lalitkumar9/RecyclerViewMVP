package com.cs.recyclerviewmvp.framework.view;

import android.view.View;

/**
 * Created by Vijay Patel on 29/11/18.
 */

public interface OnHolderClickListener {
    void onHolderClicked(int holderPosition, View clickedImage);
}