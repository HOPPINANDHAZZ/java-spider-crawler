package com.hoppinzq.service.work;


import com.hoppinzq.service.bean.SpiderLink;
import com.hoppinzq.service.cache.BloomFilterCache;
import com.hoppinzq.service.dao.SpiderDao;
import com.hoppinzq.service.html.HTMLPage;
import com.hoppinzq.service.html.HTTP;
import com.hoppinzq.service.html.Link;
import org.springframework.scheduling.annotation.Async;

import java.util.*;

public class HTMLParse {

    HTTP _http = null;
    SpiderDao _spiderDao=null;
    public HTMLParse(HTTP http,SpiderDao spiderDao) {
        _http = http;
        _spiderDao =spiderDao;
    }

    public void start() {
        try {
            HTMLPage _page = new HTMLPage(_http);
            _page.open(_http.getURL(), null);
            Vector _links = _page.getLinks();
            Iterator _it = _links.iterator();
            int n = 0;
            while (_it.hasNext()) {
                Link _link = (Link) _it.next();
                String _href = input(_link.getHREF().trim());
                int index = _href.indexOf("?");
                if (index != -1)
                    _href = _href.substring(0, index);
                if(_link.getPrompt()!=null){
                    String _title = input(_link.getPrompt().trim());
                    if(_title.length()>0&&BloomFilterCache.urlIndexFilter.mightContain(_href)){
                        System.err.println("链接："+_href+",已经爬取过！不予处理");
                    }
                    if(_title.length()>0&&!BloomFilterCache.urlIndexFilter.mightContain(_href)){
                        System.out.println("标题:"+_title+",链接:"+_href+"，没有被爬取过，已处理。");
                        BloomFilterCache.urlIndexFilter.put(_href);
                        BloomFilterCache.urls.add(new SpiderLink(_title,_href));
                        if(BloomFilterCache.urls.size()>=1024){
                            insertSpiderLink(BloomFilterCache.urls);
                            BloomFilterCache.urls.clear();
                        }
                    }
                    n++;
                }
            }
            System.out.println("共扫描到" + n + "个链接");
        }
        catch (Exception ex) {
            System.err.println("错误："+ex);
        }
    }

    /**
     * 解决java中的中文问题
     * @param str 输入的中文
     * @return 经过解码的中文
     */
    private static String input(String str) {
        String temp = null;
        if (str != null) {
            try {
                temp = new String(str.getBytes("ISO-8859-1"),"UTF-8");
            }
            catch (Exception e) {
            }
        }
        return temp;
    }

    @Async
    public void insertSpiderLink(List<SpiderLink> spiderLinks){
        _spiderDao.insertSpiderLink(spiderLinks);
    }
}
