import java.awt.*;
import java.awt.geom.*;
import java.util.Iterator;

public class CatBot extends ClobberBot{

    private int shotClock = 0;
    public Color mycolor;
    private ClobberBotAction currAction;
    private int previousState = 0;

    public CatBot(Clobber game) {
        super(game);
    }
    public void drawMe(Graphics page, Point2D me) {
        Color color = Color.gray;
        Color eyes = Color.magenta;
        Color ears = Color.gray;
        if (this.shotClock <= 19 && this.shotClock >= 17) {
            color = Color.orange;
            eyes = Color.white;
            ears = Color.red;
        }

        int x = (int)me.getX() - 7 - 1;
        int meX = (int)me.getX();
        int y = (int)me.getY() - 7 - 1;
        int meY = (int)me.getY();
        int pos1 = x + 3;
        int pos2 = x + 9;
        int pos3 = y + 3;
        page.setColor(color);
        page.fillOval(x, y, 15, 15);
        page.setColor(ears);
        page.fillPolygon(new int[] {meX-2, meX-5, meX-7}, new int[] {meY-6, meY-12, meY-5}, 3);
        page.fillPolygon(new int[] {meX+2, meX+5, meX+7}, new int[] {meY-6, meY-12, meY-4}, 3);
        page.setColor(eyes);
        page.fillOval(x+3, y+3, 3, 4);
        page.fillOval(x+9, y+3, 3, 4);
        page.setColor(Color.black);
        page.drawArc(x+3, y+8, 3, 4, 0, -180);
        page.drawArc(meX, y+8, 4, 4, 0, -180);
        page.fillOval(meX-2, y+7, 3, 3);
        page.setColor(Color.white);
        page.drawLine(meX, y, meX-2, y+5);
    }
    public ClobberBotAction takeTurn(WhatIKnow currState) {
        --this.shotClock;

        this.currAction = new ClobberBotAction(0, 0);

        int action = getAction(currState);
        int move, shot;
        if (action != 2 && this.shotClock > 0) {
            if( action == 1) {
                move = this.getEscapeDirection(currState);
            }else {
                move = this.getBotDirection(currState);
            }
            this.currAction = new ClobberBotAction(1, move);
            this.previousState = move;
        } else if (this.shotClock <= 0) {
            this.shotClock = this.game.getShotFrequency() + 1;
            shot = this.getBotDirection(currState);
            this.currAction = new ClobberBotAction(2, shot);
            if (this.shotClock > 0) {
                this.previousState = 0;
            }
        }
        return this.currAction;
    }

    private int getAction(WhatIKnow currState){
        Iterator bullet = currState.bullets.iterator();
        Iterator bot = currState.bots.iterator();
        int closestBulletIndex;
        double closestBullet = 500;
        int closestBotIndex;
        double closestBot = 600;

        for(int x = 0; x<currState.bullets.size(); x++)
        {
            double bulletDist = currState.me.distance((ImmutablePoint2D)currState.bullets.get(x));
            if(bulletDist < closestBullet) {
                closestBullet = bulletDist;
                closestBulletIndex = x;
            }
        }

        for(int x = 0; x<currState.bots.size(); x++)
        {
            double botDist = currState.me.distance((ImmutablePoint2D)currState.bots.get(x));
            if(botDist < closestBot) {
                closestBot = botDist;
                closestBotIndex = x;
            }
        }

        if(closestBullet < 40) {
            return 1;
        }else if (closestBot > 100 && closestBullet > 100){
            return 3;
        }
        return 2;
    }

    private int getBotDirection(WhatIKnow currState) {
        double pi = 3.141592654;
        double sin = 0.0;
        BotPoint2D nearestBot = this.getNearestBot(currState);

        double distY = nearestBot.y - currState.me.y;
        double distX = nearestBot.x - currState.me.x;

        double theta = Math.atan2(distY, distX)* 180 / Math.PI;
        int move = 4;

        if ((theta >= -22.5) && (theta < 22.5)) {
            move = 32;
        } else if ((theta >= 22.5) && (theta < 67.5)) {
            move = 40;
        } else if ((theta >= 67.5) && (theta < 112.5)) {
            move = 8;
        } else if ((theta >= 112.5) && (theta < 157.5)) {
            move = 24;
        } else if (((theta >= 157.5) && (theta <= 180)) || ((theta >= -180) && (theta < -157.5))){
            move = 16;
        } else if ((theta >= -157.5) && (theta < -112.5)) {
            move = 20;
        } else if ((theta >= -112.5) && (theta < -67.5)) {
            move = 4;
        } else if ((theta >= -67.5) && (theta < -22.5)) {
            move = 36;
        }
        return move;
    }

