package com.mf.common.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/8/27.
 */
public class Page<T> {

    private final List<T> content = new ArrayList<>();
    private int number = 0;
    private int size = 0;
    private long totalSize;

    public int getNumber() {
        return number;
    }

    public int getSize() {
        return size;
    }

    public int getNumberOfElements() {
        return this.content.size();
    }

    public List<T> getContent() {
        return Collections.unmodifiableList(this.content);
    }

    public <S> Page<S> map(List<S> content) {
        Page<S> page = new Page<S>();
        if (content != null) {
            page.content.addAll(content);
            page.number = this.number;
            page.size = this.size;
            page.totalSize = this.totalSize;
        }
        return page;
    }

    public static <S> Page<S> map(List<S> content, int number, int size, long totalSize) {
        Page<S> page = new Page<S>();
        page.content.addAll(content);
        page.number = number;
        page.size = size;
        page.totalSize = totalSize;
        return page;
    }

    public Iterator<T> iterator() {
        return this.content.iterator();
    }

    public int getTotalPages() {
        return this.getSize() == 0 ? 1 : (int) Math.ceil((double) this.getTotalSize() / (double) this.getSize());
    }

    public boolean hasNext() {
        return this.getNumber() + 1 < this.getTotalPages();
    }

    public boolean isLast() {
        return !this.hasNext();
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }
}
