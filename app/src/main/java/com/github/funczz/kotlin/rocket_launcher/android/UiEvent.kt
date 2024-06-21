package com.github.funczz.kotlin.rocket_launcher.android

import java.util.UUID

data class UiEvent(

    val id: UUID,

    val payload: Any,

    ) {

    companion object {

        @JvmStatic
        fun new(payload: Any): UiEvent = UiEvent(
            id = UUID.randomUUID(),
            payload = payload,
        )

        @JvmStatic
        fun add(event: UiEvent, events: List<UiEvent>): List<UiEvent> = events + event


        @JvmStatic
        fun add(payload: Any, events: List<UiEvent>): List<UiEvent> = add(
            event = new(payload = payload), events = events
        )

        @JvmStatic
        fun del(event: UiEvent, events: List<UiEvent>): List<UiEvent> = events.filterNot {
            it.id == event.id
        }


    }
}
