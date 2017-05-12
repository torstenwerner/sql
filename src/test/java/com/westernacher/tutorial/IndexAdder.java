package com.westernacher.tutorial;

import java.util.Map;
import java.util.function.Function;

public class IndexAdder<DelegateType>  implements Function<DelegateType, IndexAdder.WithIndex<DelegateType>> {

    private int currentIndex = 0;

    @Override
    public WithIndex<DelegateType> apply(DelegateType delegate) {
        return new WithIndex<>(currentIndex++, delegate);
    }

    public static class WithIndex<DelegateType> {

        final private int index;
        final private DelegateType delegate;

        public WithIndex(int index, DelegateType delegate) {
            this.index = index;
            this.delegate = delegate;
        }

        public int index() {
            return index;
        }

        public DelegateType get() {
            return delegate;
        }

        @Override
        public String toString() {
            return "WithIndex{" +
                    "index=" + index +
                    ", delegate=" + delegate +
                    '}';
        }
    }
}
