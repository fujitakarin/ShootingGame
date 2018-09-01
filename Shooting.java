/*
＜＜このプログラムを実行すること＞＞
作成日：2018/8/29
コード名：Shooting.java
*/
import java.awt.Container;
import javax.swing.JFrame;

public class Shooting extends JFrame {
    public Shooting() {

        setTitle("インベーダー");//ウィンドウのタイトル

        setResizable(false);//ウィンドウのサイズが変更できるかどうか

        //メインパネルを作成してフレームに追加
        MainPanel panel = new MainPanel();
        Container contentPane = getContentPane();
        contentPane.add(panel);

        //パネルサイズに合わせてフレームサイズを自動設定
        pack();
    }

    public static void main(String[] args) {
        Shooting frame = new Shooting();//Shootingメソッド(Frame)をインスタンス化
        //ウィンドウを閉じる時の設定
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//バツボタンを押すとアプリケーションを終了
        frame.setVisible(true);
    }
}