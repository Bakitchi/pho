package com.eharmony.services.mymatchesservice.rest;

import java.util.Set;

import com.eharmony.services.mymatchesservice.rest.internal.DataServiceStateEnum;

public interface MatchFeedQueryContext {

    public long getUserId();
    public long getMatchId();
    public String getLocale();
    public int getStartPage();
    public int getPageSize();
    public Set<String> getStatuses();
    public boolean isViewHidden();
    public boolean isAllowedSeePhotos();
    public int getTeaserResultSize();

    // Internal test flags.
    public DataServiceStateEnum getVoldyState();
}
