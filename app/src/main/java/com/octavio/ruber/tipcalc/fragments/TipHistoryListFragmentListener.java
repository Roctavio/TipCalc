package com.octavio.ruber.tipcalc.fragments;

import com.octavio.ruber.tipcalc.models.TipRecord;

/**
 * Created by Ruber Octavio on 6/19/2016.
 */
public interface TipHistoryListFragmentListener {
    void addToList(TipRecord record);
    void clearList();
}
