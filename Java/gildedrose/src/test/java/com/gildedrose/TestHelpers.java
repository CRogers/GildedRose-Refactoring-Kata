package com.gildedrose;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import static com.gildedrose.TestHelpers.FluentGildedRose.gildedRose;

public class TestHelpers {
    public static class FluentGildedRose {
        private Item[] items;
        private int timesUpdating = 0;

        static FluentGildedRose gildedRose() {
            return new FluentGildedRose();
        }


        public FluentGildedRose startingWith(Item... items) {
            this.items = items;
            return this;
        }

        public FluentGildedRose timesUpdatingQuality(int i) {
            this.timesUpdating = i;
            return this;
        }

        public void shouldMatch(Matcher<GildedRose> gildedRoseMatcher) {
            GildedRose app = new GildedRose(items);
            for (int i = 0; i < timesUpdating; i++) {
                app.updateQuality();
            }
            assertThat(app, gildedRoseMatcher);
        }
    }

    static class ItemNumber extends FeatureMatcher<GildedRose, Item> {
        private final int number;

        public ItemNumber(int number, Matcher<Item> subMatcher) {
            super(subMatcher, "item number " + number, "item's");
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
            super(subMatcher, "name", "but name");
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
            super(subMatcher, "days to sell in", "but sell in");
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
            super(subMatcher, "quality", "but quality");
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

    public static class FluentRegularItem {
        private int quality;
        private int sellingIn;

        public FluentRegularItem withQuality(int quality) {
            this.quality = quality;
            return this;
        }

        public FluentRegularItem sellingIn(int sellingIn) {
            this.sellingIn = sellingIn;
            return this;
        }

        public void shouldEndUpWithQuality(int endQuality) {
            String regularItem = "regular";
            gildedRose()
                .startingWith(
                    new Item(regularItem, sellingIn, quality)
                )
                .timesUpdatingQuality(1)
                .shouldMatch(itemNumber(0, allOf(
                    withName("regular"),
                    toSellIn(sellingIn - 1),
                    TestHelpers.withQuality(endQuality)
                )));
        }

        public static FluentRegularItem regularItem() {
            return new FluentRegularItem();
        }
    }
}
