package com.gildedrose;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {
    private final String regularItem = "regular";

    @Test
    public void regular_item_selling_in_0_with_quality_0() {
        Item[] items = new Item[] { new Item(regularItem, 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].name, is(regularItem));
        assertThat(app.items[0].quality, is(0));
        assertThat(app.items[0].sellIn, is(-1));
    }

    @Test
    public void regular_item_selling_in_1_with_quality_0() {
        Item[] items = new Item[] { new Item(regularItem, 1, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].name, is(regularItem));
        assertThat(app.items[0].quality, is(0));
        assertThat(app.items[0].sellIn, is(0));
    }

    @Test
    public void regular_item_selling_in_1_with_quality_1() {
        Item[] items = new Item[] { new Item(regularItem, 1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].name, is(regularItem));
        assertThat(app.items[0].quality, is(0));
        assertThat(app.items[0].sellIn, is(0));
    }
}
