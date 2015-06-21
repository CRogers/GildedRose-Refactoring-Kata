package com.gildedrose;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void item_foo_selling_in_0_with_quality_0() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].name, is("foo"));
        assertThat(app.items[0].quality, is(0));
        assertThat(app.items[0].sellIn, is(-1));
    }

}
