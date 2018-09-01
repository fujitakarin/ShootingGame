import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Shot{

    private static final int SPEED = 10; 
    private static final Point STORAGE = new Point(-20,-20);//弾の保管場所
    private int x,y,width,height;
    private Image image;
    private MainPanel panel;

    public Shot(MainPanel panel) {
        //座標を取得
        x = STORAGE.x;
        y = STORAGE.y;
        this.panel = panel;

        loadImage();//

    }

    public void move(){
        if(isInStorage())//保管庫に入っているか
            return;

        y -= SPEED;//yを減らしていく(画面上に移動)

        if(y < 0)//画面外に出たか
            store();//保管庫に移動させる

    }


    //新しい座標を生成
    public Point getPos() {
        return new Point(x, y);
    }

    //引数によって最新の座標が届き、弾の座標に代入される
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void store(){//xとyに-20を代入(保管庫)
        x = STORAGE.x;
        y = STORAGE.y;
    }

    public boolean isInStorage() {//保管庫に入っているかの真偽を返す
        if (x == STORAGE.x && y == STORAGE.x)//保管庫に入っているか
            return true;
        return false;
    }

    public void draw(Graphics g) {

        g.drawImage(image, x, y, null);
    }

    private void loadImage(){//弾の画像の読み込みなど
        ImageIcon icon = new ImageIcon(getClass().getResource("image/shot.gif"));//shot.gifの諸々の情報をiconに入れる
        image = icon.getImage();//イメージを取り出す

        width = image.getWidth(panel);//shot画像の幅を取得
        height = image.getHeight(panel);//shot画像の高さを取得
    }

}