package com.aqwsxlostfly.packandgo.Tools;

import com.aqwsxlostfly.packandgo.Heroes.Bullet;
import com.aqwsxlostfly.packandgo.Main;
import com.aqwsxlostfly.packandgo.Screens.GameSc;

public class BulletGenerator {
    boolean isFire;

    public void update(Joystick joy) {
        isFire = joy.getDir().getX() != 0 || joy.getDir().getY() != 0;
        if (isFire) GameSc.bullets.add(new Bullet(Main.bullet, GameSc.player.position,
                25, GameSc.player.radius / 8, joy.getDir()));


    }
}
