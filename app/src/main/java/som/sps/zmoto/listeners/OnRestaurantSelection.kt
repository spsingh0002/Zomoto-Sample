package som.sps.zmoto.listeners

import som.sps.zmoto.model.Restaurants

interface OnRestaurantSelection {
    fun onClick(restaurant: Restaurants)
}