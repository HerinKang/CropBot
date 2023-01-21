package org.hd.cropbot;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.hd.cropbot.listeners.EventListeners;

import javax.security.auth.login.LoginException;

public class CropBot {

    private final ShardManager shardManager;
    public CropBot() throws LoginException {
        String token = "MTA2NjE3OTc0MDk2MjEyNzk0Mg.GXphf2.xxHTu2BGpSAbJT-sY6nBx0L0w4Yi2MacvGAa8g";
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        shardManager = builder.build();

        //registered listeners
        shardManager.addEventListener(new EventListeners());
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public static void main(String[] args) {
        try {
            CropBot cropBot = new CropBot();
        } catch (LoginException e) {
            System.out.println("Invalid token");
        }

    }

}
