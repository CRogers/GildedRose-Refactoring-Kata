package com.gildedrose;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.junit.Test;

public class GildedRoseTest {
    private final String regularItem = "regular";

    private static class ItemNumber extends FeatureMatcher<GildedRose, Item> {
        private final int number;

        public ItemNumber(int number, Matcher<Item> subMatcher) {
            super(subMatcher, "item number", "item");
            this.number = number;
        }

        @Override
        protected Item featureValueOf(GildedRose actual) {
            return actual.items[number];
        }
    }

    public static Matcher<GildedRose> itemNumber(int number, Matcher<Item> itemMatcher) {
        return new ItemNumber(number, itemMatcher);
    }

    private static class ItemName extends FeatureMatcher<Item, String> {
        public ItemName(Matcher<String> subMatcher) {
            super(subMatcher, "item name", "name");
        }

        @Override
        protected String featureValueOf(Item actual) {
            return actual.name;
        }
    }

    public static Matcher<Item> withName(Matcher<String> nameMatcher) {
        return new ItemName(nameMatcher);
    }

    @Test
    public void regular_item_selling_in_0_with_quality_0() {
        Item[] items = new Item[] { new Item(regularItem, 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app, itemNumber(0, withName(is(regularItem))));
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
