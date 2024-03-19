package com.aqwsxlostfly.packandgo.packandgo.config;


import com.aqwsxlostfly.packandgo.packandgo.GameLoop;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public HeadlessApplication getApplication(GameLoop gameLoop) {
        return new HeadlessApplication(gameLoop);
    }
}
