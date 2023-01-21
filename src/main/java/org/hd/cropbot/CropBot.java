package org.hd.cropbot;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.hd.cropbot.listeners.EventListeners;

import javax.security.auth.login.LoginException;

public class CropBot {

    private final ShardManager shardManager;
    public CropBot() throws LoginException {
        String token = "MTA2NjE3OTc0MDk2MjEyNzk0Mg.GLb-3g.HZ7HM2unObt85NT1_CDc6gNecgZB85147Asrrk";
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token,GatewayIntent.GUILD_MESSAGES);
        builder.setStatus(OnlineStatus.ONLINE);
        //builder.enableIntents(GatewayIntent.DIRECT_MESSAGES);
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES);
        //builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
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
