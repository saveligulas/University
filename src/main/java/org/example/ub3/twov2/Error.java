package org.example.ub3.twov2;

public enum Error {
    SAME_GENDER("Partners have the same gender"),
    UNDERAGE("One or both partners are underaged"),
    NO_PROPOSAL("You have no open proposal"),
    HAS_NOT_PROPOSED("You have not proposed"),
    HAS_A_PARTNER("Person already has a partner"),
    HAS_NO_PLANNED_MARRIAGE("You have no planned marriage"),
    HAS_NO_PREPARED_MARRIAGE("You have no prepared marriage"),
    HAS_NO_MARRIAGE("You are not married"),
    NOT_ENOUGH_GROOMSMEN("You need at least one groomsman"),
    NOT_ENOUGH_BRIDESMAIDS("You need at least one bridesmaid"),
    NOT_ENOUGH_GUESTS("You need at least one guest"),
    NOT_INVOLVED_IN_MARRIAGE("You are not involved in any marriage"),
    IS_MARRIED("You are already married you need to divorce to marry again"),
    UNEXPECTED_ERROR("An unexpected error occurred");

    public final String message;

    Error(String message) {
        this.message = message;
    }
}
