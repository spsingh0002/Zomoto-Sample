package som.sps.zmoto.listeners

import som.sps.zmoto.model.LocationSuggestion

interface SearchSelectionListener {
    fun onSelection(location: LocationSuggestion)
}