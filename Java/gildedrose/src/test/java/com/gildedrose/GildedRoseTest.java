package com.gildedrose;

import org.junit.Test;

import static com.gildedrose.TestHelpers.FluentItemTest.agedBrie;
import static com.gildedrose.TestHelpers.FluentItemTest.regularItem;

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
        agedBrie()
            .sellingIn(10)
            .withQuality(0)
        .shouldEndUpWithQuality(1);
    }

    @Test
    public void aged_brie_should_not_increase_past_50() {
        agedBrie()
            .sellingIn(10)
            .withQuality(50)
        .shouldEndUpWithQuality(50);
    }

    @Test
    public void aged_brie_past_sell_date_should_increase_quality_by_two() {
        agedBrie()
            .sellingIn(0)
            .withQuality(20)
        .shouldEndUpWithQuality(22);
    }
}
