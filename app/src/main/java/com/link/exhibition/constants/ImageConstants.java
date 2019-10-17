package com.link.exhibition.constants;

import com.link.exhibition.R;

/**
 * Created on 2019/6/10  11:24
 * chenpan pan.chen@linkfeeling.cn
 */
@SuppressWarnings("all")
public final class ImageConstants {

    public static int matchRankImage(int num) {
        switch (num) {
            case 0:
                return R.mipmap.rank1;
            case 1:
                return R.mipmap.rank2;
            case 2:
                return R.mipmap.rank3;
            case 3:
                return R.mipmap.rank4;
            case 4:
                return R.mipmap.rank5;
            case 5:
                return R.mipmap.rank6;
            case 6:
                return R.mipmap.rank7;
            case 7:
                return R.mipmap.rank8;
            case 8:
                return R.mipmap.rank9;
            case 9:
                return R.mipmap.rank10;
        }
        return 0;
    }

}
