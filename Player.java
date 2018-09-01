import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Player {
    //初期位置・移動スピードの設定
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int SPEED = 5;

    private int x,y,width,height;

    private Image image;

    private MainPanel panel;

    public Player(int x, int y,MainPanel panel) {
        //引数できた座標を代入
        this.x = x;
        this.y = y;
        //パネルを更新
        this.panel = panel;
        loadImage();
    }

    public void move(int dir) {
        if (dir == LEFT) {//左に動くなら
            x -= SPEED;//x座標をマイナス
        } else if (dir == RIGHT) {//右に動くなら
            x += SPEED;//x座標をプラス
        }

        if (x < 0) {
            x = 0;
        }
        if (x > MainPanel.WIDTH - width) {
            x = MainPanel.WIDTH - width;
        }
    }

    public void draw(Graphics g){
        g.drawImage(image, x, y, null);
    }

    public Point getPos() {
        return new Point(x, y);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void loadImage() {//player画像の読み込みなど

        ImageIcon icon = new ImageIcon(getClass().getResource("image/player.gif"));//player.gifの諸々の情報をiconに入れる
        image = icon.getImage();//イメージを取り出す

        width = image.getWidth(panel);//player画像の幅を代入
        height = image.getHeight(panel);//player画像の高さを代入
    }
}