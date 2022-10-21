//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.io.PrintStream;
import java.util.Iterator;

public class p10 extends ClobberBot {
    ClobberBotAction action;
    int minx;
    int miny;
    int maxx;
    int maxy;
    int swit = 0;
    boolean firstturn = true;

    public p10(Clobber currState) {
        super(currState);
        this.mycolor = Color.orange;
        this.minx = currState.getMinX();
        this.miny = currState.getMinY();
        this.maxx = currState.getMaxX();
        this.maxy = currState.getMaxY();
    }

    private void showWhatIKnow(WhatIKnow var1) {
        PrintStream var10000 = System.out;
        int var10001 = var1.me.getID();
        var10000.println("My id is " + var10001 + ", I'm at position (" + var1.me.getX() + ", " + var1.me.getY() + ")");
        System.out.print("Bullets: ");
        Iterator var2 = var1.bullets.iterator();

        while(var2.hasNext()) {
            BulletPoint2D var3 = (BulletPoint2D)var2.next();
            System.out.print("" + var3 + ", ");
        }

        System.out.println();
        System.out.print("Bots: ");
        Iterator var5 = var1.bots.iterator();

        while(var5.hasNext()) {
            BotPoint2D var4 = (BotPoint2D)var5.next();
            System.out.print("" + var4 + ", ");
        }

        System.out.println();
    }

    public ClobberBotAction takeTurn(WhatIKnow currState) {
        double meX = currState.me.getX();
        double meY = currState.me.getY();
        BotPoint2D closestBot = this.findBot(currState);
        double cBotX = closestBot.getX();
        double xBotY = closestBot.getY();
        BulletPoint2D cBullet = this.findBullet(currState);
        if (!this.firstturn && cBullet != null) {
            double cBulletX = cBullet.getX();
            double cbul = cBullet.getY();
            if (Point2D.distance(meX, meY, cBulletX, cbul) <= 60.0) {
                if (cBullet.getYPlus() > 0 && cBullet.getXPlus() > 0 && meY > cbul && meX > cBulletX && Math.abs(Math.abs(meX - cBulletX) - Math.abs(meY - cbul)) < 20.0) {
                    return this.action = new ClobberBotAction(1, 36);
                }

                if (cBullet.getYPlus() > 0 && cBullet.getXPlus() < 0 && meY > cbul && meX < cBulletX && Math.abs(Math.abs(meX - cBulletX) - Math.abs(meY - cbul)) < 20.0) {
                    return this.action = new ClobberBotAction(1, 20);
                }

                if (cBullet.getYPlus() < 0 && cBullet.getXPlus() > 0 && meY < cbul && meX > cBulletX && Math.abs(Math.abs(meX - cBulletX) - Math.abs(meY - cbul)) < 20.0) {
                    return this.action = new ClobberBotAction(1, 40);
                }

                if (cBullet.getYPlus() < 0 && cBullet.getXPlus() < 0 && meY < cbul && meX < cBulletX && Math.abs(Math.abs(meX - cBulletX) - Math.abs(meY - cbul)) < 20.0) {
                    return this.action = new ClobberBotAction(1, 24);
                }

                if (cBullet.getXPlus() > 0 && cBullet.getYPlus() == 0 && meX > cBulletX && Math.abs(meY - cbul) < 15.0) {
                    if (!(meY < cbul) || !(Math.abs(meY - (double)this.miny) < 10.0) && !(Math.abs(meY - (double)this.maxy) < 10.0)) {
                        return this.action = new ClobberBotAction(1, 8);
                    }

                    return this.action = new ClobberBotAction(1, 4);
                }

                if (cBullet.getXPlus() < 0 && cBullet.getYPlus() == 0 && meX < cBulletX && Math.abs(meY - cbul) < 15.0) {
                    if (!(meY < cbul) || !(Math.abs(meY - (double)this.miny) < 10.0) && !(Math.abs(meY - (double)this.maxy) < 10.0)) {
                        return this.action = new ClobberBotAction(1, 8);
                    }

                    return this.action = new ClobberBotAction(1, 4);
                }

                if (cBullet.getYPlus() < 0 && cBullet.getXPlus() == 0 && meY < cbul && Math.abs(meX - cBulletX) < 15.0) {
                    if (!(meX < cBulletX) || !(Math.abs(meX - (double)this.minx) < 10.0) && !(Math.abs(meX - (double)this.maxx) < 10.0)) {
                        return this.action = new ClobberBotAction(1, 32);
                    }

                    return this.action = new ClobberBotAction(1, 16);
                }

                if (cBullet.getYPlus() > 0 && cBullet.getXPlus() == 0 && meY > cbul && Math.abs(meX - cBulletX) < 15.0) {
                    if (!(meX < cBulletX) || !(Math.abs(meX - (double)this.minx) < 10.0) && !(Math.abs(meX - (double)this.maxx) < 10.0)) {
                        return this.action = new ClobberBotAction(1, 32);
                    }

                    return this.action = new ClobberBotAction(1, 16);
                }
            }
        }

        if (meX - cBotX <= 0.0 && meY - xBotY <= 0.0) {
            if (meX < cBotX) {
                if (meY < xBotY || meY == (double)this.miny) {
                    this.action = new ClobberBotAction(1, 8);
                }

                if (meY > xBotY || meY == (double)this.maxy) {
                    this.action = new ClobberBotAction(1, 4);
                }

                if (meY == xBotY || Math.abs(meY - xBotY) < 10.0) {
                    this.action = new ClobberBotAction(2, 32);
                }

                if (meY < xBotY && Math.abs(Math.abs(meX - cBotX) - Math.abs(meY - xBotY)) < 20.0) {
                    this.action = new ClobberBotAction(2, 40);
                }

                if (meY > xBotY && Math.abs(Math.abs(meX - cBotX) - Math.abs(meY - xBotY)) < 20.0) {
                    this.action = new ClobberBotAction(2, 36);
                }
            } else if (meX > cBotX) {
                if (meY < xBotY || meY == (double)this.miny) {
                    this.action = new ClobberBotAction(1, 8);
                }

                if (meY > xBotY || meY == (double)this.maxy) {
                    this.action = new ClobberBotAction(1, 4);
                }

                if (meY == xBotY || Math.abs(meY - xBotY) < 10.0) {
                    this.action = new ClobberBotAction(2, 16);
                }

                if (meY < xBotY && Math.abs(Math.abs(meX - cBotX) - Math.abs(meY - xBotY)) < 20.0) {
                    this.action = new ClobberBotAction(2, 24);
                }

                if (meY > xBotY && Math.abs(Math.abs(meX - cBotX) - Math.abs(meY - xBotY)) < 20.0) {
                    this.action = new ClobberBotAction(2, 20);
                }
            } else if (meX == cBotX || Math.abs(meX - cBotX) < 100.0) {
                if (meY < xBotY) {
                    this.action = new ClobberBotAction(2, 8);
                }

                if (meY > xBotY) {
                    this.action = new ClobberBotAction(2, 4);
                }
            }
        } else if (meY < xBotY) {
            if (meX < cBotX || meX == (double)this.maxx) {
                this.action = new ClobberBotAction(1, 32);
            }

            if (meX > cBotX || meX == (double)this.minx) {
                this.action = new ClobberBotAction(1, 16);
            }

            if (meX == cBotX || Math.abs(meX - cBotX) < 10.0) {
                this.action = new ClobberBotAction(2, 8);
            }

            if (meX < cBotX && Math.abs(Math.abs(meX - cBotX) - Math.abs(meY - xBotY)) < 20.0) {
                this.action = new ClobberBotAction(2, 40);
            }

            if (meX > cBotX && Math.abs(Math.abs(meX - cBotX) - Math.abs(meY - xBotY)) < 20.0) {
                this.action = new ClobberBotAction(2, 24);
            }
        } else if (meY > xBotY) {
            if (meX < cBotX || meX == (double)this.maxx) {
                this.action = new ClobberBotAction(1, 32);
            }

            if (meX > cBotX || meX == (double)this.minx) {
                this.action = new ClobberBotAction(1, 16);
            }

            if (meX == cBotX || Math.abs(meX - cBotX) < 10.0) {
                this.action = new ClobberBotAction(2, 4);
            }

            if (meX < cBotX && Math.abs(Math.abs(meX - cBotX) - Math.abs(meY - xBotY)) < 20.0) {
                this.action = new ClobberBotAction(2, 36);
            }

            if (meX > cBotX && Math.abs(Math.abs(meX - cBotX) - Math.abs(meY - xBotY)) < 20.0) {
                this.action = new ClobberBotAction(2, 20);
            }
        } else if (meY == xBotY || Math.abs(meY - xBotY) < 100.0) {
            if (meX < cBotX) {
                this.action = new ClobberBotAction(2, 32);
            }

            if (meX > cBotX) {
                this.action = new ClobberBotAction(2, 16);
            }
        }

        this.firstturn = false;
        return this.action;
    }

