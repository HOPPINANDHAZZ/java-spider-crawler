package com.hoppinzq.service.service;

import com.hoppinzq.service.aop.annotation.ApiCache;
import com.hoppinzq.service.aop.annotation.ApiMapping;
import com.hoppinzq.service.aop.annotation.ApiServiceMapping;
import com.hoppinzq.service.util.GetWeb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * @author: zq
 */
@ApiServiceMapping(title = "静态资源爬取", description = "静态资源爬取",roleType = ApiServiceMapping.RoleType.NO_RIGHT)
public class WebResource{
    private static final Logger logger = LoggerFactory.getLogger(WebResource.class);

    @ApiCache
    @ApiMapping(value = "findResource", title = "查找资源", description = "查找资源")
    public List getCSDNBlogMessage(String url) throws IOException {
        return GetWeb.getImagePathList(url);
    }

}
