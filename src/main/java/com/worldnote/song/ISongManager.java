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
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author MouBieCat
 */
public interface ISongManager {

    /**
     * Play song to player
     * @param player player
     * @param songName song name
     */
    void playSongToPlayer(final @NotNull Player player, final @NotNull String songName);

    /**
     * Play song to player
     * @param player player
     * @param song song
     */
    void playSongToPlayer(final @NotNull Player player, final @NotNull Song song);

    /**
     * Stop song to player
     * @param player player
     */
    void stopSongToPlayer(final @NotNull Player player);

}
