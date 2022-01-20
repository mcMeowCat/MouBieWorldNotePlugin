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
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MouBieCat
 */
public final class PlayerSongManager {

    private final Map<Player, RadioSongPlayer> playerSong = new HashMap<>();

    public void play(final @NotNull Player player, final @NotNull Song song) {
        this.addPlayer(player, new RadioSongPlayer(song));
    }

    public void stop(final @NotNull Player player) {
        this.removePlayer(player);
    }

    private void addPlayer(final @NotNull Player player, final @NotNull RadioSongPlayer songPlayer) {
        final RadioSongPlayer getSongPlayer = this.getSongPlayer(player);
        if (getSongPlayer != null)
            this.removePlayer(player);

        songPlayer.addPlayer(player);
        songPlayer.setPlaying(true);
        this.playerSong.put(player, songPlayer);
    }

    private void removePlayer(final @NotNull Player player) {
        final RadioSongPlayer songPlayer = this.getSongPlayer(player);
        if (songPlayer != null) {
            songPlayer.removePlayer(player);
            songPlayer.setPlaying(false);
        }
    }

    @Nullable
    public RadioSongPlayer getSongPlayer(final @NotNull Player player) {
        return this.playerSong.get(player);
    }

}
