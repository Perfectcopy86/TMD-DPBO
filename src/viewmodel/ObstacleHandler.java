/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;
import java.util.Random;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import static viewmodel.Constants.gameOption.GAME_HEIGHT;
import static viewmodel.Constants.gameOption.GAME_WIDTH;
import model.Obstacle;


/**
 *
 * @author Rasyid andriansyah
 */
public class ObstacleHandler {
    private final int MIN_X = 0; // posisi y maksimal (batasan bawah)
    private final int MAX_X = GAME_WIDTH - 300; // posisi y maksimal (batasan bawah)
    private final Random rand = new Random(); // inisialisasi library random
    
    private final int MAX_OBSTACLE = 5000; // jumlah maks obstacle dlm 1 frame
    private final int MIN_GAP = 32; // lebar gap minimum antar obstacle
    private int OBS_WIDTH = 50; // lebar obstacle
    private int obstacleNumber = 0; // jumlah obstacle
    private final ArrayList<Obstacle> obstacles = new ArrayList<>(); // list obstacle
    
    public ObstacleHandler() {

    }
    
  
    public void updateObstacle(){
        // mengupdate kondisi obstacle
        Iterator<Obstacle> itr = obstacles.iterator(); // iterator untuk setiap obstacle
        while(itr.hasNext()) {
            // selama obstacle ada
            Obstacle ob = itr.next();
            if(ob.getY() < 0){
                // jika posisi y obstacle melebihi batas y frame
                itr.remove(); // hilangkan obstacle
                obstacleNumber--; // decrement jumlah obstacle
            }else{
                // jika tidak, update posisi obstacle
                ob.update();
            }
        }
    }
    
    public void renderObstacle(Graphics g){
        // merender obstacle
        for (Obstacle ob : obstacles) {
            // untuk setiap obstacle
            ob.render(g); // gambar objeknya
        }
    }
     
    public void addObstacle(){
        // menambah jumlah obstacle
        if(obstacleNumber < MAX_OBSTACLE){
            // jika jumlah obstacle dalam frame
            // masih kurang dari batas maks obstacle
            float x = 0; // posisi x di paling kiri
            float y = GAME_HEIGHT; // posisi y sesuai batas;
            if(obstacleNumber > 0){
                y = obstacles.get(obstacles.size() -1).getY()+(MIN_GAP);
            }
            OBS_WIDTH =(rand.nextInt((MAX_X-50)-MIN_X)+MIN_X);
            int score = calculateScore(OBS_WIDTH);
            // buat obstacle baru
            Obstacle obstacle = new Obstacle(x, y, OBS_WIDTH , 30,  1, score);
            obstacles.add(obstacle); // tambahkan ke list
            obstacleNumber++; 
            
            if(obstacleNumber==MAX_OBSTACLE)
            {
                obstacleNumber =0 ;
            }
        }
    }
    
    private int calculateScore(int width) {
    // Menghitung nilai score berdasarkan lebar obstacle
    // Misalnya, semakin kecil width, semakin tinggi nilai score
    // Sesuaikan rumusan ini dengan kebutuhan Anda
    int score = 650 - width;
    return score;
}

    public ArrayList<Obstacle> getObstacles() {
        // mengambil obstacle
        return obstacles;
    }
}
