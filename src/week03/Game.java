package week03;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Game extends JFrame {

    Image offScreenImage = null;

    int width = 512;
    int height = 1024;
    Gamer gamer = new Gamer(220, 900, 36, 37, 0, GameUtils.gamerImg, this);
    int count = 1;
    public void lunch() {
        this.setVisible(true);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setTitle("雷霆战机");
        GameUtils.objList.add(gamer);
        while(true) {
            createObj();
            repaint();
            try {
                Thread.sleep(25);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = createImage(width, height);
        }
        Graphics gImage = offScreenImage.getGraphics();
        gImage.fillRect(0, 0, width, height);
        gImage.drawImage(GameUtils.bgImg, 0, 0, null);
        for(int i = 0; i < GameUtils.objList.size(); i++) {
            GameUtils.objList.get(i).paintSelf(gImage);
        }
        g.drawImage(offScreenImage, 0, 0, null);
        count++;
    }

    public void createObj() {
        if(count % 15 == 0) {
            GameUtils.bulList.add(new Bullet(gamer.getX() + 15, gamer.getY() - 16, 6, 22, 5, GameUtils.bulletImg, this));
            GameUtils.objList.add(GameUtils.bulList.get(GameUtils.bulList.size() - 1));
        }
        if(count % 35 == 0) {
            GameUtils.planeList.add(new Plane((int)(Math.random() * 450 + 1), 0, 46, 69, 2, GameUtils.planeImg, this));
            GameUtils.objList.add(GameUtils.planeList.get(GameUtils.planeList.size() - 1));
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.lunch();
    }
}

class GameUtils {
    public static Image bgImg = new ImageIcon("Image/background.jpg").getImage();
    public static Image gamerImg = new ImageIcon("Image/gamer.png").getImage();
    public static Image planeImg = new ImageIcon("Image/plane.png").getImage();
    public static Image bulletImg = new ImageIcon("Image/bullet.png").getImage();

    public static List<GameObj> objList = new ArrayList<>();
    public static List<Bullet> bulList = new ArrayList<>();
    public static List<Plane> planeList = new ArrayList<>();
}

class GameObj extends Thread{
    int x;
    int y;
    int width;
    int height;
    int speed;
    Image img;
    Game frame;

    public GameObj() {
    }

    public GameObj(int x, int y, int speed, Image img) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.img = img;
    }

    public GameObj(int x, int y, int width, int height, int speed, Image img, Game frame) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.img = img;
        this.frame = frame;
    }

    @Override
    public void run() {
        super.run();
    }

    public void paintSelf(Graphics gImage) {
        gImage.drawImage(img, x, y, null);
    }
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Game getFrame() {
        return frame;
    }

    public void setFrame(Game frame) {
        this.frame = frame;
    }
}

class Gamer extends GameObj {
    public Gamer() {
    }

    public Gamer(int x, int y, int speed, Image img) {
        super(x, y, speed, img);
    }

    public Gamer(int x, int y, int width, int height, int speed, Image img, Game frame) {
        super(x, y, width, height, speed, img, frame);
        this.frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Gamer.super.x = e.getX() - 18;
                Gamer.super.y = e.getY() - 18;
            }
        });
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}

class Bullet extends GameObj {
    public Bullet() {
        super();
    }

    public Bullet(int x, int y, int speed, Image img) {
        super(x, y, speed, img);
    }

    public Bullet(int x, int y, int width, int height, int speed, Image img, Game frame) {
        super(x, y, width, height, speed, img, frame);


    }

    @Override
    public void run() {
        while(true) {
            if(judge())
                break;
        }
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y -= speed;
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }

    public boolean judge() {
        if(y < 0) {
            GameUtils.bulList.remove(this);
            GameUtils.objList.remove(this);
            return true;
        }
        return false;
    }
}

class Plane extends GameObj {
    int count = 0;

    public Plane() {
        super();
    }

    public Plane(int x, int y, int speed, Image img) {
        super(x, y, speed, img);
    }

    public Plane(int x, int y, int width, int height, int speed, Image img, Game frame) {
        super(x, y, width, height, speed, img, frame);
        start();
    }

    @Override
    public void run() {
        while(true) {
            if(judge())
                break;
        }
    }

    @Override
    public void paintSelf(Graphics gImage) {

        super.paintSelf(gImage);
        if(count > 50)
            y += speed;
        count++;


    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }

    public boolean judge() {
        Game game = new Game();
        if(y > 1024 || this.getRec().intersects(game.gamer.getRec())) {
            GameUtils.objList.remove(this);
        }

        try{
            for(Bullet bul : GameUtils.bulList) {
                if (this.getRec().intersects(bul.getRec())) {

                    GameUtils.objList.remove(this);
                    GameUtils.objList.remove(bul);
                    return true;
                }
            }
        }catch(Exception e) {
            return false;
        }
        return false;
    }
}