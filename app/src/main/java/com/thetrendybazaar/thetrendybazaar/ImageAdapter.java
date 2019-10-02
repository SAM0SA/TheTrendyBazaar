package com.thetrendybazaar.thetrendybazaar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageAdapter extends PagerAdapter {

    private Context context;
    private int[] imageIds;


    public ImageAdapter(Context context, int id) {
        this.context = context;
        imageIds = getImageResources(id);
    }

    @Override
    public int getCount() {
        return imageIds.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(imageIds[position]);
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }

    static int[] getImageResources(int articleId){


        switch (DatabaseManager.items.select(articleId).name){
            case "Basketball":
                return new int[] {R.drawable.basketball};
            case "Jersey":
                return new int[]{R.drawable.basketball};
            case "Nike Shoe":
                return new int[]{R.drawable.nike1, R.drawable.nike2, R.drawable.nike3};
            case "Dress Shoes":
                return new int[]{R.drawable.mens_shoes1, R.drawable.mens_shoes_2};
            case "Suit":
                return new int[]{R.drawable.suit1, R.drawable.suit2, R.drawable.suit3};
            case "Gym Shirt":
                return new int[]{R.drawable.jacked_shirt1, R.drawable.jacked_shirt2};
            case "TV":
                return new int[]{R.drawable.tv1, R.drawable.tv2, R.drawable.tv3};
            case "Headphones":
                return new int[]{R.drawable.headphones1, R.drawable.headphones2};
            case "Power Bank":
                return new int[]{R.drawable.powerbank1, R.drawable.powerbank2,
                        R.drawable.powerbank3};
            case "Supreme Tee":
                return new int[]{R.drawable.supreme_tee, R.drawable.supreme_tee_black};
            case "Friends Hoodie":
                return new int[]{R.drawable.friends1, R.drawable.friends2, R.drawable.friends3,
                        R.drawable.friends4};
            case "Summer Tee":
                return new int[]{R.drawable.summer1, R.drawable.summer2};
            case "Heels":
                return new int[]{R.drawable.heels1, R.drawable.heels2};
            case "Marker Pens":
                return new int[]{R.drawable.marker1, R.drawable.marker2};
            case "Harry Potter Lego Set":
                return new int[]{R.drawable.lego1, R.drawable.lego2, R.drawable.lego3};
            case "Play-Doh":
                return new int[]{R.drawable.play_doh1};
            default:
                return new int[]{};
        }


    }
}
