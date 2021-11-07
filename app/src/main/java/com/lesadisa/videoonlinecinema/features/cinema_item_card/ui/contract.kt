package com.lesadisa.videoonlinecinema.features.cinema_item_card.ui

import com.lesadisa.videoonlinecinema.base.Event

class ViewState()

sealed class UIEvent() : Event {
    data class OnPlayButtonCLick(val movieUrl: String) : UIEvent()
}