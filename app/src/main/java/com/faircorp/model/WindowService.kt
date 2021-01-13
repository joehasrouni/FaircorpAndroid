package com.faircorp.model


//Local database for testing
// it is never used

class WindowService {
companion object {
    // Fake rooms
    val ROOMS: List<RoomDto> = listOf(
        RoomDto(1, "Room EF 6.10", 18.2, 20.0),
        RoomDto(2, "Hall", 18.2, 18.0),
        RoomDto(3, "Room EF 7.10", 21.2, 20.0)
    )

    // Fake windows
    val WINDOWS: List<WindowDto> = listOf(
        WindowDto(1, "Entry Window", -10, Status.CLOSED),
        WindowDto(2, "Back Window", -10, Status.CLOSED),
        WindowDto(3, "Sliding door", -10, Status.OPEN),
        WindowDto(4, "Window 1", -10, Status.CLOSED),
        WindowDto(5, "Window 2", -10, Status.CLOSED)
    )
}

fun findById(id: Long) = WINDOWS.firstOrNull { it.id == id}

fun findAll() = WINDOWS.sortedBy { it.name }
}