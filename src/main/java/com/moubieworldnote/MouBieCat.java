/**
 * LICENSE
 * WorldNotePlugin
 * -------------
 * Copyright (C) 2022 MouBieCat(MouBie_Yuki)
 * -------------
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 */

package com.moubieworldnote;

import com.moubieworldnote.song.ISongManager;
import com.moubieworldnote.song.SongManager;
import com.moubieworldnote.song.WorldSongManager;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * @author MouBieCat
 */
public final class MouBieCat extends JavaPlugin {

    @NotNull
    private final SongManager songsManager = new SongManager();

    @NotNull
    private final WorldSongManager worldSongManager = new WorldSongManager();

    @NotNull
    private final YamlConfiguration setting = new YamlConfiguration();

    @Override
    public void onEnable() {
        if (!this.parePluginNbsFiles())
            this.getLogger().info("§aCreate file path or no files");

        if (!this.loadSettings())
            this.getLogger().warning("§cLoad §6Settings.yml§c file error");
        this.registerWorldSong();

        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
    }

    @Override
    public void onDisable() {
        for (final Player player : Bukkit.getOnlinePlayers())
            this.songsManager.stopSongToPlayer(player);
    }

    /**
     * pare plugin dir files, server/plugins/myPluginName/songs/*.nbs
     * @return it's ok ?
     */
    private boolean parePluginNbsFiles() {
        final File dataFolder = this.getDataFolder();
        final File songFilePath = new File(dataFolder, "songs");

        if (!songFilePath.exists() & !songFilePath.mkdirs())
            return false;

        final File[] files = songFilePath.listFiles();
        if (files == null)
            return false;

        for (final File file : files) {
            final File bufferFile = new File(songFilePath, file.getName());
            final Song song = SongManager.SongHelper.pareSing(bufferFile);
            if (song != null)
                this.songsManager.addSong(file.getName(), song);
        }
        this.getLogger().info("§aLoad §6" + this.songsManager.getSongSize() + " §aSongs.");
        return true;
    }

    /**
     * Load plugin file, settings.yml
     * @return it's ok ?
     */
    private boolean loadSettings() {
        final File settingFile = new File(this.getDataFolder(), "settings.yml");
        if (!settingFile.exists())
            this.saveResource("settings.yml", false);
        try { this.setting.load(settingFile); } catch (IOException | InvalidConfigurationException e) { return false; }
        return true;
    }

    /**
     * Register world play song name
     */
    private void registerWorldSong() {
        final ConfigurationSection worlds = this.setting.getConfigurationSection("Worlds");
        if (worlds == null)
            return;

        final Set<String> keys = worlds.getKeys(false);
        for (final String key : keys) {
            final World world = Bukkit.getWorld(key);
            final String songName = this.setting.getString("Worlds." + key);
            if (world == null || songName == null)
                continue;

            // Register world
            this.worldSongManager.register(world, songName);
        }
    }

    /**
     * Get this plugin
     * @return plugin
     */
    @NotNull
    public static MouBieCat getInstance() {
        return JavaPlugin.getPlugin(MouBieCat.class);
    }

    /**
     * Get song manager
     * @return manager
     */
    @NotNull
    public ISongManager getSongsManager() {
        return this.songsManager;
    }

    /**
     * Get world play song name manager
     * @return world manager
     */
    @NotNull
    public WorldSongManager getWorldSongManager() {
        return this.worldSongManager;
    }

}
