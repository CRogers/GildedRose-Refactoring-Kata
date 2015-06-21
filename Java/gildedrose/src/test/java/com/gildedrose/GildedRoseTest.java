package com.gildedrose;

import static org.hamcrest.Matchers.allOf;
import org.junit.Test;

import static com.gildedrose.TestHelpers.FluentGildedRose.gildedRose;
import static com.gildedrose.TestHelpers.itemNumber;
import static com.gildedrose.TestHelpers.toSellIn;
import static com.gildedrose.TestHelpers.withName;
import static com.gildedrose.TestHelpers.withQuality;

public class GildedRoseTest {
    private final String regularItem = "regular";

    @Test
    public void regular_item_selling_in_0_with_quality_0() {
        gildedRose()
            .startingWith(
                new Item(regularItem, 0, 0)
            )
            .timesUpdatingQuality(1)
            .shouldMatch(itemNumber(0, allOf(
                withName(regularItem),
                toSellIn(-1),
                withQuality(0))));
    }

    @Test
    public void regular_item_selling_in_1_with_quality_0() {
        gildedRose()
            .startingWith(
                new Item(regularItem, 1, 0)
            )
            .timesUpdatingQuality(1)
            .shouldMatch(itemNumber(0, allOf(
                withName(regularItem),
                toSellIn(0),
                withQuality(0))));
    }

    @Test
    public void regular_item_selling_in_1_with_quality_1() {
        gildedRose()
            .startingWith(
                new Item(regularItem, 1, 1)
            )
            .timesUpdatingQuality(1)
            .shouldMatch(itemNumber(0, allOf(
                withName(regularItem),
                toSellIn(0),
                withQuality(0))));
    }
}
