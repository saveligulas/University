package org.example.ub3.twov2;

import java.time.LocalDate;

public record Divorce(LocalDate date, Human initiator, Human divorcee, String reason) {
}
