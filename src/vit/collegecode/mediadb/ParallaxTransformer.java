package vit.collegecode.mediadb;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;


public class ParallaxTransformer implements PageTransformer {

	private int id1;
    private int border = 0;
	private int id2;

    public ParallaxTransformer(int id_1,int id_2) {
        this.id1 = id_1;
        this.id2 = id_2;
    }

    @Override
    public void transformPage(View view, float position) {

        View parallaxView = view.findViewById(id1);
        View parallaxView1 = view.findViewById(id2);

        if (parallaxView != null) {
            if (position > -1 && position < 1) {
                float width = parallaxView.getWidth();
                parallaxView.setTranslationX(-(position * width * 0.2f));
                parallaxView1.setTranslationX(-(position * width * 0.3f));
                float sc = ((float)view.getWidth() - border)/ view.getWidth();
                if (position == 0) {
                    view.setScaleX(1);
                    view.setScaleY(1);
                } else {
                    view.setScaleX(sc);
                    view.setScaleY(sc);
                }
            }
        }
    }

    public void setBorder(int px) {
        border = px;
    }

}
