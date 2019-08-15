package com.example.black.waimai_seller.netUtil;

public class UrlUtil {
    private UrlUtil(){}

    public static UrlUtil getIns(){
        return SingleHolder.instance;
    }

    private static class SingleHolder{
        private static final UrlUtil instance = new UrlUtil();
    }

    public static final String GOODDATAURL = "http://www.papatiger.xyz/waimai/get_good_info.php";

}
