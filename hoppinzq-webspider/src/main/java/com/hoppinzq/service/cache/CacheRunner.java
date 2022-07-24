package com.hoppinzq.service.cache;

import com.hoppinzq.service.bean.SpiderLink;
import com.hoppinzq.service.dao.SpiderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(1)
public class CacheRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(CacheRunner.class);

    @Autowired
    private SpiderDao dao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("开始预热缓存");
        List<SpiderLink> spiderLinkList=dao.queryAllLink();
        for(SpiderLink s:spiderLinkList){
            BloomFilterCache.urlIndexFilter.put(s.getLink());
            if(s.getIsIndex()==0){
                SpiderCache.linksIndexCache.add(s);
            }
        }
        BloomFilterCache.isE=true;
        logger.debug("预热缓存结束");
    }
}