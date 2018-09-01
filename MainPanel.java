import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;


//JPanelクラスをスーパークラスとし、Runnable,KeyListenerインターフェースを実装する
public class MainPanel extends JPanel implements Runnable, KeyListener {
    //パネルの大きさの設定
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    //左右
    private static final int LEFT = 0;
    private static final int RIGHT = 1;

    //他クラスのオブジェクト名を先に定義しておく
    private Player player;
    private Shot shot;

    private boolean leftPressed = false;//左右キーの判定
    private boolean rightPressed = false;//スペースキーの判定
    private boolean firePressed = false;//スペースキーの判定


    private Thread gameLoop;

    public MainPanel() {//コンストラクタァ!

        setPreferredSize(new Dimension(WIDTH, HEIGHT));//パネルサイズの指定

        setFocusable(true);

        //各オブジェクトの作成
        player = new Player(0, HEIGHT - 20, this);
        shot = new Shot(this);

        addKeyListener(this);//メインパネルにキー動作に関する色々を追加

        gameLoop = new Thread(this);
        gameLoop.start();
    }

    public void run() {//(Rannableインターフェース)

        while (true) {

            if (leftPressed) {//左キーが押されたら動く
                player.move(LEFT);
            } else if (rightPressed) {//右キーが押されたら動く
                player.move(RIGHT);
            }

            if(firePressed){//スペースが押されたら弾発射
                if(shot.isInStorage()){//保管庫に弾があるか

                    Point pos = player.getPos();//playerの座標を代入
                    //(playerのx座標と画像の横幅/2 と　y座標)を弾の座標に代入
                    shot.setPos(pos.x + player.getWidth() / 2,pos.y);
                }                                                    
            }

            shot.move();//弾の動きの処理

            repaint();//再描写

            //エラーのキャッチ
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //盤面やフィールドを書いたりする
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);//背景色
        g.fillRect(0, 0, getWidth(), getHeight());//四角形の枠を描く(左上x座標,左上y座標,幅,高さ)

        //画面に表示されるオブジェクト達        
        shot.draw(g);
        player.draw(g);
    }

    public void keyTyped(KeyEvent e) {//形だけ定義(KeyLissnnerインターフェース)
    }

    // 各キーの処理
    public void keyPressed(KeyEvent e) {//キーが押された時(KeyLissnnerインターフェース)
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (key == KeyEvent.VK_SPACE) {
            firePressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {//キーが離れた時(KeyLissnnerインターフェース)
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (key == KeyEvent.VK_SPACE) {
            firePressed = false;
        }
    }
}