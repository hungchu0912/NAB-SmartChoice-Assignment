package com.nab.dc.fetcher;

public interface Fetcher<T, U> {
    U fetch(T t);
}
