package me.neznamy.tab.platforms.velocity;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.proxy.connection.client.ConnectedPlayer;

import me.neznamy.tab.shared.ITabPlayer;
import me.neznamy.tab.shared.ProtocolVersion;
import me.neznamy.tab.shared.placeholders.Placeholders;
import net.kyori.adventure.text.TextComponent;

/**
 * TabPlayer for Velocity
 */
public class VelocityTabPlayer extends ITabPlayer{

	private Player player;

	public VelocityTabPlayer(Player p, String server) {
		player = p;
		world = server;
		channel = ((ConnectedPlayer)player).getConnection().getChannel();
		uniqueId = p.getUniqueId();
		name = p.getUsername();
		version = ProtocolVersion.fromNetworkId(player.getProtocolVersion().getProtocol());
		init();
	}
	
	@Override
	public boolean hasPermission(String permission) {
		return player.hasPermission(permission);
	}
	
	@Override
	public long getPing() {
		return player.getPing();
	}
	
	@Override
	public void sendPacket(Object nmsPacket) {
		if (nmsPacket != null) ((ConnectedPlayer)player).getConnection().write(nmsPacket);
	}
	
	@Override
	public void sendMessage(String message, boolean translateColors) {
		if (message == null || message.length() == 0) return;
		player.sendMessage(TextComponent.of(translateColors ? Placeholders.color(message): message));
	}
	
	@Override
	public Object getSkin() {
		return player.getGameProfile().getProperties();
	}
	
	@Override
	public Player getPlayer() {
		return player;
	}
}