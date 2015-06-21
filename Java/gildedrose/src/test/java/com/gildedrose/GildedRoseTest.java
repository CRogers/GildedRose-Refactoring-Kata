package com.gildedrose;

import org.junit.Test;

import static com.gildedrose.TestHelpers.FluentRegularItem.regularItem;

public class GildedRoseTest {
    @Test
    public void regular_item_selling_in_0_with_quality_0() {
        regularItem()
            .sellingIn(0)
            .withQuality(0)
        .shouldEndUp()
            .sellingIn(-1)
            .withQuality(0);
    }

    @Test
    public void regular_item_selling_in_1_with_quality_0() {
        regularItem()
            .sellingIn(1)
            .withQuality(0)
        .shouldEndUp()
            .sellingIn(0)
            .withQuality(0);
    }

    @Test
    public void regular_item_selling_in_1_with_quality_1() {
        regularItem()
            .sellingIn(1)
            .withQuality(1)
        .shouldEndUp()
            .sellingIn(0)
            .withQuality(0);
    }

    @Test
    public void regular_item_selling_in_minus_1_with_quality_10() {
        regularItem()
            .sellingIn(-1)
            .withQuality(10)
        .shouldEndUp()
            .sellingIn(-2)
            .withQuality(8);
    }

    @Test
    public void regular_item_selling_in_0_with_quality_10() {
        regularItem()
            .sellingIn(0)
            .withQuality(10)
        .shouldEndUp()
            .sellingIn(-1)
            .withQuality(8);
    }
}
