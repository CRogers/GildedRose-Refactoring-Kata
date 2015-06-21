package com.gildedrose;

import org.junit.Test;

import static com.gildedrose.TestHelpers.FluentGildedRose.gildedRose;
import static com.gildedrose.TestHelpers.FluentRegularItem.regularItem;
import static com.gildedrose.TestHelpers.itemNumber;
import static com.gildedrose.TestHelpers.withQuality;

public class GildedRoseTest {
    @Test
    public void regular_item_selling_in_0_with_quality_0() {
        regularItem()
            .sellingIn(0)
            .withQuality(0)
        .shouldEndUpWithQuality(0);
    }

    @Test
    public void regular_item_selling_in_1_with_quality_0() {
        regularItem()
            .sellingIn(1)
            .withQuality(0)
        .shouldEndUpWithQuality(0);
    }

    @Test
    public void regular_item_selling_in_1_with_quality_1() {
        regularItem()
            .sellingIn(1)
            .withQuality(1)
        .shouldEndUpWithQuality(0);
    }

    @Test
    public void regular_item_selling_in_minus_1_with_quality_10() {
        regularItem()
            .sellingIn(-1)
            .withQuality(10)
        .shouldEndUpWithQuality(8);
    }

    @Test
    public void regular_item_selling_in_0_with_quality_10() {
        regularItem()
            .sellingIn(0)
            .withQuality(10)
        .shouldEndUpWithQuality(8);
    }

    @Test
    public void regular_item_selling_in_5_with_quality_100() {
        regularItem()
            .sellingIn(5)
            .withQuality(100)
        .shouldEndUpWithQuality(99);
    }

    @Test
    public void aged_brie_should_increase_in_value_from_0() {
        gildedRose()
            .startingWith(new Item("Aged Brie", 10, 0))
            .timesUpdatingQuality(1)
            .shouldMatch(itemNumber(0, withQuality(1)));
    }
}
