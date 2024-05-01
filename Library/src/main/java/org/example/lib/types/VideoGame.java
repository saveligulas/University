package org.example.lib.types;

import org.example.exc.ReservingItemCustomerHasAlreadyLentException;
import org.example.exc.ReservingItemMultipleTimesException;
import org.example.exc.VideoGameCannotBeReservedException;
import org.example.human.Customer;
import org.example.human.NameOfPerson;
import org.example.lib.Category;
import org.example.lib.OtherLibraryItem;
import org.example.lib.Reservation;
import org.example.lib.Section;

import java.util.List;

public class VideoGame extends OtherLibraryItem {
    public VideoGame(int copyNumber, String title, String publisher, NameOfPerson intellectualOwner, String identifier, List<Category> themeCategories) {
        super(copyNumber, title, publisher, intellectualOwner, identifier, Section.VIDEO_GAME, themeCategories);
    }

    @Override
    public Reservation reserve(Customer customer, int weeks) throws ReservingItemCustomerHasAlreadyLentException, ReservingItemMultipleTimesException, VideoGameCannotBeReservedException {
        throw new VideoGameCannotBeReservedException();
    }
}