    private int getEscapeDirection(WhatIKnow currState) {
        double pi = 3.141592654;
        double sin = 0.0;
        double theta = 0.0;
        BulletPoint2D nearestBullet = null;
        BotPoint2D nearestBot = null;

        nearestBullet = this.getNearestBullet(currState);
        nearestBot = this.getNearestBot(currState);

        double bulletDistY = nearestBullet.getYPlus() - currState.me.y;
        double bulletDistX = nearestBullet.getXPlus() - currState.me.x;
        double botDistY = nearestBot.y - currState.me.y;
        double botDistX = nearestBot.x - currState.me.x;

        double botDistance = this.botDist(currState.me, nearestBot);
        double bulletDistance = this.bulletDist(nearestBullet, currState.me);

        if(botDistance < bulletDistance){
            theta = Math.atan2(botDistY, botDistX)* 180 / Math.PI;
        }else{
            theta = Math.atan2(bulletDistY, bulletDistX)* 180 / Math.PI;
        }

        int move = 4;
        if(nearestBullet!=null){
            if(currState.me.getX() <= 575 && currState.me.getX() >= 25) {
                if (currState.me.getY() <= 575 && currState.me.getY() >= 25) {
                    if ((theta >= -22.5) && (theta < 22.5)) {
                        if (previousState == 20){
                            move = 20;
                        }else if(previousState == 24){
                            move = 24;
                        }else {
                            if ((theta >= -22.5) && (theta < 0)) {
                                move = 20;
                            } else {
                                move = 24;
                            }
                        }
                    }  else if ((theta >= 22.5) && (theta < 67.5)) {
                        if (previousState == 16){
                            move = 16;
                        }else if(previousState == 4){
                            move = 4;
                        }else {
                            if ((theta >= 22.5) && (theta < 45)) {
                                move = 16;
                            } else {
                                move = 4;
                            }
                        }
                    }  else if ((theta >= 67.5) && (theta < 112.5)){
                        if (previousState == 20){
                            move = 20;
                        }else if(previousState == 36){
                            move = 36;
                        }else {
                            if ((theta >= 67.5) && (theta < 90)) {
                                move = 20;
                            } else {
                                move = 36;
                            }
                        }
                    } else if ((theta >= 112.5) && (theta < 157.5)) {
                        if (previousState == 32){
                            move = 32;
                        }else if(previousState == 4){
                            move = 4;
                        }else {
                            if ((theta >= 112.5) && (theta < 135)) {
                                move = 4;
                            } else {
                                move = 32;
                            }
                        }
                    } else if (((theta >= 157.5) && (theta <= 180)) || ((theta >= -180) && (theta < -157.5))){
                        if (previousState == 40){
                            move = 40;
                        }else if(previousState == 36){
                            move = 36;
                        }else {
                            if (((theta >= 157.5) && (theta <= 180))) {
                                move = 36;
                            } else {
                                move = 40;
                            }
                        }
                    } else if ((theta >= -157.5) && (theta < -112.5)){
                        if (previousState == 8){
                            move = 8;
                        }else if(previousState == 32){
                            move = 32;
                        }else {
                            if ((theta >= -157.5) && (theta < -135)) {
                                move = 32;
                            } else {
                                move = 8;
                            }
                        }
                    } else if ((theta >= -112.5) && (theta < -67.5)) {
                        if (previousState == 40){
                            move = 40;
                        }else if(previousState == 24){
                            move = 24;
                        }else {
                            if ((theta >= -112.5) && (theta < -90)) {
                                move = 40;
                            } else {
                                move = 24;
                            }
                        }
                    } else if ((theta >= -67.5) && (theta < -22.5)){
                        if (previousState == 16){
                            move = 16;
                        }else if(previousState == 8){
                            move = 8;
                        }else {
                            if ((theta >= -67.5) && (theta < -45)) {
                                move = 8;
                            } else {
                                move = 16;
                            }
                        }
                    }
                }
            }
        }

        if(move != 0){
            this.shotClock++;
        }
        return move;
    }
    private BotPoint2D getNearestBot(WhatIKnow currState) {
        BotPoint2D nearestBot = null;
        double dist = 100000.0;
        Iterator bot = currState.bots.iterator();

        while(bot.hasNext()) {
            BotPoint2D currBot = (BotPoint2D)bot.next();
            double currDist = this.botDist(currState.me, currBot);
            if (currDist < dist) {
                nearestBot = currBot;
                dist = currDist;
            }
        }
        return nearestBot;
    }

    private BulletPoint2D getNearestBullet(WhatIKnow currState) {
        Iterator it = currState.bullets.iterator();
        BulletPoint2D bullet = null;
        double dist;
        double mDist = 10000.0;

        while(it.hasNext()) {
            BulletPoint2D b = (BulletPoint2D)it.next();
            dist = bulletDist(b, currState.me);
            if (dist < mDist) {
                mDist = dist;
                bullet = b;
            }
        }
        return bullet;
    }

    private double botDist(BotPoint2D bot1, BotPoint2D bot2) {
        return this.getDist(bot1.x, bot1.y, bot2.x, bot2.y);
    }

    private double bulletDist(BulletPoint2D bullet, BotPoint2D bot) {
        return this.getDist(bullet.getXPlus(), bullet.getYPlus(), bot.x, bot.y);
    }

    private double getDist(double point1X, double point1Y, double point2X, double point2Y) {
        double x = point1X - point2X;
        double y = point1Y - point2Y;
        return Math.sqrt(x * x + y * y);
    }

    public String toString()
    {
        return "CatBot by Patricia Capitan, Aida Gomezbueno, and Marie Plouy";
    }
}