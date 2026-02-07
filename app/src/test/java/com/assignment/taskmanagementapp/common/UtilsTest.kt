package com.assignment.taskmanagementapp.common

import org.junit.Assert.*
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date

class UtilsTest {
    @Test
    fun givenTimestamp_WhenFormatDate_ThenReturnsCorrectFormat() {
        // Given - January 1, 2024 at 12:00:00 GMT
        val timestamp = 1704110400000L

        // When
        val result = formatDate(timestamp)

        // Then
        assertEquals("01/01/2024", result)
    }

    @Test
    fun givenCurrentTimestamp_WhenFormatDate_ThenReturnsTodaysDate() {
        // Given
        val currentTimestamp = System.currentTimeMillis()
        val expectedFormat = SimpleDateFormat("dd/MM/yyyy").format(Date(currentTimestamp))

        // When
        val result = formatDate(currentTimestamp)

        // Then
        assertEquals(expectedFormat, result)
    }

    @Test
    fun givenZeroTimestamp_WhenFormatDate_ThenReturnsEpochDate() {
        // Given - Unix epoch (January 1, 1970)
        val timestamp = 0L

        // When
        val result = formatDate(timestamp)

        // Then
        assertEquals("01/01/1970", result)
    }
}
