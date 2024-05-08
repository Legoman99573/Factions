package com.massivecraft.factions.util.particle;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class BukkitParticleProvider implements ParticleProvider<Particle> {

    @Override
    public String name() {
        return "BUKKIT";
    }

    @Override
    public void spawn(Particle effect, Location location, int count) {
        location.getWorld().spawnParticle(effect, location, count);
    }

    @Override
    public void playerSpawn(Player player, Particle effect, Location location, int count) {
        player.spawnParticle(effect, location, count);
    }

    @Override
    public void spawn(Particle particle, Location location, int count, double speed, double offsetX, double offsetY, double offsetZ) {
        location.getWorld().spawnParticle(particle, location, count, offsetX, offsetY, offsetZ, speed);
    }

    @Override
    public void playerSpawn(Player player, Particle particle, Location location, int count, double speed, double offsetX, double offsetY, double offsetZ) {
        player.spawnParticle(particle, location, count, offsetX, offsetY, offsetZ, speed);
    }

    @Override
    public void spawn(Particle particle, Location location, ParticleColor color) {
        if (particle.getDataType().equals(Particle.DustOptions.class)) {
            location.getWorld().spawnParticle(particle, location, 1, new Particle.DustOptions(color.getColor(), 1));
        } else if (particle.getDataType()==Void.class){
            location.getWorld().spawnParticle(particle, location, 1, null);
        }
    }

    @Override
    public void playerSpawn(Player player, Particle particle, Location location, ParticleColor color) {
        if (particle.getDataType().equals(Particle.DustOptions.class)) {
            player.spawnParticle(particle, location, 1, new Particle.DustOptions(color.getColor(), 1.5f));
        } else if (particle.getDataType()==Void.class){
            player.spawnParticle(particle, location, 1, null);
        }
    }

    @Override
    public Particle effectFromString(String string) {
        for (Particle particle : Particle.values()) {
            if (particle.name().equalsIgnoreCase(string)) {
                return particle;
            }
        }
        return null;
    }

    @Override
    public String effectName(Particle particle) {

        if (particle != null) {
            return particle.name();
        } else {
            return "DUST"; // 1.20.6 breaks stuff. Just fallback on this. Probably a better way to implement this, but I am dum
        }
    }
}