    private BotPoint2D findBot(WhatIKnow var1) {
        Iterator var2 = var1.bots.iterator();
        BotPoint2D var3 = null;
        double var4 = 1000000.0;
        double var6 = 10000.0;

        while(var2.hasNext()) {
            BotPoint2D var8 = (BotPoint2D)var2.next();
            var4 = Point2D.distance(var1.me.getX(), var1.me.getY(), var8.getX(), var8.getY());
            if (Math.abs(var1.me.getX() - var8.getX()) < 10.0 || Math.abs(var1.me.getX() - var8.getX()) < 10.0) {
                var3 = var8;
            }

            if (var4 < var6) {
                var6 = var4;
                var3 = var8;
            }
        }

        return var3;
    }

    private BulletPoint2D findBullet(WhatIKnow var1) {
        Iterator var2 = var1.bullets.iterator();
        BulletPoint2D var3 = null;
        double var4 = 1000000.0;
        double var6 = 10000.0;

        while(var2.hasNext()) {
            BulletPoint2D var8 = (BulletPoint2D)var2.next();
            var4 = Point2D.distance(var1.me.getX(), var1.me.getY(), var8.getX(), var8.getY());
            if (var4 < var6) {
                var6 = var4;
                var3 = var8;
            }
        }

        return var3;
    }

    public void drawMe(Graphics var1, Point2D var2) {
        int var3 = (int)var2.getX() - 7 - 1;
        int var4 = (int)var2.getY() - 7 - 1;
        int var5 = (int)var2.getX();
        int var6 = (int)var2.getY();
        var1.setColor(Color.white);
        var1.drawOval(var3 + 3, var4, 9, 8);
        var1.setColor(this.mycolor);
        var1.fillOval(var3, var6, 15, 8);
        var1.setColor(Color.green);
        var1.fillOval(var3 + 5, var4 + 3, 5, 5);
        var1.drawLine(var5, var6, var5 + 8, var6 - 8);
        var1.drawLine(var5, var6, var5 - 8, var6 - 8);
        var1.setColor(Color.blue);
        var1.drawLine(var5 + 3, var6 + 2, var5 + 3, var6 + 6);
        var1.drawLine(var5 - 3, var6 + 2, var5 - 3, var6 + 6);
    }

    public String toString() {
        return "p10";
    }
}
