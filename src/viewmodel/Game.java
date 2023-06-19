/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;
import java.awt.Graphics;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Random;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import model.Player;
import model.Tabledata;
import view.GameWindow;
import view.Menu;

/**
 *
 * @author Rasyid andriansyah
 */
public class Game extends JPanel implements Runnable{
    // mengimplementasikan Runnable java untuk membuat thread
    
    
    private Thread gameThread;
    private boolean running = false; // deteksi game berjalan
    
    private GameWindow window; // window game
    private Clip audio; // backsound
    
    // properti objek dalam game
    private final Player player; // player
    private final ObstacleHandler obs_handler; // obstacle
    private String username; // username
    private int standing; // skor standing
    private int score; // skor 
    
    public enum STATE{
        Game,
        GameOver
    }
    
    public Game(){
        // konstruktor
        
        // membuat player dgn posisi random
        Random rand = new Random();
        this.player = new Player(0, 100);
        
        // membuat obstacle
        this.obs_handler = new ObstacleHandler();
        
        // membuat backsound
        Sound bgm = new Sound();
        audio = bgm.playSound(this.audio, "naruto.wav");
    }
    
    // mengeset STATE game
    public STATE gameState = STATE.Game;
    
    public synchronized void StartGame(GameWindow gw){
        // memulai menjalankan game
        gameThread = new Thread(this); // buat thread baru
        gameThread.start(); // thread dijalankan
        this.window = gw; // buat window
        running = true; // set running
    }
    
    @Override
    public void paint(Graphics g){
        // membuat Component
        super.paint(g); 
        Image backgroundimg = new ImageIcon(getClass().getResource("/asset/back.jpg")).getImage();
        g.drawImage(backgroundimg, 0, 0, getWidth(), getHeight(), this);
        player.render(g); // render objek player
        obs_handler.renderObstacle(g); // render objek obstacle
    }
    
    @Override
    public void run() {
        // meng override method run dari parent Runnable
        while(true){
            // selama true (game loop)
            try {
                updateGame(); // update objek game
                repaint(); // membuat ulang Component (update paint())
                Thread.sleep(1000L/60L); // pause thread
                this.standing = player.getStanding(); // mengambil skor adapt
                this.score = player.getScore(); // mengambil skor fall
                if(this.player.getBoundBottom().y > 600) {
                    // jika posisi tabrakan player > 600
                    // maka player tergerus zaman (keluar frame dari kanan)
                    this.gameState = STATE.GameOver;
                }else if(this.player.getBoundTop().y < 0) {
                    // jika posisi tabrakan player < 0
                    // maka player tergerus zaman (keluar frame dari kanan)
                    this.gameState = STATE.GameOver;
                }
                if(gameState == STATE.GameOver) {
                    // jika state saat ini GameOver
                    Sound bgm = new Sound(); 
                    bgm.stopSound(this.audio); // stop bgm
                    saveScore(); // simpan skor adapt dan fall
                    close(); // tutup window
                    new Menu().setVisible(true); // menampilkan menu
                    stopGame(); // stop game
                }
            } catch (InterruptedException ex) {
                // log error
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void updateGame(){
        // update objek dalam game
        obs_handler.addObstacle(); // menambah obstacle
        obs_handler.updateObstacle(); // mengupdate kondisi obstacle
        player.update(obs_handler.getObstacles()); // mengupdate kondisi player
    }
    
    public synchronized void stopGame() {
        // menghentikan game
        try{
            gameThread.join(); // menghentikan thread
            running = false; // set tidak berjalan
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    void close() {
        // menutup window
        window.CloseWindow();
    }

    public Player getPlayer(){
        // mengambil player
        return this.player;
    }
    
    public void setUsername(String username) {
        // mengeset username game
        this.username = username;
    }
    
    public void setNilai(int score, int standing) {
        // mengeset skor player di awal game
        this.player.setScore(score);
        this.player.setStanding(standing);
    }
    
    public void saveScore() {
        // menyimpan skor saat game over
        // mainkan backsound game over
        Sound gobgm = new Sound();
        audio = gobgm.playSound(this.audio, "wasted.wav");
        
        try {
            // tambah atau update data (skor adapt dan fall)
            Tabledata texperiences = new Tabledata();
            texperiences.insertData(this.username, this.score, this.standing);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        // menampilan panel game over
        JOptionPane.showMessageDialog(null, "Username : " + this.username + "\nScore : " + this.score + "\nStanding : " + this.standing, "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
        // berhentikan sound saat panel game over hilang
        gobgm.stopSound(this.audio);
    }
}
