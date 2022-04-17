package io.solidcrafts.stocklist.bindings

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visible_if_true")
fun visibleWhenTrue(view: View, flag: Boolean? = null) {
    view.visibility = if (flag == true) View.VISIBLE else View.GONE
}