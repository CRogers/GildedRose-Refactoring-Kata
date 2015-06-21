package com.gildedrose;

import static org.hamcrest.Matchers.allOf;
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

    public static Matcher<Item> withName(String name) {
        return new ItemName(is(name));
    }

    private static class ItemSellIn extends FeatureMatcher<Item, Integer> {
        public ItemSellIn(Matcher<Integer> subMatcher) {
            super(subMatcher, "item to sell in", "sell in");
        }

        @Override
        protected Integer featureValueOf(Item actual) {
            return actual.sellIn;
        }
    }

    public static Matcher<Item> toSellIn(Matcher<Integer> sellingInMatcher) {
        return new ItemSellIn(sellingInMatcher);
    }

    public static Matcher<Item> toSellIn(Integer sellingIn) {
        return new ItemSellIn(is(sellingIn));
    }

    private static class ItemQuality extends FeatureMatcher<Item, Integer> {
        public ItemQuality(Matcher<Integer> subMatcher) {
            super(subMatcher, "item quality", "quality");
        }

        @Override
        protected Integer featureValueOf(Item actual) {
            return actual.quality;
        }
    }

    public static Matcher<Item> withQuality(Matcher<Integer> qualityMatcher) {
        return new ItemQuality(qualityMatcher);
    }

    public static Matcher<Item> withQuality(Integer quality) {
        return new ItemQuality(is(quality));
    }

    @Test
    public void regular_item_selling_in_0_with_quality_0() {
        Item[] items = new Item[] { new Item(regularItem, 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app, itemNumber(0, allOf(
            withName(regularItem),
            toSellIn(-1),
            withQuality(0))));
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
