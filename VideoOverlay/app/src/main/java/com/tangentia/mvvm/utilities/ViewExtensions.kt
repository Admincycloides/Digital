package com.tangentia.mvvm.utilities

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.google.android.material.snackbar.Snackbar

/**
 * View's visibility extensions.
 */

fun View.visible() {
    changeViewVisibility(View.VISIBLE)
}

fun View.gone() {
    changeViewVisibility(View.GONE)
}

fun View.invisible() {
    changeViewVisibility(View.INVISIBLE)
}

private fun View.changeViewVisibility(newState: Int) {
    visibility = newState
}

/**
 * Snackbar's extensions.
 */

fun View.showSmallSnackbar(text: String) {
    showSnackbar(text, Snackbar.LENGTH_SHORT)
}

fun View.showLongSnackbar(text: String) {
    showSnackbar(text, Snackbar.LENGTH_LONG)
}

fun View.showIndefiniteSnackbar(text: String) {
    showSnackbar(text, Snackbar.LENGTH_INDEFINITE)
}

private fun View.showSnackbar(text: String, duration: Int) {
    Snackbar.make(this, text, duration).show()
}

fun View.snackBarWithAction(
    message: String, actionlable: String,
    block: () -> Unit
) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        .setAction(actionlable) {
            block()
        }
}

//fun ImageView.loadImageURL(
//    url: String?,
//    placeHolderImg: Int = R.drawable.placeholder_default,
//    errorImg: Int = R.drawable.placeholder_default,
//) {
//    Picasso.with(context)
//        .load(url)
//        .placeholder(placeHolderImg)
//        .error(errorImg)
//        .into(this)
//}

//fun ShimmerFrameLayout.performShimmerStart(
//) {
//    this.showShimmer(true)
//    this.startShimmer()
//}

//fun ShimmerFrameLayout.performShimmerStop(
//) {
//    this.stopShimmer()
//    this.hideShimmer()
//}

//fun ImageView.setTintColor(
//) {
//    this.imageTintList = ColorStateList.valueOf(appColor)
//}

//fun TextView.changeTextColor(
//) {
//    this.setTextColor(appColor)
//}

//fun Button.changeTextColor(
//) {
//    this.setTextColor(appColor)
//}

//fun TextView.changeTintColor(
//) {
//    this.backgroundTintList = ColorStateList.valueOf(appColor)
//}

//fun View.changeTintColor(
//) {
//    this.backgroundTintList = ColorStateList.valueOf(appColor)
//}

//fun CheckBox.changeTintColor(
//) {
//    this.buttonTintList = ColorStateList.valueOf(appColor)
//}

//fun RadioButton.changeTintColor(
//) {
//    this.buttonTintList = ColorStateList.valueOf(appColor)
//}

//fun RadioButton.changeTextColor(
//) {
//    this.setTextColor(appColor)
//}

fun RelativeLayout.changeTintColor(color:Int
) {
    this.backgroundTintList = ColorStateList.valueOf(color)
}

fun RelativeLayout.changeBackgroundColor(color:Int
) {
    this.setBackgroundColor(color)
}

//fun MaterialButton.setTintColor(
//) {
//    this.backgroundTintList = ColorStateList.valueOf(appColor)
//}

//fun MaterialCardView.setCardStokeColor(
//) {
//    this.strokeColor = appColor
//}

//fun Button.setTintColor(
//) {
//    this.backgroundTintList = ColorStateList.valueOf(appColor)
////    this.setBackgroundColor(appColor)
//}

//fun TextInputEditText.setTintColor(
//) {
//    this.backgroundTintList = ColorStateList.valueOf(appColor)
////    this.setBackgroundColor(appColor)
//}

//fun ExtendedFloatingActionButton.setTintColor(
//) {
//    this.backgroundTintList = ColorStateList.valueOf(appColor)
////    this.setBackgroundColor(appColor)
//}

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}


///usage
/*
snackBarWithAction(message, lable){
  //TODO on user click on snackbar action lable
}
 */