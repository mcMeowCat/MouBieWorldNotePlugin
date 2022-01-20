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

package com.worldnote.song;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MouBieCat
 */
public final class SongManager implements ISongManager {

    @NotNull
    private final Map<String, Song> songs = new HashMap<>();

    @NotNull
    private final PlayerSongManager songManager = new PlayerSongManager();

    /**
     * Add a song in manager
     * @param name song name
     * @param song song
     */
    public void addSong(final @NotNull String name, final @NotNull Song song) {
        if (!this.songs.containsKey(name))
            this.songs.put(name, song);
    }

    /**
     * Remove a song to manager
     * @param name name
     */
    public void removeSong(final @NotNull String name) {
        this.songs.remove(name);
    }

    /**
     * Get a song, but the song maybe a Null
     * @param name song name
     * @return song ( maybe null )
     */
    @Nullable
    public Song getSong(final @NotNull String name) {
        return this.songs.get(name);
    }

    /**
     * Play song to player
     * @param player player
     * @param songName song name
     */
    public void playSongToPlayer(final @NotNull Player player, final @NotNull String songName) {
        final Song song = this.getSong(songName);
        if (song != null)
            this.songManager.play(player, song);
    }

    /**
     * Play song to player
     * @param player player
     * @param song song
     */
    public void playSongToPlayer(final @NotNull Player player, final @NotNull Song song) {
        this.songManager.play(player, song);
    }

    /**
     * Stop song to player
     * @param player player
     */
    public void stopSongToPlayer(final @NotNull Player player) {
        this.songManager.stop(player);
    }

    /**
     * Get now song size
     * @return size
     */
    public int getSongSize() {
        return this.songs.size();
    }

    public static class SongHelper {
        /**
         * Parsing song file
         * @param file file
         * @return a song
         */
        @Nullable
        public static Song pareSing(final @NotNull File file) {
            return NBSDecoder.parse(file);
        }
    }

}
