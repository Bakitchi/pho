package com.eharmony.services.mymatchesservice.service.transform;

import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eharmony.services.mymatchesservice.rest.MatchFeedRequestContext;

public abstract class AbstractMatchFeedTransformer implements IMatchFeedTransformer {
	
	Logger logger = LoggerFactory.getLogger(AbstractMatchFeedTransformer.class);

	@Override
	public MatchFeedRequestContext processMatchFeed(MatchFeedRequestContext context) {

        if (context == null) {
        	logger.debug("Match feed context is null, returning without processing. Context={}",
                      context);
            return context;
        }
        
        if(context.getLegacyMatchDataFeedDto() == null){
        	logger.debug("LegacyMatchDataFeedDto is null, returning without processing. Context={}", context);
        	return context;
        }

        for (Iterator<Map.Entry<String, Map<String, Map<String, Object>>>> matchIterator =
             context.getLegacyMatchDataFeedDto().getMatches().entrySet().iterator(); matchIterator.hasNext();) {

            Map<String, Map<String, Object>> matchInfo = matchIterator.next().getValue();

            if (!processMatch(matchInfo, context)) {

                // remove the current matchInfo from the iterator, continue with the next matchInfo
            	matchIterator.remove();
            	logger.debug("MatchInfo filtered out of result set, matchInfo={}", matchInfo);

            }
        }
        
        return context;
	}
	

    /**
     * Run filter on a matchInfo object. If the method
     * returns false, the object is removed from the feed.
     *
     * @param   match  match info object
     * @param   context    MatchFeedRequestContext
     *
     * @return  true if the transformation is successful, false otherwise
     */
    protected abstract boolean processMatch( Map<String, Map<String, Object>> match,
    											MatchFeedRequestContext context);

}